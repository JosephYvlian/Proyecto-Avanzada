package co.edu.uniquindio.ProyectoAvanzada.test.RepositoriosTest;

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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReporteTest {

    @Autowired
    private ReporteRepo reporteRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    // TEST CRUD -------------------------------------------------------------------------------------------------------

    // Test para registrar un nuevo reporte en la base de datos
    @Test
    public void registrarReporteTest() {
        ObjectId categoriaId = new ObjectId("67f7f67ec7f7bb4b5717fb21"); // ID de una categoría existente
        Categoria categoria = categoriaRepo.findById(String.valueOf(categoriaId))
                .orElseThrow(() -> new IllegalArgumentException("La categoría no existe"));

        Ciudad ciudad = new Ciudad("Armenia");
        Ubicacion ubicacion = new Ubicacion(4.53454, -75.67543, "La Isabela");

        Reporte reporte = Reporte.builder()
                .titulo("Me acaban de robar, demonios")
                .categoria(categoriaId)
                .ciudad(ciudad)
                .descripcion("Un careloco me acaba de robar cerca al estadio")
                .ubicacion(ubicacion)
                .fecha(new Date())
                .imagenes(List.of("img1.png", "img2.png"))
                .build();

        Reporte guardado = reporteRepo.save(reporte);
        assertNotNull(guardado);
    }

    // Test para actualizar la descripción de un reporte existente
    @Test
    public void actualizarReporteTest() {
        ObjectId id = new ObjectId("67f6f059f5144a4eddc02b4f"); // ID del reporte a actualizar

        Reporte reporte = reporteRepo.findById(String.valueOf(id)).orElseThrow();
        reporte.setDescripcion("Nueva descripcion");

        reporteRepo.save(reporte);

        Reporte actualizada = reporteRepo.findById(String.valueOf(id)).orElseThrow();
        assertEquals("Nueva descripcion", actualizada.getDescripcion());
    }

    // Test para listar todos los reportes almacenados
    @Test
    public void listarReporteTest() {
        List<Reporte> lista = reporteRepo.findAll();
        lista.forEach(System.out::println);

        assertEquals(1, lista.size());
    }

    // Test para eliminar un reporte por su ID y verificar que ya no exista
    @Test
    public void eliminarReporteTest() {
        ObjectId id = new ObjectId("67f6f059f5144a4eddc02b4f"); // ID del reporte a eliminar

        reporteRepo.deleteById(String.valueOf(id));

        Reporte eliminada = reporteRepo.findById(String.valueOf(id)).orElse(null);
        assertNull(eliminada);
    }

    // TEST REPO -------------------------------------------------------------------------------------------------------

    // Buscar reportes por ID de categoría
    @Test
    public void buscarPorCategoriaTest() {
        ObjectId idCategoria = new ObjectId("67f6eaee7555db6e7b134488");
        List<Reporte> reportes = reporteRepo.buscarPorCategoria(idCategoria.toHexString());
        reportes.forEach(System.out::println);

        assertFalse(reportes.isEmpty());
    }

    // Buscar reportes por nombre de ciudad
    @Test
    public void buscarPorCiudadTest() {
        List<Reporte> reportes = reporteRepo.buscarPorCiudad("Armenia");
        reportes.forEach(System.out::println);

        assertFalse(reportes.isEmpty());
    }

    // Buscar reportes registrados entre dos fechas
    @Test
    public void buscarEntreFechasTest() {
        Calendar cal = Calendar.getInstance();

        cal.set(2024, Calendar.JANUARY, 1);
        Date inicio = cal.getTime();

        cal.set(2025, Calendar.DECEMBER, 31);
        Date fin = cal.getTime();

        List<Reporte> reportes = reporteRepo.buscarEntreFechas(inicio, fin);
        reportes.forEach(System.out::println);
        assertFalse(reportes.isEmpty());
    }

    // Buscar reportes por ubicación exacta
    @Test
    public void buscarPorUbicacionExactaTest() {
        double lng = 4.53454;
        double lat = -75.67543  ;

        List<Reporte> reportes = reporteRepo.buscarPorUbicacionExacta(lat, lng);
        reportes.forEach(System.out::println);
        assertFalse(reportes.isEmpty());
    }
}