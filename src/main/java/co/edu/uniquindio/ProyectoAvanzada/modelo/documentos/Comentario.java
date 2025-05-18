package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.time.LocalDateTime;

@Document("comentarios")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId idComentario;

    private ObjectId usuarioId;
    private ObjectId reporteId;
    private String mensaje;
    private LocalDateTime fecha;

}
