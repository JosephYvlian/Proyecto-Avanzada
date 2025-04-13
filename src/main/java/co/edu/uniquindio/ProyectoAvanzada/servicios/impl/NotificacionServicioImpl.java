package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.EmailDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.NotificacionMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.UsuarioRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ReporteServicio;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServicioImpl implements NotificacionServicio {

    private final NotificacionMapper notificacionMapper;
    private final NotificacionRepo notificacionRepo;
    private final EmailServicio emailServicio;
    private final UsuarioServicioImpl usuarioServicio;
    private final ReporteServicio reporteServicio;

    @Override
    public void enviarNotificacion(EnviarNotificacionDTO dto) {

        Notificacion notificacion = notificacionMapper.toDocument(dto);
        notificacion.setTituloNotificacion(dto.titulo());
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeida(false);

        notificacionRepo.save(notificacion);

        String correoDestinatario = usuarioServicio.obtenerCorreoPorId(dto.idUsuario());
        String nombreReporte = reporteServicio.obtenerReporte(dto.idReporte()).titulo();

        EmailDTO emailDTO = new EmailDTO(
                "Nueva Notificación",
                "Tienes una nueva notificación sobre el reporte de " + '"' + nombreReporte + '"'+ ":\n\n " + dto.mensaje(),
                correoDestinatario
                );

        try {
            // Enviar el correo
            emailServicio.enviarCorreo(emailDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar correo: " + e.getMessage());
        }
    }

    @Override
    public void marcarComoLeido(String idNotificacion) {
        Notificacion notificacion = notificacionRepo.findById(idNotificacion)
                .orElseThrow(() -> new RuntimeException("La notificación no existe"));

        notificacion.setLeida(true);
        notificacionRepo.save(notificacion);
    }

    @Override
    public List<NotificacionDTO> listarNotificaciones(String idUsuario) {
        List<Notificacion> notificaciones = notificacionRepo.buscarPorUsuario(idUsuario);
        List<NotificacionDTO> dtoList = new ArrayList<>();

        for (Notificacion noti : notificaciones) {
            dtoList.add(notificacionMapper.toDTO(noti));
        }

        return dtoList;
    }

    @Override
    public List<NotificacionDTO> listarNotificacionesNoLeidas(String idUsuario) {
        List<Notificacion> notificaciones = notificacionRepo.buscarNoLeidasPorUsuario(idUsuario);
        List<NotificacionDTO> dtoList = new ArrayList<>();

        for (Notificacion noti : notificaciones) {
            dtoList.add(notificacionMapper.toDTO(noti));
        }

        return dtoList;
    }

    @Override
    public List<NotificacionDTO> listarNotificacionesLeidas(String idUsuario) {
        List<Notificacion> notificaciones = notificacionRepo.buscarLeidasPorUsuario(idUsuario);
        List<NotificacionDTO> dtoList = new ArrayList<>();

        for (Notificacion noti : notificaciones) {
            dtoList.add(notificacionMapper.toDTO(noti));
        }

        return dtoList;
    }

}