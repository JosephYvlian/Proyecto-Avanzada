package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.ReporteMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

    private final ReporteRepo reporteRepo;
    private final ReporteMapper reporteMapper;

    @Override
    public void crearReporte(CrearReporteDTO reporteDTO) {
        Reporte reporte = reporteMapper.toDocument(reporteDTO);
        reporte.setEstado(EstadoReporte.PENDIENTE);
        reporte.setCodigoReporte(reporte.getCodigoReporte() + 1);
        reporteRepo.save(reporte);
    }

    @Override
    public ReporteDTO obtenerReporte(String idReporte) {
        Reporte reporte = reporteRepo.buscarReportePorCodigo(idReporte).orElse(null);

        if (reporte == null) {
            throw new RuntimeException("No existe un reporte con el id: " + idReporte);
        }

        return reporteMapper.toDTO(reporte);

    }

    @Override
    public List<ReporteDTO> listarReporte() {

        List<Reporte> reportes = reporteRepo.findAll();
        List<ReporteDTO> listaDTO = new ArrayList<>();

        for (Reporte reporte : reportes) {
            ReporteDTO dto = reporteMapper.toDTO(reporte);
            listaDTO.add(dto);
        }

        return listaDTO;
    }

    @Override
    public void eliminarReporte(String idReporte) {
        Reporte reporte = reporteRepo.buscarReportePorCodigo(idReporte).orElse(null);

        if (reporte == null) {
            throw new RuntimeException("No existe un reporte con el id: " + idReporte);
        }

        reporte.setEstado(EstadoReporte.ELIMINADO);
        reporteRepo.save(reporte);
    }

    @Override
    public void editarReporte(String idReporte, EditarReporteDTO dto) {
        Reporte reporte = reporteRepo.buscarReportePorCodigo(idReporte).orElse(null);

        if (reporte == null || !siExisteReporte(idReporte)) {
            throw new RuntimeException("No existe un reporte con este id.");
        }


        reporteMapper.actualizarReporteDesdeDTO(reporte, dto);
        reporteRepo.save(reporte);
    }

    @Override
    public void marcarReporte(String idReporte) {
        Reporte reporte = reporteRepo.buscarReportePorCodigo(idReporte).orElse(null);
        if (reporte == null) {
            throw new RuntimeException("No existe un reporte con el id: " + idReporte);

        }

        reporte.setVotosImportancia(reporte.getVotosImportancia() + 1);
        reporteRepo.save(reporte);
    }

    private boolean siExisteReporte(String idReporte) {
        return reporteRepo.buscarReportePorCodigo(idReporte).isPresent();
    }
}