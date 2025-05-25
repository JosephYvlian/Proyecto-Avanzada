package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody CrearComentarioDTO dto) throws Exception {
        comentarioServicio.crearComentario(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario creado correctamente."));
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<List<ComentarioDTO>>> listarComentarios(@Valid @PathVariable
                                                                             @NotBlank(message = "El idReporte no puede estar vacio") String idReporte) throws Exception {
        List<ComentarioDTO> comentarios = comentarioServicio.listarComentariosDeReporte(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, comentarios));
    }

    @DeleteMapping("/{idReporte}/{idComentario}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@Valid @NotBlank(message = "El idReporte no puede estar vacio") @PathVariable String idReporte,
                                                                 @Valid @NotBlank(message = "El idComentario no puede estar vacio") @PathVariable String idComentario) {
        comentarioServicio.eliminarComentario(idReporte, idComentario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Comentario eliminado correctamente."));
    }
}
