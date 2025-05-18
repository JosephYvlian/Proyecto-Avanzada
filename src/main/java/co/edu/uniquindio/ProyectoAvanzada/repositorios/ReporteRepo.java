package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, ObjectId> {

    @Query("{ '_id':  ?0}")
    Optional<Reporte> buscarReportePorCodigo(String idReporte);

    // Buscar reportes por ID de categoría
    @Query("{ 'categoria': ?0 }")
    List<Reporte> buscarPorCategoria(String idCategoria);


    // Buscar reportes por ciudad
    @Query("{ 'ciudad.nombre': ?0 }")
    List<Reporte> buscarPorCiudad(String nombreCiudad);

    // Buscar reportes por usuario
    @Query("{ 'idUsuario' : ?0 }")
    List<Reporte> buscarReportesPorUsuario(String idUsuario);

    // Buscar reportes por estado
    @Query("{ 'estado' : ?0 }")
    List<Reporte> buscarReportesPorEstado(String estado);

    @Query("{'estado' :  'ACTIVO' }")
    List<Reporte> listarReportesActivos();

    List<Reporte> findByUsuarioId(ObjectId usuarioId);
}