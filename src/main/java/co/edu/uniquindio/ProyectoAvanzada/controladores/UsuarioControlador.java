package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")

public class UsuarioControlador {
    private final UsuarioServicio usuarioServicio;

    @PutMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<String>> editar(@Valid @RequestBody EditarUsuarioDTO cuenta, @PathVariable String idUsuario) throws Exception {
        usuarioServicio.editar(cuenta, idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String idUsuario) throws Exception{
        usuarioServicio.eliminar(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtener(@PathVariable String idUsuario) throws Exception{
        UsuarioDTO info = usuarioServicio.obtener(idUsuario);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarTodos(){
        List<UsuarioDTO> lista = usuarioServicio.listarTodos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
}