package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import jakarta.validation.constraints.NotBlank;

public record MarcarReporteDTO(
        @NotBlank String estado
) {
}