package com.fatec.Pizzaria.modelos;

public abstract class ItemMenu {
    private int id;
    private String nome;
    private double preco;

    public ItemMenu(int id, String nome, double preco){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int   getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        if(preco <=0) throw new IllegalArgumentException("O preco deve ser maior que zero");
        this.preco = preco;
    }

    public double calcularPreçoFinal(){
        return this.preco;
    }
}
