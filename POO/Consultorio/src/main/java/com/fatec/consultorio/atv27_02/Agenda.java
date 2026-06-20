package com.fatec.consultorio.atv27_02;

import java.time.LocalDate;
public class Agenda {
    private LocalDate data;
    private String hora;
    private String medico;
    private String paciente;



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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Agenda(){
        this.data= LocalDate.now();
        this.hora="";
        this.medico="";
        this.paciente="";
    }

    public Agenda(LocalDate pData,String pHora,String pMedico,String pPaciente) throws Exception{
        setData(pData);
        setHora(pHora);
        setMedico(pMedico);
        setPaciente(pPaciente);
    }

    void mostrar(){
        System.out.println("----Agenda---");
        System.out.println("Data: "+getData()+"\nHora: "+getHora()+"\nMedico: "+getMedico()+"\nPaciente: "+getPaciente()+"\n");
    }

    void consultar(){
    }
}