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
    private double longitud;
    private double latitud;
    private String lugar;

}
