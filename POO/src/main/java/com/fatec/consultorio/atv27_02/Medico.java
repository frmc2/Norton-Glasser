package com.fatec.consultorio.atv27_02;


public class Medico {
    private String nome;
    private String crm;
    private String telefone;
    private String especialidade;
    private String senha;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCrm(){
        return crm;
    }

    public void setCrm(String crm) throws Exception{
        if(crm==null || crm.isBlank()){
            throw new Exception("Ocorreu um erro. O crm não pode ser vazio!");
        }
        else this.crm = crm;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }


    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public Medico(){
        this.nome="";
        this.crm="";
        this.telefone="";
        this.especialidade="";
        this.senha="";
    }

    public Medico(String pNome,String pCrm, String pTelefone, String pEspecialidade,String pSenha)throws Exception{
        setNome(pNome);
        setCrm(pCrm);
        setTelefone(pTelefone);
        setEspecialidade(pEspecialidade);
        setSenha(pSenha);
    }


    void mostrar(){
        System.out.println("---Médico---");
        System.out.println("Nome: "+getNome()+"\ncrm: "+getCrm()+"\ntelefone: "+getTelefone()+"\nespecialidade: "+getEspecialidade()+"\nsenha: "+getSenha()+"\n");
    }

    void acessar(){}

}
