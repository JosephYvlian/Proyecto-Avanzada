package co.edu.uniquindio.ProyectoAvanzada.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearCategoriaDTO(
        @NotBlank (message = "El nombre de la categoria no puede ser vacia.") @Length(min=3) String nombre,
        @NotBlank (message = "La descripcion de la categoria no puede ser vacia.") String descripcion
) {
}
