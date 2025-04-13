package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificacionServicioImplTest {

    @Autowired
    private NotificacionServicio notificacionServicio;

    @Autowired
    private NotificacionRepo notificacionRepo;

    private static String idNotificacionCreada = "67fb1dd6d873005c34e9c502";

    private static String idUsuario = "67fb13ce89d0bc1871f9cede";

    @Test
    public void testEnviarNotificacion() {
        EnviarNotificacionDTO dto = new EnviarNotificacionDTO(
                "penegrande123",          // ID de usuario simulado
                "reportedelapollagorda123",          // ID de reporte simulado
                "Alerta Importante",   // Título de la notificación
                "Un evento ha ocurrido cerca de ti." // Mensaje de la notificación
        );

        // Enviar la notificación (usamos el DTO con los parámetros adecuados)
        notificacionServicio.enviarNotificacion(dto);

        List<Notificacion> lista = notificacionRepo.findAll();
        assertFalse(lista.isEmpty());

        Notificacion ultima = lista.getLast();
        idNotificacionCreada = ultima.getIdNotificacion();

        assertEquals("Alerta Importante", ultima.getTituloNotificacion());
        assertFalse(ultima.isLeida(), "El estado debe ser false tras crear la notificación");
    }

    @Test
    public void testMarcarComoLeido() {
        notificacionServicio.marcarComoLeido(idNotificacionCreada);

        Notificacion notificacion = notificacionRepo.findById(idNotificacionCreada).orElseThrow();
        assertTrue(notificacion.isLeida(), "La notificación debería estar marcada como leída.");
    }
}
