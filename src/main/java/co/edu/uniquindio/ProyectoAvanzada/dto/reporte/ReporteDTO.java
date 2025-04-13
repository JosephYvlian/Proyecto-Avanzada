package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;

import java.util.List;

public record ReporteDTO(
        String idReporte,
        String titulo,
        Categoria categoria,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        List<ComentarioDTO> comentarios,
        Integer votosImportancia
) {
}
