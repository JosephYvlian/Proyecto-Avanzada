package co.edu.uniquindio.ProyectoAvanzada.dto.notificacion;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;

import java.util.List;

public record EnviarNotificacionDTO(
        String mensaje,
        Reporte reporte,
        List<String> imagen
) {
}
