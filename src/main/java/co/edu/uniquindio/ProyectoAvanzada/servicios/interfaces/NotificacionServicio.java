package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;

import java.util.List;

public interface NotificacionServicio {

    void enviarNotificacion(EnviarNotificacionDTO notificacionDTO);
    void marcarComoLeido(String idNotificacion);
    List<NotificacionDTO> listarNotificaciones(String idUsuario);
    List<NotificacionDTO> listarNotificacionesNoLeidas(String idUsuario);
    List<NotificacionDTO> listarNotificacionesLeidas(String idUsuario);

}
