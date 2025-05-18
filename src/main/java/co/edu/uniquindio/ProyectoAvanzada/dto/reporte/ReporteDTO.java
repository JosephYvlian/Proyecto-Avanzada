package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.HistorialReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;

import java.util.List;

public record ReporteDTO(
        String idReporte,
        String titulo,
        String categoria,
        EstadoReporte estadoReporte,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        String usuario,
        Integer votosImportancia,
        List<HistorialReporte> historial
) {
}
