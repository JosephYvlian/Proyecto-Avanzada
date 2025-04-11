package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;


import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.UsuarioMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    @Override
    public void crear(CrearUsuarioDTO cuenta) {
        if (siExisteEmail(cuenta.email())) {
            throw new RuntimeException("El correo ya está en uso");
        }

        Usuario usuario = usuarioMapper.toDocument(cuenta);
        usuarioRepo.save(usuario);
    }

    @Override
    public void editar(EditarUsuarioDTO cuenta) {
        Usuario usuario = usuarioRepo.buscarUsuarioPorCorreo(cuenta.email()).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        if (!usuario.getEmail().equals(cuenta.email()) && siExisteEmail(cuenta.email())) {
            throw new RuntimeException("El nuevo correo ya está en uso");
        }

        usuarioMapper.actualizarUsuarioDesdeDTO(usuario, cuenta);
        usuarioRepo.save(usuario);
    }

    @Override
    public void eliminar(String id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        usuarioRepo.delete(usuario);
    }

    @Override
    public UsuarioDTO obtener(String id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario no existe");
        }

        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<UsuarioDTO> listaDTO = new java.util.ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = usuarioMapper.toDTO(usuario);
            listaDTO.add(dto);
        }

        return listaDTO;
    }

    private boolean siExisteEmail(String email) {
        return usuarioRepo.buscarUsuarioPorCorreo(email).isPresent();
    }
}

