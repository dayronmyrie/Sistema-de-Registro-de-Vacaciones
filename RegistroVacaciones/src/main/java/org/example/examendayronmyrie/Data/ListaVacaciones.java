package org.example.examendayronmyrie.Data;

import org.example.examendayronmyrie.Entity.Vacaciones;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
public class ListaVacaciones {
    public List<Vacaciones> contenedorVacaciones;

    public ListaVacaciones(){
        contenedorVacaciones = new ArrayList<>();
    }

    public List<Vacaciones> getContenedorVacaciones() {
        return contenedorVacaciones;
    }

    public void setContenedorVacaciones(List<Vacaciones> contenedorVacaciones) {
        this.contenedorVacaciones = contenedorVacaciones;
    }
    public void ingresarVacaciones(Vacaciones vacaciones){
        contenedorVacaciones.add(vacaciones);
    }

    public int contarTotalDiasVacaciones() {
        int totalDias = 0;
        for (Vacaciones vacacion : contenedorVacaciones) {
            LocalDate inicio = LocalDate.of(vacacion.getAnio(), vacacion.getMes(), vacacion.getDia());
            LocalDate fin = LocalDate.of(vacacion.getAnioFin(), vacacion.getMesFin(), vacacion.getDiaFin());
            totalDias += ChronoUnit.DAYS.between(inicio, fin) + 1;
        }
        return totalDias;
    }
    @Override
    public String toString() {
        return "ListaVacaciones{" +
                "contenedorVacaciones=" + contenedorVacaciones +
                '}';
    }
}
