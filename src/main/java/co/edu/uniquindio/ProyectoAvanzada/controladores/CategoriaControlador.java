package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.CategoriaServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias/")
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    @GetMapping("/{idCategoria}")
    public ResponseEntity<MensajeDTO<CategoriaDTO>> obtenerCategoria(@Valid @PathVariable @NotBlank String idCategoria){
        CategoriaDTO categoria = categoriaServicio.obtenerCategoria(idCategoria);
        return ResponseEntity.ok(new MensajeDTO<>(false, categoria));
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<List<CategoriaDTO>>> listarCategorias(){
        List<CategoriaDTO> lista = categoriaServicio.listarCategorias();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearCategoria(@Valid @RequestBody CrearCategoriaDTO categoriaDTO){
        categoriaServicio.crearCategoria(categoriaDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoria creada correctamente"));
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<MensajeDTO<String>> editarCategoria(@Valid @RequestBody EditarCategoriaDTO dto, @Valid @PathVariable @NotBlank String idCategoria){
        categoriaServicio.editarCategoria(dto, idCategoria);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoria editada correctamente"));
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@Valid @PathVariable @NotBlank String idCategoria){
        categoriaServicio.eliminarCategoria(idCategoria);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Categoria eliminada correctamente"));
    }

}
