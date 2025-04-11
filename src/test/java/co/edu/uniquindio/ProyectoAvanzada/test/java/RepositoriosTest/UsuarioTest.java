package co.edu.uniquindio.ProyectoAvanzada.test.java.RepositoriosTest;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    // TEST CRUD -------------------------------------------------------------------------------------------------------
    @Test
    public void registrarTest() {
        // Creamos un usuario utilizando el patrón Builder
        Usuario usuario = Usuario.builder()
                .rol(Rol.CLIENTE)
                .nombre("Hector Lavoe")
                .ciudad("Barranquilla")
                .telefono("3094507845")
                .direccion("Cr 5 #9 - 11")
                .email("hector@gmail.com")
                .password("98765")
                .estadoCuenta(EstadoCuenta.ACTIVO)
                .fechaRegistro(LocalDateTime.now())
                .build();
        // Guardamos el usuario en la base de datos
        Usuario guardado = usuarioRepo.save(usuario);
        // Verificamos que se haya guardado correctamente
        assertNotNull(guardado);
    }

    @Test
    public void actualizarTest() {
        //Definimos el ID del cliente (de MongoDB)
        ObjectId id = new ObjectId("67f6b0b21c1d871c5147aa5b");
        //Obtenemos el cliente con el id XXXXXXX
        Usuario usuario = usuarioRepo.findById(String.valueOf(id)).orElseThrow();
        //Modificar el email del cliente
        usuario.setEmail("nuevoemail@email.com");
        //Guardamos nuevamente el cliente
        usuarioRepo.save(usuario);
        //Obtenemos el cliente con el id XXXXXXX nuevamente
        Usuario usuarioActualizado = usuarioRepo.findById(String.valueOf(id)).orElseThrow();
        //Verificamos que el email se haya actualizado
        assertEquals("nuevoemail@email.com", usuarioActualizado.getEmail());
    }

    @Test
    public void listarTodosTest(){
        //Obtenemos la lista de todos los clientes (por ahora solo tenemos 1)
        List<Usuario> lista = usuarioRepo.findAll();
        //Imprimimos los clientes, se hace uso de una función lambda
        lista.forEach(System.out::println);
        //Verificamos que solo exista un cliente
        assertEquals(2, lista.size());
    }

    @Test
    public void eliminarTest(){
        //Definimos el ID del cliente que queremos borrar
        ObjectId id = new ObjectId("67f6b5f40ec2462fec4002c4");
        //Borramos el cliente con el id XXXXXXX
        usuarioRepo.deleteById(String.valueOf(id));
        //Obtenemos el cliente con el id XXXXXXX
        Usuario cliente = usuarioRepo.findById(String.valueOf(id)).orElse(null);
        //Verificamos que el cliente no exista, debe ser null, ya que fue eliminado
        assertNull(cliente);
    }

    // TEST REPO -------------------------------------------------------------------------------------------------------

    // Test: Buscar usuario por correo
    @Test
    public void buscarPorEmailTest() {
        String email = "williamshake@gmail.com";
        Optional<Usuario> usuario = usuarioRepo.buscarUsuarioPorEmail(email);
        assertTrue(usuario.isPresent());
        System.out.println(usuario.get());
    }

    // Test: Buscar usuarios por rol
   /* @Test
    public void buscarPorRolTest() {
        String rol = Rol.CLIENTE.name();
        List<Usuario> usuarios = usuarioRepo.buscarPorRol(rol);
        usuarios.forEach(System.out::println);
        assertFalse(usuarios.isEmpty());
    }

    // Test: Buscar usuarios por ciudad exacta
    @Test
    public void buscarPorCiudadTest() {
        String ciudad = "Pasto";
        List<Usuario> usuarios = usuarioRepo.buscarPorCiudad(ciudad);
        usuarios.forEach(System.out::println);
        assertFalse(usuarios.isEmpty());
    }

    // Test: Buscar usuarios por estado de cuenta
    @Test
    public void buscarPorEstadoCuentaTest() {
        String estado = EstadoCuenta.ACTIVO.name();
        List<Usuario> usuarios = usuarioRepo.buscarPorEstadoCuenta(estado);
        usuarios.forEach(System.out::println);
        assertFalse(usuarios.isEmpty());
    }*/
}