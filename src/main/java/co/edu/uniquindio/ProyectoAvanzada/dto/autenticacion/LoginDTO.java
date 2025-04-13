package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank(message = "No puede ingresar campos vacios.")
        @Email(message = "El email no tiene un formato válido.")
        String email,

        @NotBlank(message = "No puede ingresar campos vacios.")
        String password
) {
}
