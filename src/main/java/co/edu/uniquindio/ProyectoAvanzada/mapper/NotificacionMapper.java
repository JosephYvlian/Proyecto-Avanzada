package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.EnviarNotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.notificacion.NotificacionDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    @Mapping(source = "titulo", target = "tituloNotificacion")
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    Notificacion toDocument(EnviarNotificacionDTO dto);

    NotificacionDTO toDTO(Notificacion notificacion);
}
