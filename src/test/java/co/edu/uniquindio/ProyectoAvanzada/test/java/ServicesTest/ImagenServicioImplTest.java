package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.servicios.impl.ImagenServicioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import java.io.InputStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ImagenServicioImplTest {

    @Test
    public void testSubirImagen() throws Exception {
        ImagenServicioImpl servicio = new ImagenServicioImpl();

        // Cargar un archivo de prueba desde los recursos
        InputStream imagenStream = getClass().getResourceAsStream("/logo.png");
        assertNotNull(imagenStream, "No se encontró el archivo de prueba");

        MockMultipartFile imagen = new MockMultipartFile(
                "archivo",
                "test-image.jpg",
                "image/jpeg",
                imagenStream
        );

        Map resultado = servicio.subirImagen(imagen);

        assertNotNull(resultado);
        assertTrue(resultado.containsKey("url"), "La respuesta debe contener una URL de la imagen subida");

        System.out.println("Imagen subida con éxito: " + resultado.get("url"));
    }
}
