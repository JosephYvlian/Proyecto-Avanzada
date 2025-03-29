package co.edu.uniquindio.ProyectoAvanzada.dto.usuario;

import java.util.List;

public record EditarUsuarioDTO(
        String nombre,
        String email,
        List<String> numTelefonos,
        String ciudad,
        String direccion,
        String contrasena
) {
}
