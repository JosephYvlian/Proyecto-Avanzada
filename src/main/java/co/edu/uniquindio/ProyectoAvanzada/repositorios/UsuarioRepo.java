package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    @Query("{ 'cedula':  ?0}")
    Optional<Usuario> buscarUsuarioPorCedula(String cedula);

    // Buscar usuario por correo
    @Query("{ 'email' : ?0 }")
    Optional<Usuario> buscarUsuarioPorEmail(String email);

    // Buscar usuario por nombre de usuario
    @Query("{ 'username' : ?0 }")
    Optional<Usuario> buscarUsuarioPorUsername(String username);

    // Buscar usuarios con más de X reportes
    @Query("{ 'reportes' : { $size : { $gt: ?0 } } }")
    List<Usuario> buscarUsuariosConMasDeXReportes(int cantidad);


}