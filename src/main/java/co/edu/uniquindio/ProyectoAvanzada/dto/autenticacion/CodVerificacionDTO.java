package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CodVerificacionDTO(
        @NotBlank(message = "No pueden haber campos vacios.") @Email(message = "El email no tiene un formato válido.") String email,
        @NotBlank(message = "El codigo no puede ser vacio.") @Length(max = 6, message = "El codigo es de 6 digitos.") String codigo
) {
}
