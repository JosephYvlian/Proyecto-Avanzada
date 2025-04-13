package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        String idReporte,
        String idUsuario,
        String mensaje,
        LocalDateTime fecha

) {
}
