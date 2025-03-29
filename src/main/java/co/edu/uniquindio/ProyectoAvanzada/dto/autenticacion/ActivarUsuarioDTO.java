package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActivarUsuarioDTO(
        @NotBlank @Email @Length(max=50) String email,
        @NotBlank @Length(max=6) String codigo
) {
}
