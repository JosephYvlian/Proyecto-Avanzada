package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Notificacion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepo extends MongoRepository<Notificacion, ObjectId> {

    List<Notificacion> findByUsuarioId(ObjectId idUsuario);

    List<Notificacion> findByUsuarioIdAndLeida(ObjectId usuarioId, boolean leida);
}
