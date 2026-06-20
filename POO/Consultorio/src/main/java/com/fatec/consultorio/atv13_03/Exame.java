package com.fatec.consultorio.atv13_03;

import java.time.LocalDate;

public class Exame extends Procedimento {

    public Exame(){
        super();
    }
    public Exame( LocalDate pData, String pDescritivo)throws Exception {
        setData(pData);
        setDescritivo(pDescritivo);
    }

    public void mostrar(){
        System.out.println("---Exame---");
        super.mostrar();
        System.out.println();
    }

    void solicitar(){

    }
    @Override
    public void consultar() {
        System.out.println("Consultando Solicitação de Exame Clínico:");
        super.mostrar();
    }
}
