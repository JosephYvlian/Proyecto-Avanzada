package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServicioImpl implements EmailServicio {

    @Value("${spring.mail.host}")
    private String HOST;

    @Value("${spring.mail.port}")
    private int PUERTO;

    @Value("${spring.mail.username}")
    private String USUARIO;

    @Value("${spring.mail.password}")
    private String PASSWORD;

    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("nexsoporte.nex@gmail.com")
                .to(emailDTO.correoDestino())
                .withSubject(emailDTO.asunto())
                .withPlainText(emailDTO.cuerpo())
                .buildEmail();


        try(Mailer mailer = MailerBuilder
                .withSMTPServer(HOST, PUERTO, USUARIO, PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {



            mailer.sendMail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
