package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.util.List;

public record CrearComentarioDTO(
        String reporteId,
        String usuarioId,
        String mensaje

) {
}
