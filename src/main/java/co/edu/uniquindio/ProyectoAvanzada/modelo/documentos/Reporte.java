package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ciudad;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document("reportes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Reporte {

    @Id
    private String idReporte;

    private String titulo;
    private ObjectId categoria;
    private Ciudad ciudad;
    private String descripcion;
    private Ubicacion ubicacion;
    private LocalDateTime fecha;
    private List<String> imagenes;

}