package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, String> {

    @Query("{ 'codigoReporte':  ?0}")
    Optional<Reporte> buscarReportePorCodigo(String codigoReporte);

    // Buscar reportes por ID de categoría
    @Query("{ 'categoria': ?0 }")
    List<Reporte> buscarPorCategoria(String idCategoria);

    // Buscar reportes por ciudad
    @Query("{ 'ciudad.nombre': ?0 }")
    List<Reporte> buscarPorCiudad(String nombreCiudad);

    // Buscar reportes registrados entre dos fechas
    @Query("{ 'fecha': { $gte: ?0, $lte: ?1 } }")
    List<Reporte> buscarEntreFechas(Date fechaInicio, Date fechaFin);

    // Buscar reportes por coordenadas exactas (latitud y longitud)
    @Query("{ 'ubicacion.latitud': ?0, 'ubicacion.longitud': ?1 }")
    List<Reporte> buscarPorUbicacionExacta(Double lat, Double lng);
}
