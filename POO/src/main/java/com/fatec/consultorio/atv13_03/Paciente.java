package com.fatec.consultorio.atv13_03;


public class Paciente {
    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;
    private String genero;
    private int idade;

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) throws Exception{
        if(nome==null || nome.isBlank()){
            throw new Exception("O nome do paciente não pode ser vazio!");
        }
        else{
            this.nome = nome;
        }
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

    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public Paciente(){
        this.codigo=0;
        this.nome="";
        this.cpf="";
        this.telefone="";
        this.genero="";
        this.idade=0;
    }

    public Paciente(int pCodigo, String pNome, String pCpf, String pTelefone, String pGenero, int pIdade)throws Exception{
        setCodigo(pCodigo);
        setNome(pNome);
        setCpf(pCpf);
        setTelefone(pTelefone);
        setGenero(pGenero);
        setIdade(pIdade);
    }


    void mostrar(){
        System.out.println("---Paciente---");
        System.out.println("Código: "+getCodigo()+"\nNome: "+getNome()+ "\nCPF: "+getCpf()+ "\nIdade: "+getIdade()+ "\nTelefone: "+getTelefone()+ "\nGenero: "+getGenero()+"\n");
    }

    void cadastrar(){

    }
    void consultar (){

    }

}
