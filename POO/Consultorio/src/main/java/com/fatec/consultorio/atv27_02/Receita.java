package com.fatec.consultorio.atv27_02;


import java.time.LocalDate;
public class Receita {
    private String consulta;
    private LocalDate data;
    private String descritivo;



    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws Exception {
        LocalDate  hoje = LocalDate.now();
        if(data.isBefore(hoje)){
            throw new Exception("Ocorreu um erro. A data da receita não pode ser anterior a atual!");
        }
        else this.data = data;
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
        this.data=LocalDate.now();
        this.descritivo="";
    }



    public Receita(String pConsulta, LocalDate pData, String pDescritivo) throws Exception{
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
