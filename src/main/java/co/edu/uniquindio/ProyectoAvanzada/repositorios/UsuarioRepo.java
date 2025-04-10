package co.edu.uniquindio.ProyectoAvanzada.repositorios;

import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {

    // Buscar un usuario por correo (por ejemplo, para login)
    @Query("{ 'email': ?0 }")
    Optional<Usuario> buscarPorEmail(String email);

    // Buscar usuarios por rol (CLIENTE, ADMINISTRADOR)
    @Query("{ 'rol': ?0 }")
    List<Usuario> buscarPorRol(String rol);

    // Buscar usuarios por ciudad exacta
    @Query("{ 'ciudad': ?0 }")
    List<Usuario> buscarPorCiudad(String ciudad);

    // Buscar usuarios por estado de cuenta (ACTIVO, INACTIVO)
    @Query("{ 'estadoCuenta': ?0 }")
    List<Usuario> buscarPorEstadoCuenta(String estado);
}
