package com.fatec.consultorio.atv13_02;

public class    App {
    public static void main(String[] args) {
        Paciente p1 = new Paciente();
        p1.nome = "Carlos Silva";
        p1.cpf = "111.222.333-44";
        p1.telefone = "(11) 98888-7777";
        p1.genero = "Masculino";
        p1.idade = 35;
        p1.mostrar();

        Medico m1 = new Medico();
        m1.nome = "Dra. Ana Souza";
        m1.crm = "12345-SP";
        m1.telefone = "(11) 97777-6666";
        m1.especialidade = "Cardiologia";
        m1.senha = "senha123";
        m1.mostrar();

        Recepcionista r1 = new Recepcionista();
        r1.nome = "Fernanda Lima";
        r1.cpf = "555.666.777-88";
        r1.telefone = "(11) 96666-5555";
        r1.senha = "rec123";
        r1.mostrar();

        Agenda a1 = new Agenda();
        a1.data = "25/10/2023";
        a1.hora = "14:00";
        a1.medico = m1;
        a1.paciente = p1;
        a1.mostrar();

        Consulta c1 = new Consulta();
        c1.data = "25/10/2023";
        c1.hora = "14:00";
        c1.medico = m1;
        c1.paciente = p1;
        c1.motivo = "Check-up anual";
        c1.historico = "Paciente sem histórico de doenças graves.";
        c1.mostrar();

        Receita rec1 = new Receita();
        rec1.consulta = c1;
        rec1.data = "25/10/2023";
        rec1.descritivo = "Vitamina C - Tomar 1 comprimido ao dia.";
        rec1.mostrar();

        Exame ex1 = new Exame();
        ex1.consulta = c1;
        ex1.data = "26/10/2023";
        ex1.descritivo = "Exame de Sangue Completo";
        ex1.mostrar();
    }
}
