package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

public record EnviarNotificacionDTO(
        String idUsuario,
        String idReporte,
        String titulo,
        String mensaje
) {
}
