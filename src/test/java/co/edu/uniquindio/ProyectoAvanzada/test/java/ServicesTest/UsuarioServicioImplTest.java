package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.*;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CodigoRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioServicioImplTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CodigoRepo codigoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String idUsuario = "67fc17a7dbc7f76054be2fd8";

    /*@BeforeEach
    void limpiarAntes() {
        usuarioRepo.deleteAll();
        codigoRepo.deleteAll();
    }*/

    @Test
    void testCrearUsuarioCliente() {
        RegistroDTO dto = new RegistroDTO(Rol.CLIENTE, "Joseph Garcia", "3116796649", "Armenia", "Calle 10", "josephstoff2@gmail.com", "pass123", EstadoCuenta.INACTIVO, LocalDateTime.now());

        usuarioServicio.crear(dto);

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail("josephstoff2@gmail.com").orElse(null);
        assertNotNull(usuario);
        assertEquals("Joseph Garcia", usuario.getNombre());
        assertEquals(EstadoCuenta.INACTIVO, usuario.getEstadoCuenta());
        idUsuario = usuario.getIdUsuario();

        CodigoValidacion codigo = codigoRepo.buscarPorEmail("josephstoff2@gmail.com").orElse(null);
        assertNotNull(codigo);
    }

    @Test
    void testCrearUsuarioAdministrador() {
        RegistroDTO dto = new RegistroDTO(Rol.ADMINISTRADOR, "Luisa", "3124568978", "Bogotá", "Carrera 8", "admin.nex@gmail.com", "pass123", EstadoCuenta.ACTIVO, LocalDateTime.now());

        usuarioServicio.crear(dto);

        Usuario admin = usuarioRepo.buscarUsuarioPorEmail("admin.nex@gmail.com").orElse(null);
        assertNotNull(admin);
        assertEquals(Rol.ADMINISTRADOR, admin.getRol());
        assertEquals(EstadoCuenta.ACTIVO, admin.getEstadoCuenta());
    }

    @Test
    void testVerificarUsuario() {
        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail("josephstoff2@gmail.com").orElseThrow();
        CodigoValidacion codigo = codigoRepo.buscarPorEmail("josephstoff2@gmail.com").orElseThrow();

        usuarioServicio.verificarUsuario(new CodVerificacionDTO("josephstoff2@gmail.com", codigo.getCodigo()));

        Usuario actualizado = usuarioRepo.buscarUsuarioPorEmail("josephstoff2@gmail.com").orElseThrow();
        assertEquals(EstadoCuenta.ACTIVO, actualizado.getEstadoCuenta());
    }

    @Test
    void testLoginExitoso() throws Exception {
        LoginDTO login = new LoginDTO("josephstoff2@gmail.com", "pass123");
        TokenDTO tokenDTO = usuarioServicio.login(login);

        assertNotNull(tokenDTO);
        assertNotNull(tokenDTO.token());
    }

    @Test
    void testEditarUsuario() {
        EditarUsuarioDTO editarDTO = new EditarUsuarioDTO("Joseph Mod", "3116796649", "Medellin", "Nueva dirección");
        usuarioServicio.editar(editarDTO, idUsuario);

        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        assertNotNull(usuario);
        assertEquals("Joseph Mod", usuario.getNombre());
        assertEquals("Medellin", usuario.getCiudad());
    }

    @Test
    void testObtenerUsuario() {
        UsuarioDTO dto = usuarioServicio.obtener(idUsuario);

        assertNotNull(dto);
        assertEquals("Joseph Mod", dto.nombre());
    }

    @Test
    void testListarUsuarios() {
        List<UsuarioDTO> lista = usuarioServicio.listarTodos();

        assertFalse(lista.isEmpty());
        assertEquals(2, lista.size()); // Admin + usuario
    }

    @Test
    void testRecuperarUsuario() {
        usuarioServicio.recuperarUsuario("josephstoff2@gmail.com");

        CodigoValidacion codigo = codigoRepo.buscarPorEmail("josephstoff2@gmail.com").orElse(null);
        assertNotNull(codigo);
    }

    @Test
    void testActualizarContrasena() {
        ActualizarContrasenaDTO dto = new ActualizarContrasenaDTO("josephstoff2@gmail.com", "pass123", "nuevaPass123");

        usuarioServicio.actualizarContrasena(dto);

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail("josephstoff2@gmail.com").orElseThrow();
        assertTrue(passwordEncoder.matches("nuevaPass123", usuario.getPassword()));
    }

    @Test
    void testRecuperarContrasena() {
        usuarioServicio.recuperarUsuario("josephstoff2@gmail.com");
        CodigoValidacion codigo = codigoRepo.buscarPorEmail("josephstoff2@gmail.com").orElseThrow();

        RecuperarContrasenaDTO dto = new RecuperarContrasenaDTO("josephstoff2@gmail.com", codigo.getCodigo(), "otraClave321");
        usuarioServicio.recuperarContrasena(dto);

        Usuario usuario = usuarioRepo.buscarUsuarioPorEmail("josephstoff2@gmail.com").orElseThrow();
        assertTrue(passwordEncoder.matches("otraClave321", usuario.getPassword()));
    }

    @Test
    void testEliminarUsuario() {
        usuarioServicio.eliminar(idUsuario);

        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        assertNotNull(usuario);
        assertEquals(EstadoCuenta.ELIMINADO, usuario.getEstadoCuenta());
    }
}

