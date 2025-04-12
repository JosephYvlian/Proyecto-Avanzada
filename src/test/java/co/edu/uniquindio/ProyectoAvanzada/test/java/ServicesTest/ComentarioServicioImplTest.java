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

    private static String idComentarioCreado = "67fab14f823899624dab0824";

    // Puedes ajustar esto al ID de un reporte real de tu base de datos de pruebas
    private final String idReporte = "67f9f81d8158242db1ee7d02";
    private final String idUsuario = "67faad20fc3a656dcc8f2ead";

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
