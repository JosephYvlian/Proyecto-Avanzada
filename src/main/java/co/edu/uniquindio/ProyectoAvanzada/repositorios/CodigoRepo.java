package co.edu.uniquindio.ProyectoAvanzada.repositorios;


import co.edu.uniquindio.ProyectoAvanzada.modelo.vo.CodigoValidacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CodigoRepo extends MongoRepository<CodigoValidacion, String> {

    Optional<CodigoValidacion> buscarPorEmail(String email);

}
