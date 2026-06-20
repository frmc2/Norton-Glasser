package Atividade.Classes;

import Atividade.Interface.FormaGeometrica;

public class Triangulo implements FormaGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public int quantidadeArestas() {
        return 3;
    }

    @Override
    public double calculaArea() {
        return (base * altura) / 2;
    }
}
