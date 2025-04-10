package co.edu.uniquindio.ProyectoAvanzada.test;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;


    // TEST CRUD -------------------------------------------------------------------------------------------------------

    // Test para registrar una nueva categoría en la base de datos
    @Test
    public void registrarCategoriaTest() {
        Categoria categoria = Categoria.builder()
                .nombre("Hurto a Mano Desarmada")
                .descripcion("Hurtos a Mano Desarmada, si sabe")
                .build();

        Categoria guardada = categoriaRepo.save(categoria);

        assertNotNull(guardada);
    }

    // Test para actualizar la descripción de una categoría existente
    @Test
    public void actualizarCategoriaTest() {
        ObjectId id = new ObjectId("67f6eaee7555db6e7b134488"); // ID de la categoria a actualizar

        Categoria categoria = categoriaRepo.findById(String.valueOf(id)).orElseThrow();

        categoria.setDescripcion("Nueva descripcion");

        categoriaRepo.save(categoria);

        Categoria actualizada = categoriaRepo.findById(String.valueOf(id)).orElseThrow();

        assertEquals("Nueva descripcion", actualizada.getDescripcion());
    }

    // Test para listar todas las categorías almacenadas en la base de datos
    @Test
    public void listarCategoriasTest() {
        List<Categoria> lista = categoriaRepo.findAll();

        lista.forEach(System.out::println);

        assertEquals(1, lista.size());
    }

    // Test para eliminar una categoría por su ID y verificar que ya no exista
    @Test
    public void eliminarCategoriaTest() {
        ObjectId id = new ObjectId("67f6eaee7555db6e7b134488"); // ID de la categoria a eliminar

        categoriaRepo.deleteById(String.valueOf(id));

        Categoria eliminada = categoriaRepo.findById(String.valueOf(id)).orElse(null);

        assertNull(eliminada);
    }

    // TEST REPO -------------------------------------------------------------------------------------------------------

    // Test: Buscar categoría por nombre exacto
    @Test
    public void buscarPorNombreTest() {
        String nombreCategoria = "Hurto a Mano Desarmada"; // Asegúrate de que exista en tu BD
        Optional<Categoria> categoria = categoriaRepo.buscarPorNombre(nombreCategoria);

        assertTrue(categoria.isPresent());
        System.out.println(categoria.get());
    }
}
