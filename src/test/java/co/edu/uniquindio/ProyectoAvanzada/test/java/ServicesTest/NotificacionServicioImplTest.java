package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

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
        NotificacionDTO dto = new NotificacionDTO(
                "Alerta Importante",
                "Un evento ha ocurrido cerca de ti.",
                false,
                LocalDateTime.now()
        );

        // Enviar la notificación (idUsuario es opcional, puedes simular uno real si quieres)
        notificacionServicio.enviarNotificacion(dto, idUsuario);

        List<Notificacion> lista = notificacionRepo.findAll();
        assertFalse(lista.isEmpty());

        Notificacion ultima = lista.getLast();
        idNotificacionCreada = ultima.getIdNotificacion();

        assertEquals("Alerta Importante", ultima.getTituloNotificacion());
        assertFalse(ultima.isEstado(), "El estado debe ser false tras crear la notificación");
    }

    @Test
    public void testMarcarComoLeido() {
        notificacionServicio.marcarComoLeido(idNotificacionCreada);

        Notificacion notificacion = notificacionRepo.findById(idNotificacionCreada).orElseThrow();
        assertTrue(notificacion.isEstado(), "La notificación debería estar marcada como leída (estado = true)");
    }
}
