package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

public record EmailDTO(
        String asunto,
        String cuerpo,
        String correoDestino
) {
}
