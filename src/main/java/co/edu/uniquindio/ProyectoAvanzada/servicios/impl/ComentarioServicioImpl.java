package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.ComentarioMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ComentarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final ComentarioRepo comentarioRepo;
    private final ReporteRepo reporteRepo;
    private final ComentarioMapper comentarioMapper;
    private final EmailServicio emailServicio;

    @Override
    public void crearComentario(String idReporte, CrearComentarioDTO dto) throws Exception {

        if (dto.mensaje() == null || dto.mensaje().isBlank() ) {
            throw new RuntimeException("El mensaje no puede estar vacío");
        }

        ObjectId objectId = new ObjectId(idReporte);
        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        Comentario comentario = comentarioMapper.toDocument(dto);
        comentario.setIdComentario(new ObjectId());
        comentario.setFecha(LocalDateTime.now());
        comentario.setReporteId(objectId);
        comentarioRepo.save(comentario);

        Usuario usuario = usuarioRepo.findById(reporte.getUsuarioId()  )
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String asunto = "A new NEX comment has been added to your report";
        String cuerpo = dto.mensaje();
        String destinario = usuario.getEmail();

        emailServicio.enviarCorreo(new EmailDTO(asunto, cuerpo, destinario));
    }


    @Override
    public List<ComentarioDTO> listarComentariosDeReporte(String idReporte) throws Exception {

        ObjectId objectId = new ObjectId(idReporte);

        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        List<Comentario> comentarios = comentarioRepo.findByReporteId(objectId);

        if (comentarios.isEmpty()) {
            throw new RuntimeException("No hay comentarios para este reporte");
        }

        return  comentarios.stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }


    @Override
    public void eliminarComentario(String idReporte, String idComentario) {
        ObjectId objectId = new ObjectId(idReporte);
        Reporte reporte = reporteRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        Comentario comentario = comentarioRepo.findById(new ObjectId(idComentario))
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        if (!comentario.getReporteId().equals(reporte.getIdReporte())) {
            throw new RuntimeException("El comentario no pertenece a este reporte");
        }

        comentarioRepo.delete(comentario);
    }


}