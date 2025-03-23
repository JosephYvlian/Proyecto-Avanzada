package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;


import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("usuarios")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    private String id;

    private Rol rol;
    private String nombre;
    private String ciudad;
    private List<String> telefonos;
    private String direccion;
    private String correo;


}
