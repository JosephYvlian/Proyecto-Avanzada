package co.edu.uniquindio.ProyectoAvanzada.test.java.ServicesTest;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.impl.EmailServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EmailServicioImplTest {

    private EmailServicioImpl emailServicio;

    @BeforeEach
    public void setUp() {
        emailServicio = new EmailServicioImpl();

        // Configurar valores simulados del application.properties
        ReflectionTestUtils.setField(emailServicio, "HOST", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailServicio, "PUERTO", 587);
        ReflectionTestUtils.setField(emailServicio, "USUARIO", "nexsoporte.nex@gmail.com");
        ReflectionTestUtils.setField(emailServicio, "PASSWORD", "ztbz jnyo umoc imbq");
    }

    @Test
    public void testEnviarCorreo() {
        EmailDTO emailDTO = new EmailDTO(
                "WELCOME TO NEX",
                "Your voice matters.\n" +
                        "With NEX, you can easily report incidents, hazards, or any concerns within your community.\n" +
                        "Together, we build a safer, more connected neighborhood.\n" +
                        "\n" +
                        "Let’s make a difference — one report at a time.",
                "josephstoff2@gmail.com"
        );

        assertDoesNotThrow(() -> emailServicio.enviarCorreo(emailDTO));
    }
}
