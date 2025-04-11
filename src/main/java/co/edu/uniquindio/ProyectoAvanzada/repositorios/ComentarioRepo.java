package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    @Query("{ 'idComentario':  ?0}")
    List<Comentario> buscarComentarioPorId(String idComentario);

    // Buscar comentarios hechos por un usuario
    @Query("{ 'idUsuario': ?0 }")
    List<Comentario> buscarPorUsuario(String idUsuario);

    // Buscar comentarios asociados a un reporte
    @Query("{ 'idReporte': ?0 }")
    List<Comentario> buscarPorReporte(String idReporte);



}
