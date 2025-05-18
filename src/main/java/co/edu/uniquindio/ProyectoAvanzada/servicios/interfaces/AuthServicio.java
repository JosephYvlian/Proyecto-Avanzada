package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.TokenDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.autenticacion.LoginDTO;

public interface AuthServicio {

    TokenDTO login(LoginDTO login) throws Exception;
}
