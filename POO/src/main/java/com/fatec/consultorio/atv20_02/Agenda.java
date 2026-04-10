package com.fatec.consultorio.atv20_02;

public class Agenda {
    public String data;
    public String hora;
    public String medico;
    public String paciente;



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        this.data="";
        this.hora="";
        this.medico="";
        this.paciente="";
    }

    public Agenda(String pData,String pHora,String pMedico,String pPaciente){
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