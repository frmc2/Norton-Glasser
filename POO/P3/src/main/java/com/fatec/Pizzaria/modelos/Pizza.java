package com.fatec.Pizzaria.modelos;

public class Pizza extends ItemMenu{
    private String ingredientes;

    public Pizza(int id, String nome, double preco, String ingredientes){
        super(id, nome, preco);
        this.ingredientes = ingredientes;
    }

    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public double calcularPreçoFinal(){
        return getPreco() + 2.00;
    }
}
