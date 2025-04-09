package co.edu.uniquindio.ProyectoAvanzada.test;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ciudad;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReporteTest {

    @Autowired
    private ReporteRepo reporteRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarReporteTest() {
        ObjectId categoriaId = new ObjectId("67f6eaee7555db6e7b134488"); //ID de una categoria existene
        Categoria categoria = categoriaRepo.findById(String.valueOf(categoriaId))
                .orElseThrow(() -> new IllegalArgumentException("La categoría no existe"));

        Ciudad ciudad = new Ciudad("Armenia");

        Ubicacion ubicacion = new Ubicacion(4.53454, -75.67543, "La Isabela");

        Reporte reporte = Reporte.builder()
                .titulo("HOLA")
                .categoria(categoriaId)
                .ciudad(ciudad)
                .descripcion("Un careloco me acaba de robar cerca al estadio")
                .ubicacion(ubicacion)
                .fecha(LocalDateTime.now())
                .imagenes(List.of("img1.png", "img2.png"))
                .build();

        Reporte guardado = reporteRepo.save(reporte);
        assertNotNull(guardado);
    }

    @Test
    public void actualizarReporteTest() {
        ObjectId id = new ObjectId("67f6f059f5144a4eddc02b4f");

        Reporte reporte = reporteRepo.findById(String.valueOf(id)).orElseThrow();

        reporte.setDescripcion("Nueva descripcion");

        reporteRepo.save(reporte);

        Reporte actualizada = reporteRepo.findById(String.valueOf(id)).orElseThrow();

        assertEquals("Nueva descripcion", actualizada.getDescripcion());
    }

    @Test
    public void listarReporteTest() {
        List<Reporte> lista = reporteRepo.findAll();

        lista.forEach(System.out::println);

        assertEquals(1, lista.size());
    }

    @Test
    public void eliminarCategoriaTest() {
        //Definimos el id del cliente (de MongoDB)
        ObjectId id = new ObjectId("67f6f059f5144a4eddc02b4f");

        reporteRepo.deleteById(String.valueOf(id));

        Reporte eliminada = reporteRepo.findById(String.valueOf(id)).orElse(null);

        assertNull(eliminada);
    }
}
