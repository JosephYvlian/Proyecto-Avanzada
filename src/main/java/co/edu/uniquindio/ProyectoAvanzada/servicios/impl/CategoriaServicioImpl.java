package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.CategoriaRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    @Override
    public void crearCategoria(CrearCategoriaDTO categoriaDTO) {
        boolean yaExiste = categoriaRepo.buscarPorNombre(categoriaDTO.nombre()).isPresent();

        if (yaExiste) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = Categoria.builder()
                .nombre(categoriaDTO.nombre())
                .descripcion(categoriaDTO.descripcion())
                .build();

        categoriaRepo.save(categoria);
    }

    @Override
    public void editarCategoria(EditarCategoriaDTO editCategoriaDTO, String idCategoria) {
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("La categoría no existe"));

        categoria.setNombre(editCategoriaDTO.nombre());
        categoria.setDescripcion(editCategoriaDTO.descripcion());

        categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String idCategoria) {
        if (!categoriaRepo.existsById(idCategoria)) {
            throw new RuntimeException("La categoría no existe");
        }

        categoriaRepo.deleteById(idCategoria);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepo.findAll()
                .stream()
                .map(categoria -> new CategoriaDTO(
                        categoria.getIdCategoria(),
                        categoria.getNombre(),
                        categoria.getDescripcion()
                ))
                .collect(Collectors.toList());
    }
}
