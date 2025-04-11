package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
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

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crear(@Valid @RequestBody CrearUsuarioDTO cuenta) throws Exception {
        usuarioServicio.crear(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Su registro ha sido exitoso"));
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<MensajeDTO<String>> editar(@Valid @RequestBody EditarUsuarioDTO cuenta, @PathVariable String cedula) throws Exception {
        usuarioServicio.editar(cuenta);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta editada exitosamente"));
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<MensajeDTO<String>> eliminar(@PathVariable String cedula) throws Exception{
        usuarioServicio.eliminar(cedula);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada exitosamente"));
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtener(@PathVariable String cedula) throws Exception{
        UsuarioDTO info = usuarioServicio.obtener(cedula);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarTodos(){
        List<UsuarioDTO> lista = usuarioServicio.listarTodos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
}