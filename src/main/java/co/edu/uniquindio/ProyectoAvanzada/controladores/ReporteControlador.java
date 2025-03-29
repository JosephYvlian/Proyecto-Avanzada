package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")

public class ReporteControlador {

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearReporte(@Valid @RequestBody CrearReporteDTO reporte){
        return ResponseEntity.ok(new MensajeDTO<>(false, "Nuevo reporte creado correctamente"));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listarReporte(){
        return ResponseEntity.ok(new ArrayList<ReporteDTO>());
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<ReporteDTO>> obtenerReporte(@PathVariable String idReporte){
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @PutMapping("/{idReporte]")
    public  ResponseEntity<MensajeDTO<ReporteDTO>> editarReporte(@PathVariable String idReporte, @RequestBody EditarReporteDTO reporte){
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> eliminarReporte(@PathVariable String idReporte){
        return ResponseEntity.ok(new MensajeDTO<>(false, null));
    }
}
