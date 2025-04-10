package co.edu.uniquindio.ProyectoAvanzada.test;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificacionTest {

    @Autowired
    private NotificacionRepo notificacionRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ReporteRepo reporteRepo;

    // TEST CRUD -------------------------------------------------------------------------------------------------------

    // Test para registrar una nueva notificación en la base de datos
    @Test
    public void registrarNotificacionTest() {
        ObjectId idUsuario = new ObjectId("67f7f88d4e4e7708d451c1c7"); // ID de un usuario existente
        Usuario usuario = usuarioRepo.findById(String.valueOf(idUsuario))
                .orElseThrow(() -> new IllegalArgumentException("El Usuario no existe"));

        ObjectId idReporte = new ObjectId("67f7f6e52c5b8614d14933e1"); // ID de un reporte existente
        Reporte reporte = reporteRepo.findById(String.valueOf(idReporte))
                .orElseThrow(() -> new IllegalArgumentException("El Reporte no existe"));

        Notificacion notificacion = Notificacion.builder()
                .idUsuario(idUsuario)
                .idReporte(idReporte)
                .tituloNotificacion("Nueva alerta")
                .mensaje("Se ha registrado una actividad sospechosa cerca de tu ubicación.")
                .estado(false)
                .build();

        Notificacion guardada = notificacionRepo.save(notificacion);
        assertNotNull(guardada.getIdNotificacion());
    }

    // Test para listar todas las notificaciones
    @Test
    public void listarNotificacionesTest() {
        List<Notificacion> lista = notificacionRepo.findAll();
        lista.forEach(System.out::println);
        assertEquals(1, lista.size()); // Ajusta el número según la cantidad esperada
    }

    // Test para actualizar el estado de una notificación a "leída"
    @Test
    public void actualizarNotificacionTest() {
        String id = "67f738174ded6e62b68e0827"; // ID de la notificación a actualizar

        Notificacion notificacion = notificacionRepo.findById(id).orElseThrow();
        notificacion.setEstado(true); // Marcamos como leída

        notificacionRepo.save(notificacion);

        Notificacion actualizada = notificacionRepo.findById(id).orElseThrow();
        assertTrue(actualizada.isEstado());
    }

    // Test para eliminar una notificación por su ID
    @Test
    public void eliminarNotificacionTest() {
        String id = "67f738174ded6e62b68e0827"; // ID de la notificación a eliminar

        notificacionRepo.deleteById(id);

        Notificacion eliminada = notificacionRepo.findById(id).orElse(null);
        assertNull(eliminada);
    }

    // TEST REPO -------------------------------------------------------------------------------------------------------

    // Test: Buscar todas las notificaciones de un usuario
    @Test
    public void buscarPorUsuarioTest() {
        ObjectId idUsuario = new ObjectId("67f730e7d5b9332193cc1922");
        List<Notificacion> notificaciones = notificacionRepo.buscarPorUsuario(idUsuario.toHexString());
        notificaciones.forEach(System.out::println);
        assertFalse(notificaciones.isEmpty());
    }

    // Test: Buscar todas las notificaciones asociadas a un reporte
    @Test
    public void buscarPorReporteTest() {
        ObjectId idReporte = new ObjectId("67f6f059f5144a4eddc02b4f");
        List<Notificacion> notificaciones = notificacionRepo.buscarPorReporte(idReporte.toHexString());
        notificaciones.forEach(System.out::println);
        assertFalse(notificaciones.isEmpty());
    }

    // Test: Buscar notificaciones NO leídas por usuario
    @Test
    public void buscarNoLeidasPorUsuarioTest() {
        ObjectId idUsuario = new ObjectId("67f730e7d5b9332193cc1922");
        List<Notificacion> noLeidas = notificacionRepo.buscarNoLeidasPorUsuario(idUsuario.toHexString());
        noLeidas.forEach(System.out::println);
    }

    // Test: Buscar notificaciones leídas por usuario
    @Test
    public void buscarLeidasPorUsuarioTest() {
        ObjectId idUsuario = new ObjectId("67f730e7d5b9332193cc1922");
        List<Notificacion> leidas = notificacionRepo.buscarLeidasPorUsuario(idUsuario.toHexString());
        leidas.forEach(System.out::println);
    }
}
