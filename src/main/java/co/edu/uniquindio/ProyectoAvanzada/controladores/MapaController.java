package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.ReporteCalorDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mapaCalor")
public class MapaController {

    @GetMapping
    public ResponseEntity<List<ReporteCalorDTO>> obtenerMapaCalor() {
        return ResponseEntity.ok(new ArrayList<ReporteCalorDTO>());
    }

}
