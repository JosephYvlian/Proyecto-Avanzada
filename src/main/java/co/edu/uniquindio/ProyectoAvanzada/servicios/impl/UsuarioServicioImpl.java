package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;


import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.UsuarioMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CodigoRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.seguridad.JWTUtils;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final EmailServicio emailServicio;
    private final CodigoRepo codigoRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Override
    public void crear(CrearUsuarioDTO dto) {
        if (siExisteEmail(dto.email())) {
            throw new RuntimeException("El correo ya está en uso");
        }


        Usuario usuario = usuarioMapper.toDocument(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        if (dto.email().contains(".nex@gmail.com")) {
            usuario.setEstadoCuenta(EstadoCuenta.ACTIVO);
            usuario.setRol(Rol.ADMINISTRADOR);
            usuario.setFechaRegistro(LocalDateTime.now());

            usuario.setPassword(passwordEncoder.encode(dto.password()));
            usuarioRepo.save(usuario);
            return;
        }

        // Generar código y expiración
        String codigo = generarCodigo();
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(15);

        // Guardar código en colección aparte
        CodigoValidacion codigoValidacion = new CodigoValidacion();
        codigoValidacion.setCodigo(codigo);
        codigoValidacion.setEmail(dto.email());
        codigoValidacion.setFecha(expiracion);

        codigoRepo.save(codigoValidacion);


        /// Guardar usuario
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        usuarioRepo.save(usuario);


        // Crear el mensaje del correo
        String asunto = "Verification Code NEX Platform";
        String cuerpo = "Hola " + dto.nombre() + ",\n\nTu código de verificación es: " + codigo +
                "\nEste código expirará en 15 minutos.\n\nGracias por registrarte.";

        // Crear DTO y enviar correo
        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, dto.email());

        try {
            emailServicio.enviarCorreo(emailDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de verificación", e);
        }
    }

    @Override
    public void editar(EditarUsuarioDTO dto, String idUsuario) {
        ObjectId objectId = new ObjectId(idUsuario);
        String id = jwtUtils.getUsuarioIdFromToken("token");
        Usuario usuario = usuarioRepo.buscarUsuarioPorId(id).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        usuarioMapper.editarUsuarioDTO(dto, usuario);
        usuarioRepo.save(usuario);
    }

    @Override
    public void eliminar(String idUsuario) {
        String id = jwtUtils.getUsuarioIdFromToken("token");

        Usuario usuario = usuarioRepo.buscarUsuarioPorId(id).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        usuario.setEstadoCuenta(EstadoCuenta.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDTO obtener(String idUsuario) {
        Usuario usuario = usuarioRepo.buscarUsuarioPorId(idUsuario).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<UsuarioDTO> lista = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = usuarioMapper.toDTO(usuario);
            lista.add(dto);
        }

        return lista;

    }

    @Override
    public void verificarUsuario(CodVerificacionDTO codVerificacion) {

        CodigoValidacion codigoValidacion = codigoRepo.buscarPorEmail(codVerificacion.email())
                .orElseThrow(() -> new RuntimeException("No hay código de verificación para este correo."));

        if (!codigoValidacion.getCodigo().equals(codVerificacion.codigo())) {
            throw new RuntimeException("El código del usuario no es válido");
        }

        if (LocalDateTime.now().isAfter(codigoValidacion.getFecha())) {
            throw new RuntimeException("El código ha expirado.");
        }

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail(codVerificacion.email())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));

        usuario.setEstadoCuenta(EstadoCuenta.ACTIVO);
        usuarioRepo.save(usuario);
        codigoRepo.delete(codigoValidacion);
    }

    @Override
    public void recuperarUsuario(String email) {

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail(email)
                .orElseThrow(() -> new RuntimeException("No existe un usuario con ese correo"));

        // Generar código y expiración
        String codigo = generarCodigo();
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(15);

        // Guardar código en colección aparte
        CodigoValidacion codigoValidacion = new CodigoValidacion();
        codigoValidacion.setCodigo(codigo);
        codigoValidacion.setEmail(email);
        codigoValidacion.setFecha(expiracion);

        codigoRepo.save(codigoValidacion);

        // Crear el mensaje del correo
        String asunto = "Código de recuperación de contraseña - NEX";
        String cuerpo = "Hola " + usuario.getNombre() + ",\n\n" +
                "Has solicitado recuperar tu cuenta. Tu código de verificación es: " + codigo +
                "\nEste código expirará en 15 minutos.\n\n" +
                "Si no solicitaste esto, ignora este mensaje.";

        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, email);

        try {
            emailServicio.enviarCorreo(emailDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de recuperación", e);
        }
    }

    @Override
    public void actualizarContrasena(ActualizarContrasenaDTO actualizarContrasena) {
        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail(actualizarContrasena.email())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con ese correo"));

        // Verifica que la contraseña actual ingresada coincida con la guardada
        if (!passwordEncoder.matches(actualizarContrasena.contrasenaActual(), usuario.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        // Encripta y actualiza la nueva contraseña
        usuario.setPassword(passwordEncoder.encode(actualizarContrasena.contrasenaNueva()));
        usuarioRepo.save(usuario);
    }

    @Override
    public void recuperarContrasena(RecuperarContrasenaDTO recuperarContrasena) {

        CodigoValidacion codigoValidacion = codigoRepo.buscarPorEmail(recuperarContrasena.email())
                .orElseThrow(() -> new RuntimeException("No hay código registrado para este correo"));

        if (!codigoValidacion.getCodigo().equals(recuperarContrasena.codigo())) {
            throw new RuntimeException("El código ingresado no es válido");
        }

        if (LocalDateTime.now().isAfter(codigoValidacion.getFecha())) {
            throw new RuntimeException("El código ha expirado");
        }

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail(recuperarContrasena.email())
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con ese correo"));

        usuario.setPassword(passwordEncoder.encode(recuperarContrasena.nuevaContrasena()));

        usuarioRepo.save(usuario);

        // Eliminar código para evitar reutilización
        codigoRepo.delete(codigoValidacion);

    }

    private boolean siExisteEmail(String email) {
        return usuarioRepo.buscarUsuarioPorEmail(email).isPresent();
    }

    private boolean siExisteId(String idUsuario) {
        return usuarioRepo.buscarUsuarioPorId(idUsuario).isPresent();
    }

    private String generarCodigo() {
        int codigo = (int) (Math.random() * 1_000_000); // Valor entre 0 y 999999
        return String.format("%06d", codigo); // Rellena con ceros a la izquierda si es necesario
    }


    public String obtenerCorreoPorId(String idUsuario) {
        Optional<Usuario> usuario = usuarioRepo.buscarUsuarioPorId(idUsuario);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }
        return usuario.get().getEmail();
    }
}