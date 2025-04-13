package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.ReporteCalorDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.MapaCalorServicio;
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

    private final MapaCalorServicio mapaCalorServicio;

    @GetMapping
    public ResponseEntity<MensajeDTO<List<ReporteCalorDTO>>> obtenerMapaCalor() {
        List<ReporteCalorDTO> lista = mapaCalorServicio.generarMapaCalor();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }

}
