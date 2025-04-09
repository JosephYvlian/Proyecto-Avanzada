package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones/")
public class NotificacionControlador {

    @PostMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<EnviarNotificacionDTO>> enviarNotificacion(@PathVariable String idReporte, @RequestBody EnviarNotificacionDTO enviarNotificacionDTO) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionDTO>> listarNotificacion() {
        return ResponseEntity.ok(new ArrayList<NotificacionDTO>());
    }
}
