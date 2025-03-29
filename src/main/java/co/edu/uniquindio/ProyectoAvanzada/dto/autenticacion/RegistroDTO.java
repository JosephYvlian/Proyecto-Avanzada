package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ciudad;

public record RegistroDTO(
        String nombre,
        String email,
        String numTelefonico,
        Ciudad ciudad,
        String direccion,
        String password

) {
}
