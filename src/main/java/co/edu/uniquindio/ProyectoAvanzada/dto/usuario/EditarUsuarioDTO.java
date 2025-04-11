package co.edu.uniquindio.ProyectoAvanzada.dto.usuario;

public record EditarUsuarioDTO(
        String nombre,
        String email,
        String numTelefono,
        String ciudad,
        String direccion,
        String contrasena
) {
}
