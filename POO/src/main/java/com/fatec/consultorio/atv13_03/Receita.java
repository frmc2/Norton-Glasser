package com.fatec.consultorio.atv13_03;

import java.time.LocalDate;
public class Receita extends Procedimento {

    public Receita(){
        super();
    }

    public Receita(LocalDate pData, String pDescritivo) throws Exception{
        setData(pData);
        setDescritivo(pDescritivo);
    }

    public void mostrar(){
        System.out.println("---RECEITA---");
        super.mostrar();
        System.out.println();
    }

    public void preescrever(){

    }

    @Override
    public void consultar() {
        System.out.println("Consultando Prescrição Médica:");
        super.mostrar();
    }
}