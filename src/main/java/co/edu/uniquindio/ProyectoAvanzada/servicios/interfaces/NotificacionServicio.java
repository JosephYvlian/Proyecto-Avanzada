package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;

public interface NotificacionServicio {

    void enviarNotificacion(NotificacionDTO notificacionDTO, String idUsuario);

    void marcarComoLeido(String idNotificacion);
}
