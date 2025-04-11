package co.edu.uniquindio.ProyectoAvanzada.modelo.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodigoValidacion {
    private String codigo;
    private LocalDateTime fecha;
}
