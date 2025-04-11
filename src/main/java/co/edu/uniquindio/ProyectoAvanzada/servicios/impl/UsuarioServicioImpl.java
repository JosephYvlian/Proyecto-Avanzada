package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;


import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
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
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final EmailServicio emailServicio;
    private final CodigoRepo codigoRepo;

    @Override
    public void crear(CrearUsuarioDTO cuenta) {
        if (siExisteEmail(cuenta.email())) {
            throw new RuntimeException("El correo ya está en uso");
        }

        if ((siExisteCedula(cuenta.cedula()))) {
            throw new RuntimeException("La cedula ya existe");
        }
        Usuario usuario = usuarioMapper.toDocument(cuenta);
        usuario.setEstadoCuenta(EstadoCuenta.INACTIVO);
        usuario.setRol(Rol.CLIENTE);
        usuario.setFechaRegistro(LocalDateTime.now());

        // Generar código y expiración
        String codigo = generarCodigo();
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(15);
        usuario.setCodigoValidacion(new CodigoValidacion(codigo, cuenta.email(), expiracion));


        // Guardar usuario
        usuarioRepo.save(usuario);

        // Crear el mensaje del correo
        String asunto = "Código de verificación de tu cuenta";
        String cuerpo = "Hola " + cuenta.nombre() + ",\n\nTu código de verificación es: " + codigo +
                "\nEste código expirará en 15 minutos.\n\nGracias por registrarte.";

        // Crear DTO y enviar correo
        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, cuenta.email());

        try {
            emailServicio.enviarCorreo(emailDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de verificación", e);
        }
    }

    @Override
    public void editar(EditarUsuarioDTO cuenta, String cedula){
        Usuario usuario = usuarioRepo.buscarUsuarioPorCedula(cedula).orElse(null);

        if (usuario == null || !siExisteCedula(cedula)) {
            throw new RuntimeException("El usuario no existe. ");
        }

        if (!usuario.getEmail().equals(cuenta.email()) && siExisteEmail(cuenta.email())) {
            throw new RuntimeException("El nuevo correo ya está en uso");
        }

        usuarioMapper.actualizarUsuarioDesdeDTO(usuario, cuenta);
        usuarioRepo.save(usuario);
    }

    @Override
    public void eliminar(String cedula) {
        Usuario usuario = usuarioRepo.buscarUsuarioPorCedula(cedula).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        usuario.setEstadoCuenta(EstadoCuenta.ELIMINADO);
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDTO obtener(String cedula) {
        Usuario usuario = usuarioRepo.buscarUsuarioPorCedula(cedula).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<UsuarioDTO> listaDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getEstadoCuenta() == EstadoCuenta.ACTIVO) {
                UsuarioDTO dto = usuarioMapper.toDTO(usuario);
                listaDTO.add(dto);
            }

        }

        return listaDTO;
    }

    @Override
    public void verificarUsuario(String email, String codigo) {

        CodigoValidacion codigoValidacion = codigoRepo.buscarPorEmail(email).orElse(null);

        assert codigoValidacion != null;
        if(!codigoValidacion.getCodigo().equals(codigo)) {
            throw new RuntimeException("No hay codigo de verificacion para este correo.");
        }

        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(15);

        if(LocalDateTime.now().isAfter(expiracion)) {
            throw new RuntimeException("El codigo ha expirado.");
        }

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail(email).orElse(null);

        assert usuario != null;
        usuario.setEstadoCuenta(EstadoCuenta.ACTIVO);
        codigoRepo.delete(codigoValidacion);
    }

    private boolean siExisteEmail(String email) {
        return usuarioRepo.buscarUsuarioPorEmail(email).isPresent();
    }

    private boolean siExisteCedula(String cedula) {
        return usuarioRepo.buscarUsuarioPorCedula(cedula).isPresent();
    }

    private String generarCodigo() {
        int codigo = (int)(Math.random() * 1_000_000); // Valor entre 0 y 999999
        return String.format("%06d", codigo); // Rellena con ceros a la izquierda si es necesario
    }
}

