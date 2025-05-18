package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Ciudad;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.HistorialReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("reportes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reporte {

    @Id
    @EqualsAndHashCode.Include
    private String idReporte;

    private String titulo;
    private ObjectId categoriaId;
    private Ciudad ciudad;
    private String descripcion;
    private Ubicacion ubicacion;
    private LocalDateTime fecha;
    private ObjectId usuarioId;
    private List<String> imagenes;
    private List<Comentario> comentarios;
    private List<HistorialReporte>  historial;
    private EstadoReporte estado;
    private Integer votosImportancia;

}