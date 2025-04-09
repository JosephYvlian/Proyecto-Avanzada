package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;

import java.util.List;

public record NotificacionDTO(
        String idNotificacion,
        String mensaje,
        Reporte reporte,
        List<String> imagen
) {
}
