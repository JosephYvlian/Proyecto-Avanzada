package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;

import java.util.List;

public record CrearReporteDTO(
        String titulo,
        Categoria categoria,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes
) {
}
