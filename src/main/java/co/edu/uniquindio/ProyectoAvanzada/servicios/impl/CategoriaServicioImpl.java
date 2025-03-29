package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return List.of();
    }

    @Override
    public void crearCategoria(CrearCategoriaDTO categoriaDTO) {

    }

    @Override
    public void editarCategoria(EditarCategoriaDTO editCategoriaDTO, String idCategoria) {

    }

    @Override
    public void eliminarCategoria(String idCategoria) {

    }
}
