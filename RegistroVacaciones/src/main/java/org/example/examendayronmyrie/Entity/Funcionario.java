package org.example.examendayronmyrie.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Funcionario {
    String id;
    String password;
    String nombre;
    List<Vacaciones> listaVacaciones;

    public Funcionario(String id, String password, String nombre, List<Vacaciones> listaVacaciones) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.listaVacaciones = listaVacaciones != null ? listaVacaciones : new ArrayList<>();
    }

    public Funcionario(String id, String password, String nombre) {
        this(id, password, nombre, new ArrayList<>());
    }

    public Funcionario() {
        this.listaVacaciones = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vacaciones> getListaVacaciones() {
        return listaVacaciones;
    }

    public void setListaVacaciones(List<Vacaciones> listaVacaciones) {
        this.listaVacaciones = listaVacaciones;
    }

    public void insertarVacacionesFuncionario(Vacaciones vacaciones) throws Exception {
        if (listaVacaciones == null) {
            listaVacaciones = new ArrayList<>();
        }

        int diasTotalesEnElAnio = contarDiasVacaciones(vacaciones.getAnio());

        if (diasTotalesEnElAnio + vacaciones.getDia() > 15) {
            throw new Exception("No se pueden tomar más de 15 días de vacaciones en el mismo año.");
        }

        listaVacaciones.add(vacaciones);
    }

    private int contarDiasVacaciones(int year) {
        if (listaVacaciones == null) {
            listaVacaciones = new ArrayList<>();
        }

        int totalDias = 0;
        for (Vacaciones vacaciones : listaVacaciones) {
            if (vacaciones.getAnio() == year) {
                totalDias += vacaciones.getDia();
            }
        }
        return totalDias;
    }
    public boolean cancelarVacacionesPorId(String id) {
        if (listaVacaciones == null) {
            return false;
        }
        Iterator<Vacaciones> iterator = listaVacaciones.iterator();
        while (iterator.hasNext()) {
            Vacaciones vacaciones = iterator.next();
            if (vacaciones.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean cancelarVacacionesPorIndice(int indiceVacacion) {
        if (listaVacaciones == null || indiceVacacion < 0 || indiceVacacion >= listaVacaciones.size()) {
            return false;
        }
        listaVacaciones.remove(indiceVacacion);
        return true;
    }


}


