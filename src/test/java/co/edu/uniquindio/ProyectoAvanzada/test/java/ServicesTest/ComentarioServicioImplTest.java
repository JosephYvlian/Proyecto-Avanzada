package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ComentarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComentarioServicioImplTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private ComentarioRepo comentarioRepo;

    private static String idComentarioCreado = "67fb21e3a23c0544490d1732";

    private final String idReporte = "67fb1be53880426f7563db80";
    private final String idUsuario = "67fb13ce89d0bc1871f9cede";

    @Test
    public void testCrearComentario() {
        CrearComentarioDTO dto = new CrearComentarioDTO(
                idReporte,
                idUsuario, //
                "Comentario Nuevo"
        );

        idComentarioCreado = comentarioServicio.crearComentario(dto);

        Comentario comentario = comentarioRepo.findById(idComentarioCreado).orElse(null);
        assertNotNull(comentario);
        assertEquals("Comentario Nuevo", comentario.getComentario());
    }

    @Test
    public void testEliminarComentario() {
        comentarioServicio.eliminarComentario(idComentarioCreado);
        boolean existe = comentarioRepo.findById(idComentarioCreado).isPresent();
        assertFalse(existe);
    }
}
