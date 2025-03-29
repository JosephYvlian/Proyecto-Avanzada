package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    @PostMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<ComentarioDTO>> crearComentario(@PathVariable String idReporte, @RequestBody CrearComentarioDTO comentario) {
        return ResponseEntity.ok(new MensajeDTO<ComentarioDTO>(false, null));
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTO>>> listarComentarios(@PathVariable String idReporte) {
        return ResponseEntity.ok(new MensajeDTO<>(false, new ArrayList<>()));
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String idReporte) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }
}
