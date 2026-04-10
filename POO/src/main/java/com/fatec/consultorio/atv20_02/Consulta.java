package com.fatec.consultorio.atv20_02;

public class Consulta {
    private String data;
    private String hora;
    private String medico;
    private String paciente;
    private String motivo;
    private String historico;



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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    void marcar (){

    }

    void cancelar(){

    }

    void consultar(){

    }

    void realizar(){

    }

    void atualizar(){

    }

    public Consulta(){
        this.data="";
        this.hora="";
        this.medico="";
        this.paciente="";
        this.motivo="";
        this.historico="";
    }

    public Consulta(String pData,String pHora,String pMedico,String pPaciente, String pMotivo, String pHistorico){
        setData(pData);
        setHora(pHora);
        setMedico(pMedico);
        setPaciente(pPaciente);
        setMotivo(pMotivo);
        setHistorico(pHistorico);
    }

    void mostrar(){
        System.out.println("----Consulta---");
        System.out.println("Data: "+getData());
        System.out.println("Hora: "+getHora());
        System.out.println("Medico: "+getMedico());
        System.out.println("Paciente: "+getPaciente());
        System.out.println("Motivo: "+getMotivo());
        System.out.println("Histórico: "+getHistorico()+"\n");
    }

}
