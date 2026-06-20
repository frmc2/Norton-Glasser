package com.fatec.consultorio.atv20_02;


public class Receita {
    private String consulta;
    private String data;
    private String descritivo;



    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) {
        this.descritivo = descritivo;
    }

    void preescrever(){

    }

    void consultar(){

    }

    public Receita(){
        this.consulta="";
        this.data="";
        this.descritivo="";
    }



    public Receita(String pConsulta, String pData, String pDescritivo) {
        setConsulta(pConsulta);
        setData(pData);
        setDescritivo(pDescritivo);
    }

    void mostrar(){
        System.out.println("---RECEITA---");
        System.out.println("Consulta: "+getConsulta());
        System.out.println("Data: "+getData());
        System.out.println("Descritivo: "+getDescritivo()+"\n");

    }
}
