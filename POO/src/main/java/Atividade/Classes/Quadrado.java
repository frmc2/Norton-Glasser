package Atividade.Classes;

import Atividade.Interface.FormaGeometrica;

public class Quadrado implements FormaGeometrica {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public int quantidadeArestas() {
        return 4;
    }

    @Override
    public double calculaArea() {
        return lado * lado;
    }
}