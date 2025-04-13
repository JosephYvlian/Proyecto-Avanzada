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

    private static String idComentarioCreado = "67fc1e821543e15aa3044c6c";

    private final String idReporte = "67fc1c2c29e5624bdf6582d4";
    private final String idUsuario = "67fc17a7dbc7f76054be2fd8";

    @Test
    public void testCrearComentario() {
        CrearComentarioDTO dto = new CrearComentarioDTO(
                idReporte,
                idUsuario, //
                "Comentario Nuevo",
                LocalDateTime.now()
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
