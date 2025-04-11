package co.edu.uniquindio.ProyectoAvanzada.test.java.RepositoriosTest;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ComentarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ReporteRepo reporteRepo;

    // TEST CRUD -------------------------------------------------------------------------------------------------------

    // Test para registrar un nuevo comentario en la base de datos
    @Test
    public void registrarComentarioTest() {
        ObjectId idUsuario = new ObjectId("67f7f88d4e4e7708d451c1c7"); // ID de usuario existente
        Usuario usuario = usuarioRepo.findById(String.valueOf(idUsuario))
                .orElseThrow(() -> new IllegalArgumentException("El Usuario no existe"));

        ObjectId idReporte = new ObjectId("67f7f6e52c5b8614d14933e1"); // ID de reporte existente
        Reporte reporte = reporteRepo.findById(String.valueOf(idReporte))
                .orElseThrow(() -> new IllegalArgumentException("El Reporte no existe"));

        Comentario comentario = Comentario.builder()
                .idUsuario(idUsuario)
                .idReporte(idReporte)
                .comentario("Esto es muy grave, ojalá lo atrapen")
                .fecha(LocalDateTime.now())
                .build();

        Comentario guardado = comentarioRepo.save(comentario);
        assertNotNull(guardado);
    }

    // Test para listar todos los comentarios y verificar que exista al menos uno
    @Test
    public void listarComentariosTest() {
        List<Comentario> lista = comentarioRepo.findAll();
        lista.forEach(System.out::println);
        assertEquals(1, lista.size()); // Ajusta el número según la cantidad esperada
    }

    // Test para actualizar el contenido de un comentario existente
    @Test
    public void actualizarComentarioTest() {
        String id = "67f733b80983d409de913772"; // ID del comentario a actualizar

        Comentario comentario = comentarioRepo.findById(id).orElseThrow();
        comentario.setComentario("Comentario actualizado");
        comentarioRepo.save(comentario);

        Comentario actualizado = comentarioRepo.findById(id).orElseThrow();
        assertEquals("Comentario actualizado", actualizado.getComentario());
    }

    // Test para eliminar un comentario por ID y verificar que ya no exista
    @Test
    public void eliminarComentarioTest() {
        String id = "67f733b80983d409de913772"; // ID del comentario a eliminar

        comentarioRepo.deleteById(id);

        Comentario eliminado = comentarioRepo.findById(id).orElse(null);
        assertNull(eliminado);
    }

    // TEST REPO -------------------------------------------------------------------------------------------------------

    // Test: Buscar todos los comentarios hechos por un usuario
    @Test
    public void buscarPorUsuarioTest() {
        ObjectId idUsuario = new ObjectId("67f730e7d5b9332193cc1922");
        List<Comentario> comentarios = comentarioRepo.buscarPorUsuario(idUsuario.toHexString());
        comentarios.forEach(System.out::println);
        assertFalse(comentarios.isEmpty());
    }

    // Test: Buscar todos los comentarios asociados a un reporte
    @Test
    public void buscarPorReporteTest() {
        ObjectId idReporte = new ObjectId("67f6f059f5144a4eddc02b4f");
        List<Comentario> comentarios = comentarioRepo.buscarPorReporte(idReporte.toHexString());
        comentarios.forEach(System.out::println);
        assertFalse(comentarios.isEmpty());
    }
}