package org.example.examendayronmyrie.Entity;
import java.util.List;
import java.util.ArrayList;

public class Vacaciones {
    Integer dia;
    Integer mes;
    Integer anio;
    Integer diaFin;
    Integer mesFin;
    Integer anioFin;
    String id;

    public Vacaciones(Integer dia, Integer mes, Integer anio, String id) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.id = id;
    }

    public Vacaciones(Integer dia, Integer mes, Integer anio, Integer diaFin, Integer mesFin, Integer anioFin) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.diaFin = diaFin;
        this.mesFin = mesFin;
        this.anioFin = anioFin;

    }

    public Vacaciones() {
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(Integer diaFin) {
        this.diaFin = diaFin;
    }

    public Integer getMesFin() {
        return mesFin;
    }

    public void setMesFin(Integer mesFin) {
        this.mesFin = mesFin;
    }

    public Integer getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(Integer anioFin) {
        this.anioFin = anioFin;
    }
}
