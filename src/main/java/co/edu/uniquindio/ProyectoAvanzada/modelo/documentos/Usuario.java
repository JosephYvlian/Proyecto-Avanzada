package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;


import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document("usuarios")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private ObjectId idUsuario;

    private String nombre;
    private String ciudad;
    private String telefono;
    private String direccion;
    private String email;
    private String password;
    private Rol rol;
    private EstadoCuenta estadoCuenta;
    private LocalDateTime fechaRegistro;
    private CodigoValidacion codigoValidacion;

}