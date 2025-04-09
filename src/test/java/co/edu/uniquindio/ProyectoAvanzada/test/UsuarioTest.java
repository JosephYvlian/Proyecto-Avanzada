package co.edu.uniquindio.ProyectoAvanzada.test;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Rol;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarTest() {
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.ADMINISTRADOR);
        usuario.setNombre("William Shakespeare");
        usuario.setCiudad("Medellin");
        usuario.setTelefono("3106786789");
        usuario.setDireccion("Cr 10 #9 - 28");
        usuario.setEmail("williamshake@gmail.com");

        Usuario guardado = usuarioRepo.save(usuario);

        assertNotNull(guardado);
    }

    @Test
    public void actualizarTest() {
        //Definimos el id del cliente (de MongoDB)
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
        //Definimos el id del cliente que queremos borrar
        ObjectId id = new ObjectId("67f6b0b21c1d871c5147aa5b");


        //Borramos el cliente con el id XXXXXXX
        usuarioRepo.deleteById(String.valueOf(id));


        //Obtenemos el cliente con el id XXXXXXX
        Usuario cliente = usuarioRepo.findById(String.valueOf(id)).orElse(null);


        //Verificamos que el cliente no exista, debe ser null ya que fue eliminado
        assertNull(cliente);
    }


}