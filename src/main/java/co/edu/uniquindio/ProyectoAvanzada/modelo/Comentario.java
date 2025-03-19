package co.edu.uniquindio.ProyectoAvanzada.modelo;

public class Comentario {

    private Reporte reporte;

    public Comentario(Reporte reporte) {
        this.reporte = reporte;

        reporte.getCategoria();
    }
}
