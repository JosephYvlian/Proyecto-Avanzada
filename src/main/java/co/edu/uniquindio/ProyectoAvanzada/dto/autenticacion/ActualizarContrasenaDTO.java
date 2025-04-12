package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

public record ActualizarContrasenaDTO(
        String email,
        String contrasenaActual,
        String contrasenaNueva
) {
}
