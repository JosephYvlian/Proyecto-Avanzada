package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;

import java.util.List;

public interface NotificacionServicio {

    void marcarComoLeida(String idNotificacion);

    void marcarTodasComoLeidas(String idUsuario);

    List<NotificacionDTO> listarNotificacionesUsuario(String idUsuario);

    List<Notificacion> listarPorEstado(String idUsuario, boolean leidas);

}
