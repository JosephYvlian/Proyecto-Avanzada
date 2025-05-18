package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, ObjectId> {

    // Buscar categoría por nombre exacto
    @Query("{ 'nombre': ?0 }")
    Optional<Categoria> buscarPorNombre(String nombre);

}
