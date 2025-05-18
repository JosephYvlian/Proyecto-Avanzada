package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

import java.time.LocalDateTime;

public record NotificacionDTO(
        String titulo,
        String mensaje,
        boolean leida,
        LocalDateTime fecha
) {
}
