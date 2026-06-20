package com.fatec.consultorio.atv13_03;

import java.time.LocalDate;
import java.time.LocalTime;
public class Agenda {
    private LocalDate data;
    private LocalTime hora;
    private Medico medico;
    private Paciente paciente;



    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data) throws Exception{
        LocalDate hoje = LocalDate.now();
        if(data.isBefore(hoje)){
            throw new Exception("Ocurreu um erro. A data da agenda não pode ser anterior a data atual!");
        }
        else {
            this.data = data;
        }
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda(){
        this.data= LocalDate.now();
        this.hora= LocalTime.now();
        this.medico=null;
        this.paciente=null;
    }

    public Agenda(LocalDate pData,LocalTime pHora,Medico pMedico,Paciente pPaciente) throws Exception{
        setData(pData);
        setHora(pHora);
        setMedico(pMedico);
        setPaciente(pPaciente);
    }

    void mostrar(){
        System.out.println("----Agenda---");
        System.out.println("Data: "+getData());
        System.out.println("Hora:"+getHora());
        System.out.println("Medico: "+medico.getNome());
        System.out.println("Paciente: "+paciente.getNome());
    }

    void consultar(){
    }
}