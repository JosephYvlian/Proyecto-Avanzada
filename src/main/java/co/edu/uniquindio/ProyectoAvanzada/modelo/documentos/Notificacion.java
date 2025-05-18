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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId idNotificacion;

    private ObjectId usuarioId;
    private ObjectId reporteId;
    private String tituloNotificacion;
    private String mensaje;
    private boolean leida;
    private LocalDateTime fecha;
}
