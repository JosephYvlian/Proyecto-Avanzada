package co.edu.uniquindio.ProyectoAvanzada.modelo.vo;

import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class HistorialReporte {

    private String observacion;
    private LocalDateTime fecha;
    private ObjectId idCliente;
    private EstadoReporte estado;
}
