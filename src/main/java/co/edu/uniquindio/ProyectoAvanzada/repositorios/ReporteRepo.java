package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, String> {

    // Buscar reporte por ID
    @Query("{ '_id' : ?0 }")
    Optional<Reporte> buscarReportePorId(String idReporte);

    // Buscar reportes por usuario
    @Query("{ 'idUsuario' : ?0 }")
    List<Reporte> buscarReportesPorUsuario(String idUsuario);

    // Buscar reportes por estado
    @Query("{ 'estado' : ?0 }")
    List<Reporte> buscarReportesPorEstado(String estado);

    @Query("{'estado' :  'ACTIVO' }")
    List<Reporte> listarReportesActivos();

}
