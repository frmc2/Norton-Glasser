package com.fatec.Pizzaria.modelos;

public class Bebida extends ItemMenu{
    private int tamanhoEmML;

   public Bebida(int id, String nome, double preco, int tamanhoEmML) {
        super(id, nome, preco);
        this.tamanhoEmML = tamanhoEmML;
    }

    public int getTamanhoEmML() {
        return tamanhoEmML;
    }
    public void setTamanhoEmML(int tamanhoEmML) {
        this.tamanhoEmML = tamanhoEmML;
    }

    @Override
    public double calcularPreçoFinal(){
        return getPreco();
    }
}
