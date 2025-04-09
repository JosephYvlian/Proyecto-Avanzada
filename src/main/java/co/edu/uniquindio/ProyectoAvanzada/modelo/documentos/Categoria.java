package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Categoria {
    @Id
    private String nombre;
    private String descripcion;
}
