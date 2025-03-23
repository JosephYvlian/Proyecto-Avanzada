package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notificaciones")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    private String idNotificacion;

    private ObjectId idUsuario;
    private ObjectId idReporte;
    private String tituloNotificacion;
    private String mensaje;
    private boolean estado;
}
