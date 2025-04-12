package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthControlador {

    private final CategoriaServicio categoriaServicio;

    private final UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = usuarioServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/registrarUsuario")
    public ResponseEntity<MensajeDTO<String>> registarUsuario(@RequestBody RegistroDTO registro) {
        usuarioServicio.crear(registro);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Usuario registrado exitosamente"));
    }

    @PostMapping("/verificar")
    public ResponseEntity<MensajeDTO<String>> verificarCodigo(@RequestBody CodVerificacionDTO codVerificacion) {
        usuarioServicio.verificarUsuario(codVerificacion);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Codigo verificado correctamente"));
    }

    @PostMapping("/recuperar")
    public ResponseEntity<MensajeDTO<String>> recuperarContrasena(@RequestBody RecuperarContrasenaDTO recuperarContrasena) {
        usuarioServicio.recuperarContrasena(recuperarContrasena);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contrasena recuperada exitosamente"));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<MensajeDTO<String>> cambiarContrasena(@RequestBody ActualizarContrasenaDTO actualizarContrasena) {
        usuarioServicio.actualizarContrasena(actualizarContrasena);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contrasena actualizada exitosamente"));
    }
}