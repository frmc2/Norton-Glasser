package com.fatec.biblioteca.modelos;

public class Funcionario extends Pessoa {
    private String login;
    private String senha;
    private String cargo;

    public Funcionario() {
        super();
    }

    public Funcionario(int id, String nome, String cpf, String login, String senha, String cargo) {
        super(id, nome, cpf);
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
