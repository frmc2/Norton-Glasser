package com.fatec.biblioteca.modelos;

public class Leitor extends Pessoa {
    private String matricula;

    public Leitor() {
        super();
    }

    public Leitor(int id, String nome, String cpf, String matricula) {
        super(id, nome, cpf);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    // O toString ajuda a exibir o nome do leitor nas caixas de seleção do Swing
    @Override
    public String toString() {
        return this.getNome() + " (Matrícula: " + this.matricula + ")";
    }
}
