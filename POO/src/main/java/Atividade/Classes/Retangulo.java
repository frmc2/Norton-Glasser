package Atividade.Classes;

import Atividade.Interface.FormaGeometrica;

public class Retangulo implements FormaGeometrica {
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public int quantidadeArestas() {
        return 4;
    }

    @Override
    public double calculaArea() {
        return base * altura;
    }
}