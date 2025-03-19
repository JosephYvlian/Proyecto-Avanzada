package co.edu.uniquindio.ProyectoAvanzada.modelo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reporte {
    private String titulo;
    private Categoria categoria;
    private String descripcion;
    private Ubicacion ubicacion;
    private String imagen;
}