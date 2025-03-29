package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;

public record MarcarReporteDTO(
        String mensaje,
        Reporte reporte
) {
}
