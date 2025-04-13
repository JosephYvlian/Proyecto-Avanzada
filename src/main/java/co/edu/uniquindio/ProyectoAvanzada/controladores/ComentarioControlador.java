package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearComentario(@RequestBody CrearComentarioDTO dto) throws Exception {
        comentarioServicio.crearComentario(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario creado."));
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTO>>> listarComentarios(@PathVariable String idReporte) throws Exception {
        List<ComentarioDTO> comentarios = comentarioServicio.listarComentariosDeReporte(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, comentarios));
    }

    @DeleteMapping("/{idReporte}/{idComentario}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String idReporte, @PathVariable String idComentario) {
        comentarioServicio.eliminarComentario(idReporte, idComentario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario eliminado correctamente."));
    }
}
