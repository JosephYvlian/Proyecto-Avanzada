package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;

import java.util.List;

public interface ComentarioServicio {

    void eliminarComentario(String idComentario, String idReporte);

    void crearComentario(CrearComentarioDTO dto);

    List<ComentarioDTO> listarComentariosDeReporte(String idReporte) throws Exception;
}