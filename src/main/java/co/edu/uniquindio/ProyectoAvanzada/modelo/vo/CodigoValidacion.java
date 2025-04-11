package co.edu.uniquindio.ProyectoAvanzada.modelo.vo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("codigos")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodigoValidacion {
    @Id
    private String codigo;

    private String email;
    private LocalDateTime fecha;
}
