package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.RegistroDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapear DTO de creación hacia documento Usuario
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Usuario toDocument(RegistroDTO usuarioDTO);

    // Mapear Usuario → UsuarioDTO (asumo que tienes este DTO ya definido)
    UsuarioDTO toDTO(Usuario usuario);

    // Método personalizado para actualizar un Usuario desde un EditarUsuarioDTO
    default void actualizarUsuarioDesdeDTO(Usuario usuario, EditarUsuarioDTO dto) {
        usuario.setNombre(dto.nombre());
        usuario.setCiudad(dto.ciudad());
        usuario.setDireccion(dto.direccion());
        usuario.setTelefono(dto.numTelefono());
    }
}