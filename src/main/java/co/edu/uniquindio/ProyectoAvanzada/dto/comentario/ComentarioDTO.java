package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.time.LocalDateTime;

public record ComentarioDTO(
    String idUsuario,
    String idReporte,
    String mensaje,
    LocalDateTime fecha
) {
}
