package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioServicio {
    void crear(@Valid CrearUsuarioDTO cuenta);

    void editar(@Valid EditarUsuarioDTO cuenta, String cedula);

    void eliminar(String id);

    void verificarUsuario(CodVerificacionDTO codVerificacion);

    void recuperarUsuario(String email);

    void recuperarContrasena(RecuperarContrasenaDTO recuperarContrasena);

    void actualizarContrasena(ActualizarContrasenaDTO actualizarContrasena);

    UsuarioDTO obtener(String id);

    List<UsuarioDTO> listarTodos();
}