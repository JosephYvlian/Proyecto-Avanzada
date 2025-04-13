package co.edu.uniquindio.ProyectoAvanzada.modelo.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ubicacion {
    private double latitud;
    private double longitud;
    private String lugar;

}
