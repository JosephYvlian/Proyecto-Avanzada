package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.time.LocalDateTime;

public record ComentarioDTO(
    String idComentario,
    String idUsuario,
    String idReporte,
    String mensaje,
    LocalDateTime fecha
) {
}
