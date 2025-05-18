package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.NotificacionMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.NotificacionRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.NotificacionServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionServicioImpl implements NotificacionServicio {

    private final NotificacionMapper notificacionMapper;
    private final NotificacionRepo notificacionRepo;

    public List<NotificacionDTO> listarNotificacionesUsuario(String idUsuario) {
        List<Notificacion> notificaciones = notificacionRepo.findByUsuarioId(new ObjectId(idUsuario));

        if (notificaciones.isEmpty()) {
            return new ArrayList<>();
        }

        return notificacionMapper.toDTO(notificaciones);
    }

    // Listar por estado de lectura
    public List<Notificacion> listarPorEstado(String idUsuario, boolean leidas) {
        return notificacionRepo.findByUsuarioIdAndLeida(new ObjectId(idUsuario), leidas);
    }

    // Marcar una notificación como leída
    public void marcarComoLeida(String idNotificacion) {
        Optional<Notificacion> optional = notificacionRepo.findById(new ObjectId(idNotificacion));

        optional.ifPresent(n -> {
            n.setLeida(true);
            notificacionRepo.save(n);
        });
    }

    // Marcar todas como leídas para un usuario
    public void marcarTodasComoLeidas(String idUsuario) {
        List<Notificacion> lista = notificacionRepo.findByUsuarioIdAndLeida(new ObjectId(idUsuario), false);

        for (Notificacion noti : lista) {
            noti.setLeida(true);
        }

        notificacionRepo.saveAll(lista);
    }

}