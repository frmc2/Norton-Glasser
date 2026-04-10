package com.fatec.consultorio.atv13_02;

public class Medico{
    String crm;
    String telefone;
    String nome;
    String senha;
    String especialidade;

    void mostrar(){
        System.out.println("---Medico---");
        System.out.println("Nome:"+nome+"\nCRM:"+crm+"\nTelefone:"+telefone+"\nEspecialidade"+especialidade+"\n\n");
    }

}