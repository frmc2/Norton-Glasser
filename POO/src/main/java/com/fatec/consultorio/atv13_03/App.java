package com.fatec.consultorio.atv13_03;


import java.time.LocalDate;
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {

        int x = 1;

        Paciente p1 = new Paciente();
        try {
            p1.setCodigo(x++);
            p1.setNome("Menphid Depay");
            p1.setCpf("428541088-50");
            p1.setTelefone("1197293-1385");
            p1.setGenero("Masculino");
            p1.setIdade(19);
            p1.mostrar();

            var p2 = new Paciente(x++, "Breno Bidon", "1234", "1234", "Masculino", 18);
            p2.mostrar();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. " + e.getMessage());
        }

        Funcionario m1 = new Medico();
        try {
            m1.setNome("Dr. Yuri Alberto");
            ((Medico) m1).setCrm("1234567sp");
            m1.setTelefone("(11) 97777-6666");
            ((Medico) m1).setEspecialidade("Cardiologia");
            m1.setSenha("senha123");

            m1.acessar();
            ((Medico) m1).mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Funcionario maria = new Recepcionista();
        try {
            maria.setNome("Maria Flor");
            ((Recepcionista) maria).setCpf("28738273849");
            maria.setTelefone("11 1293213123");
            maria.setSenha("senha123");

            maria.acessar();
            ((Recepcionista) maria).mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Agenda agenda1 = new Agenda();
        try {
            agenda1.setData(LocalDate.now());
            agenda1.setHora(LocalTime.now());
            agenda1.setMedico((Medico) m1);
            agenda1.setPaciente(p1);
            agenda1.mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Consulta consulta1 = new Consulta();
        try {
            consulta1.setData(LocalDate.now());
            consulta1.setHora(LocalTime.now());
            consulta1.setMedico((Medico) m1);
            consulta1.setPaciente(p1);
            consulta1.setMotivo("Dor de cabeça");
            consulta1.setHistorico("Sem problemas recorrentes");
            consulta1.mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Procedimento exame1 = new Exame();
        try {
            exame1.setData(LocalDate.now());
            exame1.setDescritivo("Exame de Sangue");
            exame1.consultar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Procedimento receita1 = new Receita();
        try {
            receita1.setData(LocalDate.now());
            receita1.setDescritivo("Paracetamol");
            receita1.consultar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

