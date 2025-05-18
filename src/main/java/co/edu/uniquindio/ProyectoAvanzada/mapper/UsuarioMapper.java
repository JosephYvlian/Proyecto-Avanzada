package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "rol", constant = "CLIENTE")
    @Mapping(target = "estadoCuenta", constant = "INACTIVO")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Usuario toDocument(CrearUsuarioDTO dto);

    UsuarioDTO toDTO(Usuario usuario);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }

    void editarUsuarioDTO(EditarUsuarioDTO dto, @MappingTarget Usuario usuario);
}