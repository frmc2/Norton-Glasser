package com.fatec.consultorio.atv27_02;


import java.time.LocalDate;
import java.time.LocalTime;
public class App {
    public static void  main(String[] args) {

        int x = 1;
        var p1 = new Paciente();
        try{

            p1.setCodigo(x++);
            p1.setNome ("");
            p1.setCpf ("428541088-50");
            p1.setTelefone ("1197293-1385");
            p1.setGenero ("Masculino");
            p1.setIdade (19);
            p1.mostrar();

            var p2 = new Paciente(x++, "    ", "1234", "1234","Masculino",18);
            p2.mostrar();
        }
        catch(Exception e){
            System.out.println("Ocorreu um erro. "+e.getMessage());
        }

        var m1 = new Medico();
        try{
            m1.setNome("Dr. Yuri Alberto");
            m1.setCrm ("");
            m1.setTelefone ("(11) 97777-6666");
            m1.setEspecialidade ("Cardiologia");
            m1.setSenha ("senha123");
            m1.mostrar();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        var agenda1 = new Agenda();
        try{
            agenda1.setData(LocalDate.of(2026,03,7)) ;
            agenda1.setHora("19:11");
            agenda1.setMedico(m1.getNome()) ;
            agenda1.setPaciente(p1.getNome()) ;
            agenda1.mostrar();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


        var consulta1 = new Consulta();
        try{
            consulta1.setData("22/02/2026");
            consulta1.setHora(LocalTime.of(10,10));
            consulta1.setMedico(m1.getNome());
            consulta1.setPaciente(p1.getNome());
            consulta1.setMotivo("Dor de cabeça");
            consulta1.setHistorico("Sem problemas recorrentes");
            consulta1.mostrar();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


        var exame1 = new Exame();
        try {
            exame1.setData("22/02/2026");
            exame1.setConsulta("Primeira consulta");
            exame1.setDescritivo("             ") ;
            exame1.mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        var receita1 = new Receita();

        try {
            receita1.setData(LocalDate.of(2026,03,7));
            receita1.setConsulta("Primeira consulta");
            receita1.setDescritivo("Foi receitado paracetamol para o paciente");
            receita1.mostrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        var ana = new Recepcionista();
        try {
            ana.setNome("Maria Flor");
            ana.setCpf("2873827363849237");
            ana.setTelefone("11 1293213123");
            ana.setSenha("senha123");
            ana.mostrar();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
