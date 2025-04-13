package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones/")
public class NotificacionControlador {

    private final NotificacionServicio notificacionServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> enviarNotificacion(@RequestBody EnviarNotificacionDTO enviarNotificacionDTO) {
        notificacionServicio.enviarNotificacion(enviarNotificacionDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificacion enviada con exito"));
    }

    @PutMapping("/{idNotificacion}/leida")
    public ResponseEntity<MensajeDTO<String>> marcarComoLeida(@PathVariable String idNotificacion) {
        notificacionServicio.marcarComoLeido(idNotificacion);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación marcada como leída."));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarTodas(@PathVariable String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificaciones(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/{idUsuario}/no-leidas")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarNoLeidas(@PathVariable String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesNoLeidas(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/{idUsuario}/leidas")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarLeidas(@PathVariable String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesLeidas(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

}
