package co.edu.uniquindio.ProyectoAvanzada.servicios.impl;

import co.edu.uniquindio.ProyectoAvanzada.dto.ReporteCalorDTO;
import co.edu.uniquindio.ProyectoAvanzada.modelo.documentos.Reporte;
import co.edu.uniquindio.ProyectoAvanzada.repositorios.ReporteRepo;
import co.edu.uniquindio.ProyectoAvanzada.servicios.interfaces.MapaCalorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MapaCalorServicioImpl implements MapaCalorServicio {
    private final ReporteRepo reporteRepo;

    @Override
    public List<ReporteCalorDTO> generarMapaCalor() {
        List<Reporte> reportes = reporteRepo.findAll();
        List<ReporteCalorDTO> puntosCalor = new ArrayList<>();

        double intensidad = 0.001; // ~111 metros

        for (Reporte reporte : reportes) {
            boolean agrupado = false;

            for (int i = 0; i < puntosCalor.size(); i++) {
                ReporteCalorDTO punto = puntosCalor.get(i);

                if (Math.abs(punto.latitud() - reporte.getUbicacion().getLatitud()) < intensidad &&
                        Math.abs(punto.longitud() - reporte.getUbicacion().getLongitud()) < intensidad) {

                    // Aumentamos la intensidad en el punto agrupado
                    puntosCalor.set(i, new ReporteCalorDTO(punto.latitud(), punto.longitud(), punto.intensidad() + 1));
                    agrupado = true;
                    break;
                }
            }

            if (!agrupado) {
                puntosCalor.add(new ReporteCalorDTO(reporte.getUbicacion().getLatitud(), reporte.getUbicacion().getLongitud(), 1));
            }
        }

        return puntosCalor;

    }

}
