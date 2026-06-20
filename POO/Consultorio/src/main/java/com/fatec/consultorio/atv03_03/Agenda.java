package com.fatec.consultorio.atv03_03;

public class Agenda {
    private String data;
    private String hora;
    private Medico medico;
    private Paciente paciente;

    public void consultar() {
        System.out.println("Consultando agenda...");
    }


    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public Agenda() {}

    public void mostrar() {
        System.out.println("Agenda: " + data + " às " + hora + " | Médico: " + (medico != null ? medico.getNome() : "N/A"));
    }
}
