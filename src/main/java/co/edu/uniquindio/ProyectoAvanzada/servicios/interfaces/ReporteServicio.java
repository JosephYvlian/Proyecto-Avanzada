package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import java.util.List;

public interface ReporteServicio {

    void crearReporte(CrearReporteDTO reporte) throws Exception;

    void editarReporte(String idReporte, EditarReporteDTO reporte);

    void eliminarReporte(String idReporte);

    void marcarReporte(String idReporte);

    ReporteDTO obtenerReporte(String idReporte);

    List<ReporteDTO> listarReporte();
}
