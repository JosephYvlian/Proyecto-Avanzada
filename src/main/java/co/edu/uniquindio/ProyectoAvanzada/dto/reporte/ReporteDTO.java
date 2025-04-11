package co.edu.uniquindio.ProyectoAvanzada.dto.reporte;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ciudad;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record ReporteDTO(
        String codigoReporte,
        String titulo,
        Categoria categoria,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        List<Comentario> comentarios
) {
}
