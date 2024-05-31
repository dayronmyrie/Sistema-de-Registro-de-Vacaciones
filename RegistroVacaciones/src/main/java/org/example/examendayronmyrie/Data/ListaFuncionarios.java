package org.example.examendayronmyrie.Data;
import org.example.examendayronmyrie.Entity.Funcionario;

import java.util.List;
import java.util.ArrayList;

public class ListaFuncionarios {
    public List<Funcionario> contenedorFuncionarios;

    public ListaFuncionarios(){
        contenedorFuncionarios = new ArrayList<>();
        Funcionario func1 = new Funcionario("deivert", "1234", "Deivert");
        Funcionario func2 = new Funcionario("dayron", "1234", "Dayron");

        contenedorFuncionarios.add(func1);
        contenedorFuncionarios.add(func2);
    }
    public Funcionario buscarFuncionarios(String id){
        for (Funcionario funcionario : contenedorFuncionarios){
            if (funcionario.getId().equals(id)){
                return funcionario;
            }
        } return null;
    }
    public boolean login(String id, String password){
        for (Funcionario funcionario : contenedorFuncionarios){
            if (funcionario.getId().equals(id) && funcionario.getPassword().equals(password)){
                return true;
            }
        } return false;
    }
}
