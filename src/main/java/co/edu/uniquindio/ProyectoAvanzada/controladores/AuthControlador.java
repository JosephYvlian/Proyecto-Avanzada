package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthControlador {

    private final CategoriaServicio categoriaServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<RegistroDTO>> registarUsuario(@RequestBody RegistroDTO registro) {
        return ResponseEntity.ok(new MensajeDTO<>(false, ));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<CodVerificacionDTO>> verificarCodigo(@RequestBody CodVerificacionDTO codVerificacion) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<LoginDTO>> iniciarSesion(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<CodRecuperacionDTO>> recuperarContrasena(@RequestBody CodRecuperacionDTO codRecuperacion) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @PutMapping
    public ResponseEntity<MensajeDTO<CambiarContrasenaDTO>> cambiarContrasena(@RequestBody CambiarContrasenaDTO cambiarContrasena) {
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }
}
