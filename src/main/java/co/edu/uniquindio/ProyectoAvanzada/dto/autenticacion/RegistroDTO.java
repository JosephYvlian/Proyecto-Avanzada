package co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion;

import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Schema
public record RegistroDTO(
        Rol rol,
        @NotBlank @Length(max = 100) String nombre,
        @Length(max = 10) String telefono,
        @NotBlank @Length(max = 100) String ciudad,
        @NotBlank @Length(max = 100) String direccion,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password,
        EstadoCuenta estadoCuenta,
        LocalDateTime fechaRegistro
) {
}