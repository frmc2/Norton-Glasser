package com.fatec.consultorio.atv03_03;


public class App {
    public static void main(String[] args) {
        try {
            Paciente p1 = new Paciente();
            p1.setCodigo(1);
            p1.setNome("Marcos");
            p1.setCpf("123.456.789-00");
            p1.setIdade(20);
            p1.setEmail("marcos@rpm.com");

            Medico m1 = new Medico();
            m1.setNome("Dr. Estranho");
            m1.setCrm("CRM/SP 123456");

            Agenda agenda1 = new Agenda();
            agenda1.setData("25/05/2026");
            agenda1.setHora("14:30");
            agenda1.setMedico(m1);
            agenda1.setPaciente(p1);

            Consulta c1 = new Consulta();
            c1.setData("25/05/2026");
            c1.setMedico(m1);
            c1.setPaciente(p1);
            c1.setAgenda(agenda1);
            c1.setMotivo("Check-up de rotina");

            Recepcionista r1 = new Recepcionista("Maria", "999.888.777-11", "11-9999-8888", "senha123");
            r1.setPaciente(p1);
            r1.setConsulta(c1);

            System.out.println("--- Dados da Consulta ---");
            c1.mostrar();

            System.out.println("\n--- Dados da Agenda via Consulta ---");
            System.out.println("Data: " + c1.getAgenda().getData());
            System.out.println("Médico Responsável: " + c1.getMedico().getNome());

            System.out.println("\n--- Dados da Recepcionista ---");
            r1.mostrar();
            System.out.println("Atendendo o paciente: " + r1.getPaciente().getNome());

        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
        }
    }
}
