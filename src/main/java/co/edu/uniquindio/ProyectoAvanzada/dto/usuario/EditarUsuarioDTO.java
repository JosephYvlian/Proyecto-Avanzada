package co.edu.uniquindio.ProyectoAvanzada.dto.usuario;

import java.util.List;

public record EditarUsuarioDTO(
        String id,
        String nombre,
        String email,
        String numTelefono,
        String ciudad,
        String direccion,
        String contrasena
) {
}
