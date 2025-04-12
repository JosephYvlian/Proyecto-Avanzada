package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.NotificacionMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionServicioImpl implements NotificacionServicio {

    private final NotificacionMapper notificacionMapper;
    private final NotificacionRepo notificacionRepo;

    @Override
    public void enviarNotificacion(NotificacionDTO notificacionDTO, String idUsuario) {
        Notificacion notificacion = notificacionMapper.toDocument(notificacionDTO);
        notificacion.setEstado(false);
        notificacionRepo.save(notificacion);
    }

    @Override
    public void marcarComoLeido(String idNotificacion) {
        Notificacion notificacion = notificacionRepo.findById(idNotificacion)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setEstado(true);
        notificacionRepo.save(notificacion);
    }
}