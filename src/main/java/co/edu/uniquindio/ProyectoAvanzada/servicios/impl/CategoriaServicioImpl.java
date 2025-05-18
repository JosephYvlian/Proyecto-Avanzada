package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.mapper.CategoriaMapper;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;
    private final CategoriaMapper categoriaMapper;


    @Override
    public CategoriaDTO obtenerCategoria(String idCategoria) {
        ObjectId objectId = new ObjectId(idCategoria);
        Categoria categoria = categoriaRepo.findById(objectId)
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));

        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public void crearCategoria(CrearCategoriaDTO dto) {
        boolean yaExiste = categoriaRepo.buscarPorNombre(dto.nombre()).isPresent();

        if (yaExiste) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = categoriaMapper.toDocument(dto);
        categoriaRepo.save(categoria);
    }

    @Override
    public void editarCategoria(EditarCategoriaDTO dto, String idCategoria) {
        Categoria categoria = categoriaRepo.findById(new ObjectId(idCategoria))
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));

        boolean yaExiste = categoriaRepo.buscarPorNombre(dto.nombre()).isPresent();
        if (yaExiste) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        categoria.setImagen(dto.imagen());
        categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String idCategoria) {

        ObjectId objectId = new ObjectId(idCategoria);
        if (!categoriaRepo.existsById(objectId)) {
            throw new RuntimeException("La categoría no existe");
        }

        categoriaRepo.deleteById(objectId);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepo.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
