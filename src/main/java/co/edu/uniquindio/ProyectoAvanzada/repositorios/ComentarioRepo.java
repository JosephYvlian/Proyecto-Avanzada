package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, ObjectId> {

    List<Comentario> findByUsuarioId(ObjectId usuarioId);
    List<Comentario> findByReporteId(ObjectId reporteId);


}