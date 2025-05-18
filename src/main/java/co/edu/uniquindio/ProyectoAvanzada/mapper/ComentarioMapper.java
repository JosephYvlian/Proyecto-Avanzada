package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.comentario.CrearComentarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "idComentario", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "reporteId", ignore = true)
    @Mapping(target = "usuarioId", source = "idUsuario")
    Comentario toDocument(CrearComentarioDTO dto);

    @Mapping(source = "usuarioId", target = "idUsuario")
    @Mapping(source = "reporteId", target = "idReporte")
    ComentarioDTO toDTO(Comentario comentario);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }
}