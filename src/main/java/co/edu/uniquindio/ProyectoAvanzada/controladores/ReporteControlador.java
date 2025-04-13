package co.edu.uniquindio.ProyectoAvanzada.controladores;

import co.edu.uniquindio.ProyectoAvanzada.dto.MensajeDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.CrearReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.EditarReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.dto.reporte.ReporteDTO;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.ReporteServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")

public class ReporteControlador {

    private final ReporteServicio reporteServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearReporte(@Valid @RequestBody CrearReporteDTO reporte){

        reporteServicio.crearReporte(reporte);

        return ResponseEntity.ok(new MensajeDTO<>(false, "Nuevo reporte creado correctamente"));
    }

    @GetMapping
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> listarReporte(){
        List<ReporteDTO> lista = reporteServicio.listarReporte();

        return  ResponseEntity.ok(new MensajeDTO<>(false, lista));

    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> obtenerReporte(@Valid @NotBlank(message = "El idReporte no puede estar vacio") @PathVariable String idReporte){
        reporteServicio.obtenerReporte(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Datos del reporte obtenidos"));
    }

    @PutMapping("/{idReporte}")
    public  ResponseEntity<MensajeDTO<String>> editarReporte(@Valid @NotBlank(message = "El idReporte no puede estar vacio") @PathVariable String idReporte,
                                                             @Valid @RequestBody EditarReporteDTO reporte){
        reporteServicio.editarReporte(idReporte, reporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte eliminado correctamente"));
    }

    @DeleteMapping("/{idReporte}")
    public ResponseEntity<MensajeDTO<String>> eliminarReporte(@Valid @NotBlank(message = "El idReporte no puede estar vacio") @PathVariable String idReporte){
        reporteServicio.eliminarReporte(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte eliminado correctamente"));
    }

    @PutMapping("/{idReporte}/importante")
    public ResponseEntity<MensajeDTO<String>> marcarImportante(@Valid @NotBlank(message = "El idReporte no puede estar vacio") @PathVariable String idReporte) {
        reporteServicio.marcarReporte(idReporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte marcado como importante"));
    }

}
