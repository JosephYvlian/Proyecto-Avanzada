package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.EstadoReporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ReporteServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReporteServicioImplTest {

    @Autowired
    private ReporteServicio reporteServicio;

    @Autowired
    private ReporteRepo reporteRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    private static String reporteExistenteID = "67f9f81d8158242db1ee7d02";

    private static final String idCategoriaPrueba = "67f9f623e2151f60f7ac6a9b"; //ID válido de Mongo

    private static Categoria categoriaDePrueba;

    @Test
    public void testCrearReporte() {
        categoriaDePrueba = categoriaRepo.findById(idCategoriaPrueba).orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoriaPrueba));

        CrearReporteDTO dto = new CrearReporteDTO(
                "Fuego en el parque",
                categoriaDePrueba,
                "Se observó un incendio leve",
                new Ubicacion(4.321, -75.123, "Armenia"),
                EstadoReporte.PENDIENTE,
                List.of("imagen1.png", "imagen2.png")
        );

        reporteServicio.crearReporte(dto);

        List<Reporte> reportes = reporteRepo.findAll();
        assertFalse(reportes.isEmpty());

        Reporte ultimo = reportes.getLast();
        reporteExistenteID = ultimo.getIdReporte();
        assertEquals(EstadoReporte.PENDIENTE, ultimo.getEstado());
    }

    @Test
    public void testObtenerReporte() {
        ReporteDTO dto = reporteServicio.obtenerReporte(reporteExistenteID);
        assertNotNull(dto);
        assertEquals("Fuego en el parque", dto.titulo());
    }

    @Test
    public void testListarReportes() {
        List<ReporteDTO> reportes = reporteServicio.listarReporte();
        assertFalse(reportes.isEmpty());

        //PRUEBA PARA VER LA LISTA DE REPORTES
        System.out.println(reportes);
    }

    @Test
    public void testEditarReporte() {
        categoriaDePrueba = categoriaRepo.findById(idCategoriaPrueba)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoriaPrueba));

        EditarReporteDTO editarDTO = new EditarReporteDTO(
                "Incendio editado",
                categoriaDePrueba,
                "Descripción actualizada",
                new Ubicacion(4.322, -75.124, "Armenia"),
                EstadoReporte.RESUELTO,
                List.of("img_editada.png")
        );

        reporteServicio.editarReporte(reporteExistenteID, editarDTO);

        Reporte actualizado = reporteRepo.findById(reporteExistenteID).orElseThrow();
        assertEquals("Incendio editado", actualizado.getTitulo());
        assertEquals(EstadoReporte.RESUELTO, actualizado.getEstado());
    }

    @Test
    public void testMarcarReporte() {
        Reporte antes = reporteRepo.findById(reporteExistenteID).orElseThrow();
        int votosAntes = antes.getVotosImportancia() != null ? antes.getVotosImportancia() : 0;

        reporteServicio.marcarReporte(reporteExistenteID);

        Reporte despues = reporteRepo.findById(reporteExistenteID).orElseThrow();
        assertEquals(votosAntes + 1, despues.getVotosImportancia());
    }

    @Test
    public void testEliminarReporte() {
        reporteServicio.eliminarReporte(reporteExistenteID);

        Reporte eliminado = reporteRepo.findById(reporteExistenteID).orElseThrow();
        assertEquals(EstadoReporte.ELIMINADO, eliminado.getEstado());
    }
}
