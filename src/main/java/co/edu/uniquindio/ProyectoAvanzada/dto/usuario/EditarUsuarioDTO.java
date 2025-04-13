package co.edu.uniquindio.ProyectoAvanzada.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarUsuarioDTO(
        @NotBlank(message = "No puede ingresar su nombre vacio.") @Length(min = 3, message = "Escriba un nombre válido.") String nombre,
        @NotBlank(message = "No puede ingresar un telefono vacio.") @Length(min = 7, max = 10, message = "Ingrese un número de telefono valido.") String telefono,
        @NotBlank(message = "No puede ingresar una ciudad vacia.") @Length(min = 3) String ciudad,
        @NotBlank(message = "No puede ingresar su dirección vacia.") @Length(min = 10) String direccion
) {
}