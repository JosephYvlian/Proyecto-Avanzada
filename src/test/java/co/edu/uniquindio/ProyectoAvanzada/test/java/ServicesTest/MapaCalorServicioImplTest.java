package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.ReporteCalorDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.modelo.enums.Ciudad;
import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.Ubicacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.MapaCalorServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MapaCalorServicioImplTest {

    @Autowired
    private MapaCalorServicio mapaCalorServicio;

    @Autowired
    private ReporteRepo reporteRepo;

    private static final double LATITUD_TEST = 4.53454;
    private static final double LONGITUD_TEST = -75.67543;

    @BeforeAll
    static void setup() {
        System.out.println(">> Iniciando pruebas de MapaCalorServicio...");
    }

    @Test
    public void testGenerarMapaCalor() {
        // Preparamos un reporte para asegurarnos de que haya al menos un punto
        Reporte reporte = Reporte.builder()
                .titulo("Punto de calor")
                .descripcion("Test punto mapa de calor")
                .ciudad(Ciudad.ARMENIA)
                .ubicacion(new Ubicacion(LATITUD_TEST, LONGITUD_TEST, "La Isabela"))
                .imagenes(List.of("img1.png"))
                .build();

        reporteRepo.save(reporte);

        List<ReporteCalorDTO> puntos = mapaCalorServicio.generarMapaCalor();

        assertNotNull(puntos);
        assertFalse(puntos.isEmpty(), "Debe haber al menos un punto de calor generado");

        // Validamos que exista al menos un punto en la ubicación aproximada
        boolean existe = puntos.stream().anyMatch(p ->
                Math.abs(p.latitud() - LATITUD_TEST) < 0.001 &&
                        Math.abs(p.longitud() - LONGITUD_TEST) < 0.001
        );

        assertTrue(existe, "Debe haberse generado un punto de calor cerca de la ubicación registrada");
    }
}
