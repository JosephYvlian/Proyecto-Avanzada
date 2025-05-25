package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.AuthServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthControlador {

    private final CategoriaServicio categoriaServicio;
    private final AuthServicio authServicio;
    private final UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = authServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, tokenDTO));
    }


    @PostMapping("/verificar-usuario")
    public ResponseEntity<MensajeDTO<String>> verificarCodigo(@Valid @RequestBody CodVerificacionDTO codVerificacion) {
        usuarioServicio.verificarUsuario(codVerificacion);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Codigo verificado correctamente"));
    }

    @PostMapping("/recuperar-usuario")
    public ResponseEntity<MensajeDTO<String>> recuperarUsuario(@Valid @RequestBody @NotBlank(message = "El email no puede ser vacio.")
                                                                   @Email(message = "Ingrese un email valido") String email) {
        usuarioServicio.recuperarUsuario(email);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Codigo recuperado correctamente"));
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<MensajeDTO<String>> recuperarContrasena(@Valid @RequestBody RecuperarContrasenaDTO recuperarContrasena) {
        usuarioServicio.recuperarContrasena(recuperarContrasena);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contrasena recuperada exitosamente"));
    }

    @PutMapping("/actualizar-contrasena")
    public ResponseEntity<MensajeDTO<String>> cambiarContrasena(@Valid @RequestBody ActualizarContrasenaDTO actualizarContrasena) {
        usuarioServicio.actualizarContrasena(actualizarContrasena);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Contrasena actualizada exitosamente"));
    }
}