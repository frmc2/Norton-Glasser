package com.fatec.consultorio.atv20_02;


public class Recepcionista {
    private String nome;
    private String cpf;
    private String telefone;
    private String senha;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public Recepcionista(){
        this.nome="";
        this.cpf="";
        this.telefone="";
        this.senha="";
    }

    public Recepcionista(String pNome,String pCpf,String pTelefone,String pSenha){
        setNome(pNome);
        setCpf(pCpf);
        setTelefone(pTelefone);
        setSenha(pSenha);
    }

    void mostrar(){
        System.out.println("----Recepcionista---");
        System.out.println("Nome: "+getNome()+"\nCPF: "+getCpf()+"\nTelefone: "+getTelefone()+"\nSenha: "+getSenha()+"\n");
    }

    void acessar(){}
}
