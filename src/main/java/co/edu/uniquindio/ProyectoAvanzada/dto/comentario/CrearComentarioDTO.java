package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.util.List;

public record CrearComentarioDTO(
        String mensaje,
        List<String> imagenes
) {
}
