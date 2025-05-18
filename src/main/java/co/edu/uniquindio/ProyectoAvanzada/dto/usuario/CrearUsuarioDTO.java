package co.edu.uniquindio.ProyectoAvanzada.dto.usuario;

import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Schema
public record CrearUsuarioDTO(
        Rol rol,
        @NotBlank(message = "No puede ingresar su nombre vacio.") @Length(min = 3, message = "Escriba un nombre válido.") String nombre,
        @NotBlank(message = "No puede ingresar un telefono vacio.") @Length(min = 7, max = 10, message = "Ingrese un número de telefono valido.") String telefono,
        @NotBlank(message = "No puede ingresar una ciudad vacia.") @Length(min = 3) String ciudad,
        @NotBlank(message = "No puede ingresar su dirección vacia.") @Length(min = 10) String direccion,
        @NotBlank(message = "No puede ingresar su email vacio.") @Length(min = 3, max = 50) @Email(message = "Ingrese un formato de email valido.") String email,
        @NotBlank(message = "No puede ingresar su contraseña vacia.") @Length(min = 8, message = "Su contraseña debe tener minimo 8 caracteres.") String password,
        EstadoCuenta estadoCuenta,
        LocalDateTime fechaRegistro
) {
}