package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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

    private String idUsuario;
    private String idReporte;
    private String tituloNotificacion;
    private String mensaje;
    private boolean leida;
    private LocalDateTime fecha;
}
