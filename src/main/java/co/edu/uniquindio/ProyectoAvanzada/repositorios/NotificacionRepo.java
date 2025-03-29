package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepo extends MongoRepository<Notificacion, String> {

    // Buscar notificaciones por usuario
    @Query("{ 'idUsuario' : ?0 }")
    List<Notificacion> buscarNotificacionesPorUsuario(String idUsuario);

    // Buscar notificaciones no leídas
    @Query("{ 'leida' : false }")
    List<Notificacion> buscarNotificacionesNoLeidas();

    // Buscar notificaciones por tipo
    @Query("{ 'tipo' : ?0 }")
    List<Notificacion> buscarNotificacionesPorTipo(String tipo);
}
