package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificacionServicioImplTest {

    @Autowired
    private NotificacionServicio notificacionServicio;

    @Autowired
    private NotificacionRepo notificacionRepo;

    private static String idUsuario = "67fc17a7dbc7f76054be2fd8"; // usa un ID real de un usuario ACTIVO
    private static String idReporte = "67fc1c2c29e5624bdf6582d4"; // usa un ID real de un reporte
    private static String idNotificacionCreada = "67fc1d7e551407134ee84718";

    @Test
    public void testEnviarNotificacion() {
        EnviarNotificacionDTO dto = new EnviarNotificacionDTO(
                idUsuario,
                idReporte,
                "Alerta Importante",
                "Se detectó actividad sospechosa en tu zona"
        );

        notificacionServicio.enviarNotificacion(dto);

        List<Notificacion> lista = notificacionRepo.buscarPorUsuario(idUsuario);
        assertFalse(lista.isEmpty());

        Notificacion ultima = lista.getLast();
        idNotificacionCreada = ultima.getIdNotificacion();

        assertEquals("Alerta Importante", ultima.getTituloNotificacion());
        assertFalse(ultima.isLeida(), "La notificación debería estar sin leer al crearse");
    }

    @Test
    public void testMarcarComoLeida() {
        notificacionServicio.marcarComoLeido(idNotificacionCreada);

        Notificacion notificacion = notificacionRepo.findById(idNotificacionCreada).orElseThrow();
        assertTrue(notificacion.isLeida(), "La notificación debería estar marcada como leída");
    }

    @Test
    public void testListarNotificaciones() {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificaciones(idUsuario);
        assertFalse(lista.isEmpty(), "La lista de notificaciones no debería estar vacía");
    }

    @Test
    public void testListarNotificacionesNoLeidas() {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesNoLeidas(idUsuario);
        // Ya marcamos como leída en el paso anterior, así que puede estar vacía
        assertNotNull(lista);
    }

    @Test
    public void testListarNotificacionesLeidas() {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesLeidas(idUsuario);
        assertFalse(lista.isEmpty(), "Debe haber al menos una notificación leída");
    }
}