package com.fatec.consultorio.atv13_02;

public class Consulta {
    String data;
    String hora;
    String medico;
    String paciente;
    String motivo;
    String historico;

    void marcar(){}
    void cancelar(){}
    void consultar(){}
    void realizar(){}
    void atualizar(){}


    void mostrar(){
        System.out.println("---Consulta---");
        System.out.println("Paciente:"+paciente.nome + "\nMedico:"+medico.nome + "\nMotivo:"+motivo+"\nData:"+data+"\nHora:"+hora+"\n\n");
    }
}
