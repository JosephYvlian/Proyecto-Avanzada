package co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public interface CategoriaServicio {

    void crearCategoria(CrearCategoriaDTO categoriaDTO);

    void editarCategoria(EditarCategoriaDTO editCategoriaDTO, String idCategoria);

    void eliminarCategoria(String idCategoria);

    List<CategoriaDTO> listarCategorias();
}
