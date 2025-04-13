package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

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

    private String idUsuario;
    private String idReporte;
    private String comentario;
    private LocalDateTime fecha;

}
