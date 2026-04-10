package com.fatec.consultorio.atv20_02;


public class App {
    public static void  main(String[] args) {
        var p1 = new Paciente();
        p1.setCodigo(1);
        p1.setNome ("Vinicius Guedes");
        p1.setCpf ("428541088-50");
        p1.setTelefone ("1197293-1385");
        p1.setGenero ("Masculino");
        p1.setIdade (19);
        p1.mostrar();

        var m1 = new Medico();
        m1.setNome("Dr. Yuri Alberto");
        m1.setCrm ("12345-SP");
        m1.setTelefone ("(11) 97777-6666");
        m1.setEspecialidade ("Cardiologia");
        m1.setSenha ("senha123");
        m1.mostrar();


        var agenda1 = new Agenda();
        agenda1.setData("22/02/2026") ;
        agenda1.setHora("19:11");
        agenda1.setMedico(m1.getNome()) ;
        agenda1.setPaciente(p1.getNome()) ;
        agenda1.mostrar();

        var consulta1 = new Consulta();
        consulta1.setData("22/02/2026");
        consulta1.setHora("19:11");
        consulta1.setMedico(m1.getNome());
        consulta1.setPaciente(p1.getNome());
        consulta1.setMotivo("Dor de cabeça");
        consulta1.setHistorico("Sem problemas recorrentes");
        consulta1.mostrar();

        var exame1 = new Exame();
        exame1.setData("22/02/2026");
        exame1.setConsulta("Primeira consulta");
        exame1.setDescritivo("Foi receitado remédio para o paciente") ;
        exame1.mostrar();

        var receita1 = new Receita();
        receita1.setData("22/02/2026");
        receita1.setConsulta("Primeira consulta");
        receita1.setDescritivo("Foi receitado remédio para o paciente");
        receita1.mostrar();

        var ana = new Recepcionista();
        ana.setNome("Maria Flor");
        ana.setCpf("2873827363849237");
        ana.setTelefone("11 1293213123");
        ana.setSenha("senha123");
        ana.mostrar();
    }
}
