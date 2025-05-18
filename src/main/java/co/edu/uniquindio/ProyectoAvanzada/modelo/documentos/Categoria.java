package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId idCategoria;

    private String nombre;
    private String descripcion;
    private String imagen;
}