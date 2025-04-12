package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class

CategoriaServiceImplTest {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private CategoriaRepo categoriaRepo;

    private static String idCategoriaCreada = "67f8825d30a95f0740a56395";

    @Test
    public void testCrearCategoria() {
        CrearCategoriaDTO categoriaDTO = new CrearCategoriaDTO("Robo", "Reportes relacionados con robos");

        categoriaServicio.crearCategoria(categoriaDTO);

        List<Categoria> categorias = categoriaRepo.findAll();
        assertFalse(categorias.isEmpty());

        idCategoriaCreada = categorias.getLast().getIdCategoria(); // guarda el ID para siguientes pruebas
        System.out.println("ID creado: " + idCategoriaCreada);
    }

    @Test
    public void testEditarCategoria() {
        EditarCategoriaDTO editarDTO = new EditarCategoriaDTO("Robo actualizado", "Descripción editada");

        categoriaServicio.editarCategoria(editarDTO, idCategoriaCreada);

        Categoria actualizada = categoriaRepo.findById(idCategoriaCreada).orElseThrow();
        assertEquals("Robo actualizado", actualizada.getNombre());
        assertEquals("Descripción editada", actualizada.getDescripcion());
    }

    @Test
    public void testListarCategorias() {
        List<CategoriaDTO> categorias = categoriaServicio.listarCategorias();

        assertFalse(categorias.isEmpty());
        categorias.forEach(System.out::println);
    }

    @Test
    public void testEliminarCategoria() {
        categoriaServicio.eliminarCategoria(idCategoriaCreada);

        boolean existe = categoriaRepo.existsById(idCategoriaCreada);
        assertFalse(existe);
    }
}