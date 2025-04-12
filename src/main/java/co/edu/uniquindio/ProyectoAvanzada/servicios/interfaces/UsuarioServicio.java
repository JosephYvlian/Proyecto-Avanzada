package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.CodVerificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.LoginDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.RegistroDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioServicio {
    void crear(@Valid RegistroDTO cuenta);

    void editar(@Valid EditarUsuarioDTO cuenta, String cedula);

    void eliminar(String id);

    void verificarUsuario(CodVerificacionDTO codVerificacion);

    TokenDTO login(LoginDTO login) throws Exception;

    UsuarioDTO obtener(String id);

    List<UsuarioDTO> listarTodos();
}