package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    NotificacionDTO toDTO(Notificacion notificacion);
    List<NotificacionDTO> toDTO(List<Notificacion> notificaciones);
}
