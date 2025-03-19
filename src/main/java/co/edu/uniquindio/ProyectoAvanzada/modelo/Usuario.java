package co.edu.uniquindio.ProyectoAvanzada.modelo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario {

    @Id
    private String id;

    private String nombre;
    private String ciudad;
    private List<String> telefonos;
    private String direccion;
    private String correo;


}
