package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.ReporteMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.seguridad.JWTUtils;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

    private final ReporteRepo reporteRepo;
    private final UsuarioRepo usuarioRepo;
    private final ReporteMapper reporteMapper;
    private final EmailServicio emailServicio;
    private final JWTUtils jWTUtils;

    @Override
    public void crearReporte(CrearReporteDTO dto) throws Exception {
        if (dto == null) {
            throw new Exception("El reporte no puede ser nulo");
        }

        Reporte reporte = reporteMapper.toDocument(dto);

        if (dto.usuarioId() != null) {
            usuarioRepo.findById(dto.usuarioId()).ifPresent(usuario -> reporte.setUsuarioId(usuario.getIdUsuario()));
        }

        reporte.setEstado(EstadoReporte.PENDIENTE);
        reporteRepo.save(reporte);

        emailServicio.enviarCorreo(new EmailDTO("Nuevo Reporte", "Se ha creado un nuevo reporte con ID: " + reporte.getIdReporte(), dto.usuarioId().toString()));
    }

    @Override
    public ReporteDTO obtenerReporte(String idReporte) {
        ObjectId objectId = new ObjectId(idReporte);

        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("No existe un reporte con el id: " + idReporte));

        return reporteMapper.toDTO(reporte);

    }

    @Override
    public List<ReporteDTO> listarReporte() {

        String idUsuario = jWTUtils.getUsuarioIdFromToken("token");

        ObjectId objectId = new ObjectId();
        List<Reporte> reportes = reporteRepo.findByUsuarioId(objectId);

        return reportes.stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarReporte(String idReporte) {
        ObjectId objectId = new ObjectId(idReporte);
        Reporte reporte = reporteRepo.findById(objectId).orElse(null);

        if (reporte == null) {
            throw new RuntimeException("No existe un reporte con el id: " + idReporte);
        }

        reporte.setEstado(EstadoReporte.ELIMINADO);
        reporteRepo.save(reporte);
    }

    @Override
    public void editarReporte(String idReporte, EditarReporteDTO dto) {
        ObjectId objectId = new ObjectId(idReporte);
        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("No existe un reporte con este id."));
        String idUsuario = jWTUtils.getUsuarioIdFromToken("token");

        reporteMapper.EditarReporteDTO(dto, reporte);
        reporteRepo.save(reporte);
    }

    @Override
    public void marcarReporte(String idReporte) {
        ObjectId objectId = new ObjectId(idReporte);
        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("No existe un reporte con este id."));
        if (reporte.getEstado() == EstadoReporte.PENDIENTE) {
            reporte.setEstado(EstadoReporte.VERIFICADO);
            reporteRepo.save(reporte);
        } else {
            throw new RuntimeException("El reporte ya ha sido marcado o eliminado.");
        }
    }
}