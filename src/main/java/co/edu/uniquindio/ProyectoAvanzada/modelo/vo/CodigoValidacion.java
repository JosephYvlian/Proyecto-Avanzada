package co.edu.uniquindio.ProyectoAvanzada.modelo.vo;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CodigoValidacion {
    private String codigo;
    private Date fecha;
}
