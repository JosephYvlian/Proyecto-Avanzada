package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import java.util.List;

public record ComentarioDTO(
    String idComentario,
    String mensaje,
    List<String> imagenes
) {
}
