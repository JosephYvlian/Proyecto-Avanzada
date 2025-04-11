package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.ActivarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.CambiarContrasenaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.CodVerificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface UsuarioServicio {

    void crear(@Valid CrearUsuarioDTO cuenta);
    void editar(@Valid EditarUsuarioDTO cuenta);
    void eliminar(String id);
    UsuarioDTO obtener(String id);
    List<UsuarioDTO> listarTodos();
    void enviarCodigoVerificacion(CodVerificacionDTO codVerificacionDTO) throws Exception;
    void cambiarPassword(CambiarContrasenaDTO cambiarContrasenaDTO) throws Exception;
    void setActivarUsuario(ActivarUsuarioDTO activarUsuarioDTO) throws Exception;
}
