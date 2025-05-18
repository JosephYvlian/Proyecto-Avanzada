package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.servicios.impl.FirebaseMessageService;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones/")
public class NotificacionControlador {

    private final NotificacionServicio notificacionServicio;
    private final FirebaseMessageService firebaseService;

  /*  @PostMapping("/enviar")
    public String enviarNotificacion(@RequestParam String token,
                                     @RequestParam String titulo,
                                     @RequestParam String mensaje){

        return firebaseService.enviarNotificacion(token, titulo, mensaje);
    }*/

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarNotificaciones(@PathVariable @NotBlank String idUsuario) {
        List<NotificacionDTO> notificaciones = notificacionServicio.listarNotificacionesUsuario(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, notificaciones));
    }

    @GetMapping("/{idUsuario}/leidas")
    public ResponseEntity<MensajeDTO<List<Notificacion>>> obtenerLeidas(@PathVariable String idUsuario) {
        List<Notificacion> leidas = notificacionServicio.listarPorEstado(idUsuario, true);
        return ResponseEntity.ok(
                new MensajeDTO<>(true, leidas)
        );
    }

    @GetMapping("/{idUsuario}/no-leidas")
    public ResponseEntity<MensajeDTO<List<Notificacion>>> obtenerNoLeidas(@PathVariable String idUsuario) {
        List<Notificacion> noLeidas = notificacionServicio.listarPorEstado(idUsuario, false);
        return ResponseEntity.ok(
                new MensajeDTO<>(true, noLeidas)
        );
    }

    @PutMapping("/marcar-leida/{idNotificacion}")
    public ResponseEntity<MensajeDTO<String>> marcarComoLeida(@PathVariable @NotBlank String idNotificacion) {
        notificacionServicio.marcarComoLeida(idNotificacion);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación marcada como leída"));
    }

    @PutMapping("/marcar-todas-leidas/{idUsuario}")
    public ResponseEntity<MensajeDTO<String>> marcarTodasComoLeidas(@PathVariable @NotBlank String idUsuario) {
        notificacionServicio.marcarTodasComoLeidas(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Todas las notificaciones marcadas como leídas"));
    }

}
