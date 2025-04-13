package co.edu.uniquindio.ProyectoAvanzada.dto.comentario;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        @NotBlank(message = "El idReporte no puede estar vacio") String idReporte,
        @NotBlank(message = "El idUsuario no puede estar vacio") String idUsuario,
        @NotBlank(message = "El mensaje no puede estar vacio") String mensaje,
        LocalDateTime fecha

) {
}
