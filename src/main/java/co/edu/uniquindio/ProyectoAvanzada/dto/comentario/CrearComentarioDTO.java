package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        @NotBlank(message = "El idUsuario no puede estar vacio") ObjectId idUsuario,
        @NotBlank(message = "El mensaje no puede estar vacio") String mensaje
) {
}
