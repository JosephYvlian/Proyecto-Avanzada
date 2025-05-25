package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.LoginDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.seguridad.JWTUtils;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.AuthServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServicioImpl implements AuthServicio {

    private final UsuarioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepo.buscarUsuarioPorEmail(loginDTO.email());

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        Usuario usuario = optionalUsuario.get();

        if (usuario.getEstadoCuenta() != EstadoCuenta.ACTIVO) {
            throw new RuntimeException("La cuenta aún no ha sido verificada");
        }

        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generateToken(usuario.getIdUsuario().toString(), crearClaims(usuario));
        return new TokenDTO(token);
    }


    private Map<String, String> crearClaims(Usuario usuario) {
        return Map.of(
                "email", usuario.getEmail(),
                "nombre", usuario.getNombre(),
                "rol", usuario.getRol().name()
        );
    }
}
