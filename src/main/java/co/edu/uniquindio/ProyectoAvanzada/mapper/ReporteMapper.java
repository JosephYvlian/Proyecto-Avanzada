package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface  ReporteMapper {

    @Mapping(target = "categoriaId", source = "categoria")
    @Mapping(target = "ubicacion", source = "ubicacion")
    Reporte toDocument(CrearReporteDTO dto);

    ReporteDTO toDTO(Reporte reporte);

    @Mapping(target = "idReporte", ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "categoriaId", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "votosImportancia", ignore = true)
    @Mapping(target = "historial", ignore = true)
    void EditarReporteDTO(EditarReporteDTO dto, @MappingTarget Reporte reporte);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }


}