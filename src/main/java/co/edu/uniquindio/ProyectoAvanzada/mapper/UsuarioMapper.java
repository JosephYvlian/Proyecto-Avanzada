package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.CrearUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.EditarUsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapear DTO de creación hacia documento Usuario
    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono")
            // Los demás campos se mapearán automáticamente porque se llaman igual
    })
    Usuario toDocument(CrearUsuarioDTO usuarioDTO);

    // Mapear Usuario → UsuarioDTO (asumo que tienes este DTO ya definido)
    UsuarioDTO toDTO(Usuario usuario);

    // Método personalizado para actualizar un Usuario desde un EditarUsuarioDTO
    default void actualizarUsuarioDesdeDTO(Usuario usuario, EditarUsuarioDTO dto) {
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setCiudad(dto.ciudad());
        usuario.setDireccion(dto.direccion());
        usuario.setTelefono(dto.numTelefono());

        if (dto.contrasena() != null && !dto.contrasena().isBlank()) {
            usuario.setPassword(dto.contrasena());
        }
    }
}
