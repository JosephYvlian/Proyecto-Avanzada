package co.edu.uniquindio.ProyectoAvanzada.test;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarCategoriaTest() {
        Categoria categoria = Categoria.builder()
                .nombre("Hurto a Mano Desarmada")
                .descripcion("Hurtos a Mano Desarmada, si sabe")
                .build();

        Categoria guardada = categoriaRepo.save(categoria);

        assertNotNull(guardada);
    }

    @Test
    public void actualizarCategoriaTest() {
        ObjectId id = new ObjectId("67f6b0b21c1d871c5147aa5b");

        Categoria categoria = categoriaRepo.findById(String.valueOf(id)).orElseThrow();

        categoria.setDescripcion("Nueva descripcion");

        categoriaRepo.save(categoria);

        Categoria actualizada = categoriaRepo.findById(String.valueOf(id)).orElseThrow();

        assertEquals("Nueva descripcion", actualizada.getDescripcion());
    }

    @Test
    public void listarCategoriasTest() {
        List<Categoria> lista = categoriaRepo.findAll();

        lista.forEach(System.out::println);

        assertFalse(lista.isEmpty());
    }

    @Test
    public void eliminarCategoriaTest() {
        //Definimos el id del cliente (de MongoDB)
        ObjectId id = new ObjectId("67f6b0b21c1d871c5147aa5b");

        categoriaRepo.deleteById(String.valueOf(id));

        Categoria eliminada = categoriaRepo.findById(String.valueOf(id)).orElse(null);

        assertNull(eliminada);
    }
}