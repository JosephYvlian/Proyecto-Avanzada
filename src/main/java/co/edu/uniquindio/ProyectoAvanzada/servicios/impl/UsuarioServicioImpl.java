package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;


import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    @Override
    public void crear(CrearUsuarioDTO cuenta) {

    }

    @Override
    public void editar(EditarUsuarioDTO cuenta) {

    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public UsuarioDTO obtener(String id) {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        return List.of();
    }
    //TODO implementar todos los métodos de la interfaz
}

