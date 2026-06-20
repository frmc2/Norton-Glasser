package com.fatec.biblioteca.modelos;

public class Livro {
    private int  idLivro;
    private String titulo;
    private String autor;

    public Livro() {}

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getIdLivro(){return idLivro;}
    public void setIdLivro(int id_Livro){this.idLivro = idLivro;}

    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}

    public String getAutor(){return autor;}
    public void setAutor(String autor){this.autor = autor;}

    @Override
    public String toString() {
        return this.titulo + " (" + this.autor + ")";
    }

}
