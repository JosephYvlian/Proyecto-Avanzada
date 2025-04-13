package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CrearReporteDTO(
        @NotBlank(message = "El titulo no puede estar vacio") String titulo,
        @NotBlank(message = "La categoria no puede estar vacia") Categoria categoria,
        @NotBlank(message = "La descripcion no puede estar vacia") String descripcion,
        @NotBlank(message = "La ubicacion no puede estar vacia") Ubicacion ubicacion,
        EstadoReporte estado,
        List<String> imagenes,
        Integer votosImportancia
) {
}