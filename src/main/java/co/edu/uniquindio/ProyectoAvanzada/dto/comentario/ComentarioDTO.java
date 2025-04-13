package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.time.LocalDateTime;
import java.util.List;

public record ComentarioDTO(
    String idComentario,
    String idUsuario,
    String mensaje,
    LocalDateTime fecha
) {
}
