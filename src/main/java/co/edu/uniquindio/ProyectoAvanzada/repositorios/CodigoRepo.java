package co.edu.uniquindio.ProyectoAvanzada.repositorios;


import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodigoRepo extends MongoRepository<CodigoValidacion, String> {

    @Query("{ 'email' : ?0 }")
    Optional<CodigoValidacion> buscarPorEmail(String email);

}
