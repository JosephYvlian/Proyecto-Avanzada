package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {


    @Override
    public void crearComentario(String idReporte, CrearComentarioDTO comentario) {

    }

    @Override
    public List<ComentarioDTO> listarComentarios(String idReporte) {
        return List.of();
    }

    @Override
    public void eliminarComentario(String idReporte) {

    }
}
