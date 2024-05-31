package org.example.examendayronmyrie.Controller;

import org.example.examendayronmyrie.Data.*;
import org.example.examendayronmyrie.Entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class FuncionarioController {
    ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
    Funcionario funcionario = new Funcionario();

    @GetMapping("/")
    public String mostrarIndex(){
        return "indexLogin";
    }
    @GetMapping("/login")
    public String indexLogin(){
        return "indexLogin";

    }
    @PostMapping("/inicioSesion")
    public String inicioSesion(@RequestParam("id")String id, @RequestParam("password")String password, Model model){
        if (listaFuncionarios.login(id, password)){
            funcionario = listaFuncionarios.buscarFuncionarios(id);
            model.addAttribute("funcionario", funcionario);
            model.addAttribute("listaFuncionarios", listaFuncionarios);

            return "indexVacaciones";
        } else { return "indexLogin";
        }
    }
}
