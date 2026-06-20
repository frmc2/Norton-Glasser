package com.fatec.consultorio.atv03_03;

public class Recepcionista {
    private String nome;
    private String cpf;
    private String telefone;
    private String senha;
    private Paciente paciente;
    private Consulta consulta;

    public void acessar() {
        System.out.println("Acesso realizado.");
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return this.consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Recepcionista() {}

    public Recepcionista(String nome, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
    }

    public void mostrar() {
        System.out.println("Recepcionista: " + nome);
    };
}