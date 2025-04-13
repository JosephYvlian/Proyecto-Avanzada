package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

import jakarta.validation.constraints.NotBlank;

public record EnviarNotificacionDTO(
        @NotBlank(message = "El idUsuario no puede estar vacio") String idUsuario,
        @NotBlank(message = "El idReporte no puede estar vacio") String idReporte,
        @NotBlank(message = "El tituto no puede estar vacio") String titulo,
        @NotBlank(message = "El mensaje no puede estar vacio") String mensaje
) {
}
