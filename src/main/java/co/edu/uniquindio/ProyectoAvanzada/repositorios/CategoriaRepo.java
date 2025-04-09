package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, String> {

    @Query("{'idCategoria' :  ?0 }")
    Optional<Categoria> buscarPorId(String id);

    @Query("{'nombre' : ?0}")
    List<Categoria> buscarPorNombre(String nombre);



}
