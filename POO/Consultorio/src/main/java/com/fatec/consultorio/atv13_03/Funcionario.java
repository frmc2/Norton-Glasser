package com.fatec.consultorio.atv13_03;


public class Funcionario {
    private String nome;
    private String telefone;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if(nome == null || nome.isBlank()){
            throw new Exception("Ocorreu um erro. O nome é obrigatório!");
        }
        else{this.nome = nome;
        }

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void acessar(){

    }
}
