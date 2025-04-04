package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
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

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@PathVariable String idReporte, @RequestBody CrearComentarioDTO comentario) {
        comentarioServicio.crearComentario(idReporte, comentario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario creado correctamente"));
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTO>>> listarComentarios(@PathVariable String idReporte) {
        List<ComentarioDTO> lista = comentarioServicio.listarComentarios(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String idReporte) {
        comentarioServicio.eliminarComentario(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario eliminado correctamente"));
    }
}
