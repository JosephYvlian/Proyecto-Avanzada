package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RecuperarContrasenaDTO(
        @NotBlank(message = "El email no puede ser vacio.") @Email(message = "Ingrese un email valido.") String email,
        @NotBlank(message = "El codigo no puede ser vacio.") @Length(max=6, message = "El codigo es de 6 digitos.") String codigo,
        @NotBlank(message = "No puede ingresar una contraseña vacia.") @Length(min=8, message = "La contraseña debe tener minimo 8 caracteres.") String nuevaContrasena
) {}
