package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.time.LocalDateTime;

@Document("comentarios")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comentario {

    @Id
    private String idComentario;

    private ObjectId idUsuario;
    private ObjectId idReporte;
    private String comentario;
    private LocalDateTime fechaComentario;

}
