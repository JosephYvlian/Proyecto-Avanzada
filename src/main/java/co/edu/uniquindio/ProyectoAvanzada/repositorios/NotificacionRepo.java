package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepo extends MongoRepository<Notificacion, String> {

    // Buscar notificaciones de un usuario
    @Query("{ 'idUsuario': ?0 }")
    List<Notificacion> buscarPorUsuario(String idUsuario);

    // Buscar notificaciones de un reporte
    @Query("{ 'idReporte': ?0 }")
    List<Notificacion> buscarPorReporte(String idReporte);

    // Buscar notificaciones no leídas de un usuario
    @Query("{ 'idUsuario': ?0, 'estado': false }")
    List<Notificacion> buscarNoLeidasPorUsuario(String idUsuario);

    // Buscar notificaciones leídas de un usuario
    @Query("{ 'idUsuario': ?0, 'estado': true }")
    List<Notificacion> buscarLeidasPorUsuario(String idUsuario);
}
