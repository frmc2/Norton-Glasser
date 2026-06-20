package com.fatec.biblioteca.modelos;

public class Copia {
    private int idCopia;
    private String codigoHex;
    private boolean disponivel;
    private Livro livro;

    public Copia() {
        this.disponivel = true;
    }

    public Copia(Livro livro) {
        this.livro = livro;
        this.disponivel = true;
    }

    public int getIdCopia(){return idCopia;}

    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;

        // Se a cópia já tiver um livro associado, gera o ID único
        if (this.livro != null) {
            this.codigoHex = this.livro.getIdLivro() + "-" + Integer.toHexString(idCopia);
        } else {
            this.codigoHex = Integer.toHexString(idCopia);
        }
    }

    public String getCodigoHex() {return codigoHex;}
    public void setCodigoHex(String CodigoHex) {this.codigoHex = codigoHex;}

    public boolean getDisponivel(){return disponivel;}
    public void setDisponivel(boolean Disponivel){this.disponivel = disponivel;}

    public Livro getLivro(){return livro;}
    public void setLivro(Livro livro){this.livro = livro;}

    @Override
    public String toString() {
        // Exibe o código hexadecimal e o título do livro associado (ex: "6-1 - O Hobbit")
        if (this.livro != null) {
            return this.codigoHex + " - " + this.livro.getTitulo();
        }
        return this.codigoHex;
    }

}
