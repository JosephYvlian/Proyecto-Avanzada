package co.edu.uniquindio.ProyectoAvanzada.mapper;

import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    Reporte toDocument(CrearReporteDTO dto);

    ReporteDTO toDTO(Reporte reporte);

    default void actualizarReporteDesdeDTO(Reporte reporte, EditarReporteDTO dto) {
        reporte.setTitulo(dto.titulo());
        reporte.setCategoria(dto.categoria());
        reporte.setDescripcion(dto.descripcion());
        reporte.setUbicacion(dto.ubicacion());
        reporte.setEstado(dto.estado());
        reporte.setImagenes(dto.imagenes());
    }
}
