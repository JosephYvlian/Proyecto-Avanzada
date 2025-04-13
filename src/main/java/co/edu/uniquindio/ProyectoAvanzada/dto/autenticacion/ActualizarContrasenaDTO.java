package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizarContrasenaDTO(
        @NotBlank(message = "El email no puede ser vacio.") @Email(message = "Ingrese un email valido.") String email,
        @NotBlank(message = "No puede ingresar una contraseña vacia.") String contrasenaActual,
        @NotBlank(message = "No puede ingresar una contraseña vacia.") @Length(min=8, message = "La contraseña debe tener minimo 8 caracteres.") String contrasenaNueva
) {
}
