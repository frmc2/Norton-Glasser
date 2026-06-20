package com.fatec.consultorio.atv13_03;


import java.time.LocalDate;

public class Procedimento {
    private LocalDate data;
    private String descritivo;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws Exception {
        LocalDate  hoje = LocalDate.now();
        if(data.isBefore(hoje)){
            throw new Exception("Ocorreu um erro. A data do procediemnto não pode ser anterior a atual!");
        }
        else this.data = data;
    }

    public String getDescritivo() {
        return descritivo;
    }

    public void setDescritivo(String descritivo) throws Exception {
        if(descritivo == null ||descritivo.isBlank()){
            throw new Exception("Ocorreu um erro. A descrição é obrigatória!");
        }
        else{
            this.descritivo = descritivo;
        }
    }

    public void mostrar(){
        System.out.println("Data: "+ getData());
        System.out.println("Descritivo: "+ getDescritivo());
    }

    public void consultar(){

    }
}
