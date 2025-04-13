package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    @Query("{ 'idComentario':  ?0}")
    List<Comentario> buscarComentarioPorId(String idComentario);

    // Buscar comentarios hechos por un usuario
    @Query("{ 'idUsuario': ?0 }")
    List<Comentario> buscarPorUsuario(String idUsuario);
    // Buscar comentarios por usuario
    @Query("{ 'idUsuario' : ?0 }")
    List<Comentario> buscarComentariosPorUsuario(String idUsuario);

    // Buscar comentarios de un reporte específico
    @Query("{ 'idReporte' : ?0 }")
    List<Comentario> buscarComentariosPorReporte(String idReporte);

    // Buscar comentarios con más de X likes
    @Query("{ 'likes' : { $gt: ?0 } }")
    List<Comentario> buscarComentariosConMasDeXLikes(int cantidad);


}