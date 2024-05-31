package org.example.examendayronmyrie.Controller;
import org.example.examendayronmyrie.Data.*;
import org.example.examendayronmyrie.Entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VacacionesController {

    ListaVacaciones listaVacaciones = new ListaVacaciones();
    private List<Vacaciones> listaVacacionesAux = new ArrayList<>();
    Vacaciones vacacionesAux = new Vacaciones();
    Funcionario funcionario = new Funcionario();

    @GetMapping("/indexVacaciones")
    public String mostrarIndexVacaciones(){
        return "indexVacaciones";
    }
    @GetMapping("/listadoVacaciones")
    public String listadoVacaciones(Model model){
        model.addAttribute("vacaciones", vacacionesAux);
        model.addAttribute("listaVacaciones", listaVacaciones);
        model.addAttribute("listaVacacionesAux", listaVacacionesAux);
        model.addAttribute("funcionario", funcionario);
        return "listadoVacaciones";
    }
    @PostMapping("/registrarVacaciones")
    public String registrarVacaciones(
            @RequestParam("diaFin") Integer diaFin,
            @RequestParam("mesFin") Integer mesFin,
            @RequestParam("yearFin") Integer yearFin,
            @RequestParam("diaInicio") Integer diaInicio,
            @RequestParam("mesInicio") Integer mesInicio,
            @RequestParam("yearInicio") Integer yearInicio,
            Model model) throws Exception {

        // Crear las fechas de inicio y fin de las nuevas vacaciones
        LocalDate fechaInicio = LocalDate.of(yearInicio, mesInicio, diaInicio);
        LocalDate fechaFin = LocalDate.of(yearFin, mesFin, diaFin);

        // Verificar si la fecha de inicio es mayor que la fecha de fin
        if (fechaInicio.isAfter(fechaFin)) {
            model.addAttribute("mensaje", "La fecha de inicio de vacaciones no puede ser posterior a la fecha de finalización");
            return "errorFecha"; // Redirigir a una página de error o mostrar un mensaje de error
        }

        // Verificar si las nuevas vacaciones chocan con las vacaciones existentes
        for (Vacaciones vacacion : listaVacacionesAux) {
            LocalDate inicioExistente = LocalDate.of(vacacion.getAnio(), vacacion.getMes(), vacacion.getDia());
            LocalDate finExistente = LocalDate.of(vacacion.getAnioFin(), vacacion.getMesFin(), vacacion.getDiaFin());

            // Verificar si hay superposición de fechas
            if (!(fechaInicio.isAfter(finExistente) || fechaFin.isBefore(inicioExistente))) {
                model.addAttribute("mensaje", "Las nuevas vacaciones chocan con las vacaciones ya registradas");
                return "errorFecha"; // Redirigir a una página de error o mostrar un mensaje de error
            }
        }

        // Calcular la diferencia en días entre las dos fechas de las nuevas vacaciones
        long diasDeVacaciones = ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;

        // Verificar si la cantidad de días de vacaciones solicitadas es mayor a 15
        if (diasDeVacaciones > 15) {
            model.addAttribute("mensaje", "No se pueden pedir más de 15 días de vacaciones en una sola solicitud");
            return "errorFecha"; // Redirigir a una página de error o mostrar un mensaje de error
        }

        // Calcular el total de días de vacaciones ya registradas
        int totalDiasVacaciones = 0;
        for (Vacaciones vacacion : listaVacacionesAux) {
            LocalDate inicio = LocalDate.of(vacacion.getAnio(), vacacion.getMes(), vacacion.getDia());
            LocalDate fin = LocalDate.of(vacacion.getAnioFin(), vacacion.getMesFin(), vacacion.getDiaFin());
            totalDiasVacaciones += ChronoUnit.DAYS.between(inicio, fin) + 1;
        }

        // Verificar si al agregar las nuevas vacaciones se supera el límite de 15 días
        if (totalDiasVacaciones + diasDeVacaciones > 15) {
            model.addAttribute("mensaje", "No se pueden tener más de 15 días de vacaciones registradas en total");
            return "errorFecha"; // Redirigir a una página de error o mostrar un mensaje de error
        }

        // Si la cantidad de días es válida, registrar las vacaciones
        Vacaciones vacacionesAux = new Vacaciones();
        vacacionesAux.setDia(diaInicio);
        vacacionesAux.setMes(mesInicio);
        vacacionesAux.setAnio(yearInicio);
        vacacionesAux.setDiaFin(diaFin);
        vacacionesAux.setMesFin(mesFin);
        vacacionesAux.setAnioFin(yearFin);

        funcionario.insertarVacacionesFuncionario(vacacionesAux);
        listaVacacionesAux.add(vacacionesAux);

        // Actualizar el total de días de vacaciones
        totalDiasVacaciones += diasDeVacaciones;

        model.addAttribute("mensaje", "Vacaciones registradas exitosamente");
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("listaVacaciones", listaVacacionesAux);
        model.addAttribute("listaVacacionesFuncionario", funcionario.getListaVacaciones());
        model.addAttribute("totalDiasVacaciones", totalDiasVacaciones);

        return "listadoVacaciones";
    }





    @PostMapping("/cancelarVacaciones")
    public String cancelarVacaciones(@RequestParam("indiceVacacion") int indiceVacacion, Model model) {
        boolean result = funcionario.cancelarVacacionesPorIndice(indiceVacacion);
        if (result) {
            model.addAttribute("mensaje", "Vacaciones canceladas exitosamente");
        } else {
            model.addAttribute("mensaje", "Error al cancelar las vacaciones");
        }
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("listaVacaciones", funcionario.getListaVacaciones());
        return "listadoVacaciones";
    }

}
