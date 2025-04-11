package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambiarContrasenaDTO(
        @NotBlank @Email @Length(max=50) String email,
        @NotBlank @Length(max=6) String codigo,
        @NotBlank @Length(max=50) String actualContrasena,
        @NotBlank @Length(max=50) String nuevaContrasena
) {}
