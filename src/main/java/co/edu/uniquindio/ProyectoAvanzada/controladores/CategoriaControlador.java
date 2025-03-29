package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.CrearCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.categoria.EditarCategoriaDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias/")
public class CategoriaControlador {

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        return ResponseEntity.ok(new ArrayList<CategoriaDTO>());
    }

    @PostMapping
    public ResponseEntity<MensajeDTO<CrearCategoriaDTO>> crearCategoria(@RequestBody CrearCategoriaDTO categoriaDTO){
        return ResponseEntity.ok(new MensajeDTO<CrearCategoriaDTO>(false, null));
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<MensajeDTO<EditarCategoriaDTO>> editarCategoria(@RequestBody EditarCategoriaDTO editCategoriaDTO, @PathVariable String idCategoria){
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@PathVariable String idCategoria){
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

}
