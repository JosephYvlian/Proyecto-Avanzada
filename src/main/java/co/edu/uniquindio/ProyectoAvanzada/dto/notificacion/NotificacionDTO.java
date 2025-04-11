package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;

import java.time.LocalDateTime;
import java.util.List;

public record NotificacionDTO(
        String titulo,
        String mensaje,
        boolean estado,
        LocalDateTime fecha
) {
}
