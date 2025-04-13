package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.ComentarioMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ComentarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final ReporteRepo reporteRepo;
    private final ComentarioMapper comentarioMapper;

    @Override
    public void crearComentario(CrearComentarioDTO dto) throws Exception {
        // 1. Crear y guardar el comentario en la colección de comentarios
        Comentario comentario = Comentario.builder()
                .comentario(dto.mensaje())
                .fecha(LocalDateTime.now())
                .idUsuario(dto.idUsuario())
                .idReporte(dto.idReporte())
                .build();

        comentario = comentarioRepo.save(comentario); // guardar y obtener ID generado

        // 2. Buscar el reporte correspondiente
        Reporte reporte = reporteRepo.findById(dto.idReporte())
                .orElseThrow(() -> new Exception("Reporte no encontrado"));

        // 3. Crear una copia del comentario sin idReporte
        Comentario comentarioEmbebido = Comentario.builder()
                .idComentario(comentario.getIdComentario())
                .idUsuario(comentario.getIdUsuario())
                .comentario(comentario.getComentario())
                .fecha(comentario.getFecha())
                .build();

        // 4. Agregar el comentario embebido al reporte
        if (reporte.getComentarios() == null) {
            reporte.setComentarios(new ArrayList<>());
        }

        reporte.getComentarios().add(comentarioEmbebido);
        reporteRepo.save(reporte);
    }


    @Override
    public List<ComentarioDTO> listarComentariosDeReporte(String idReporte) throws Exception {

        List<Comentario> comentarios = comentarioRepo.buscarComentariosPorReporte(idReporte);
        List<ComentarioDTO> comentarioDTOs = new ArrayList<>();

        for (Comentario comentario : comentarios) {
            ComentarioDTO dto = comentarioMapper.toDTO(comentario);
            comentarioDTOs.add(dto);
        }

        return comentarioDTOs;
    }


    @Override
    public void eliminarComentario(String idReporte, String idComentario) {
        Reporte reporte = reporteRepo.findById(idReporte)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        boolean eliminado = false;

        List<Comentario> comentariosActualizados = new ArrayList<>();

        for (Comentario comentario : reporte.getComentarios()) {
            if (comentario.getIdComentario() != null && comentario.getIdComentario().equals(idComentario)) {
                eliminado = true;
            } else {
                comentariosActualizados.add(comentario);
            }
        }

        if (!eliminado) {
            throw new RuntimeException("El comentario no existe en el reporte");
        }

        reporte.setComentarios(comentariosActualizados);
        reporteRepo.save(reporte);

        // También eliminar de la colección de comentarios, si aplica
        comentarioRepo.deleteById(idComentario);
    }


}