package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toDocument(CrearCategoriaDTO dto);

    @Mapping(target = "idCategoria", source = "idCategoria")
    CategoriaDTO toDTO(Categoria categoria);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }
}
