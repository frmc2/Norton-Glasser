package com.fatec.consultorio.atv03_03;

public class Paciente {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String genero;
    private int idade;

    public void cadastrar() {
        System.out.println("Paciente cadastrado com sucesso.");
    }

    public void consultar() {
        System.out.println("Consultando dados do paciente...");
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) throws Exception {
        if(email == null || !email.contains("@")) throw new Exception("Email inválido!");
        this.email = email;
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public Paciente() {}

    public void mostrar() {
        System.out.println("Paciente: " + nome + " | CPF: " + cpf);
    }
}