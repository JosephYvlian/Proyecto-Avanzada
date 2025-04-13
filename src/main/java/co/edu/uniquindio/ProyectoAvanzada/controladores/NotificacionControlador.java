package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public ResponseEntity<MensajeDTO<String>> enviarNotificacion(@Valid @RequestBody EnviarNotificacionDTO enviarNotificacionDTO) {
        notificacionServicio.enviarNotificacion(enviarNotificacionDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificacion enviada con exito"));
    }

    @PutMapping("/{idNotificacion}/leida")
    public ResponseEntity<MensajeDTO<String>> marcarComoLeida(@Valid @PathVariable @NotBlank(message = "El idNotificacion no puede estar vacio") String idNotificacion) {
        notificacionServicio.marcarComoLeido(idNotificacion);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación marcada como leída."));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarTodas(@Valid @NotBlank(message = "El idUsuario no puede estar vacio") @PathVariable String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificaciones(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/{idUsuario}/no-leidas")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarNoLeidas(@Valid @PathVariable @NotBlank(message = "El idUsuario no puede estar vacio")String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesNoLeidas(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @GetMapping("/{idUsuario}/leidas")
    public ResponseEntity<MensajeDTO<List<NotificacionDTO>>> listarLeidas(@Valid @PathVariable @NotBlank(message = "El idUsuario no puede estar vacio") String idUsuario) {
        List<NotificacionDTO> lista = notificacionServicio.listarNotificacionesLeidas(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

}
