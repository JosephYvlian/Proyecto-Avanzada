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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    private final ComentarioMapper comentarioMapper;

    @Override
    public String crearComentario(CrearComentarioDTO dto) {
        Comentario comentario = comentarioMapper.toDocument(dto);
        return comentarioRepo.save(comentario).getIdComentario();
    }

    @Override
    public List<ComentarioDTO> listarComentariosPorReporte(String idReporte) {
        return comentarioRepo.buscarComentarioPorId(idReporte)
                .stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminarComentario(String id) {
        comentarioRepo.deleteById(id);
    }
}