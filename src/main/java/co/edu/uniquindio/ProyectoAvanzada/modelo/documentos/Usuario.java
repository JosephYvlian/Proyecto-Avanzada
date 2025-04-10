package co.edu.uniquindio.ProyectoAvanzada.modelo.documentos;


import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document("usuarios")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Usuario {

    @Id
    private String idUsuario;

    private Rol rol;
    private String nombre;
    private String ciudad;
    private String telefono;
    private String direccion;
    private String email;
    private String password;
    private EstadoCuenta estadoCuenta;
    private Date fechaRegistro;
}
