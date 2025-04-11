package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;


import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
@EqualsAndHashCode
public class Usuario {

    @Id
    private String idUsuario;

    @Indexed(unique = true)
    private String cedula;

    private Rol rol;
    private String nombre;
    private String ciudad;
    private String telefono;
    private String direccion;

    @Indexed(unique = true)
    private String email;

    private String password;
    private EstadoCuenta estadoCuenta;
    private LocalDateTime fechaRegistro;
    private CodigoValidacion codigoValidacion;


}
