package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;
import co.edu.uniquindio.ProyectoAvanzada.servicios.impl.ImagenServicioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ImagenServicioImplTest {

    private final ImagenServicioImpl imagenServicio = new ImagenServicioImpl();

    @Test
    void testSubirImagenDesdePC() throws Exception {
        // Ruta local absoluta de la imagen en tu PC
        File file = new File("C:\\Users\\josep\\Documents\\GitHub\\Proyecto-Avanzada\\src\\main\\resources\\logo.png");
        FileInputStream fis = new FileInputStream(file);

        // Crear el MultipartFile simulado
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                file.getName(),
                "image/png",  // Asegúrate del MIME correcto si es JPG: "image/jpeg"
                fis
        );

        // Subir imagen a Cloudinary
        Map resultado = imagenServicio.subirImagen(mockFile);

        // Verificar resultado
        assertNotNull(resultado);
        assertTrue(resultado.containsKey("url"));
        System.out.println("📷 Imagen subida correctamente: " + resultado.get("url"));

        // Opcional: eliminar la imagen después del test
        // String publicId = (String) resultado.get("public_id");
        // imagenServicio.eliminarImagen(publicId);
    }
}
