package Atividade.Classes;

import Atividade.Interface.FormaGeometrica;

public class Circulo implements FormaGeometrica {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public int quantidadeArestas() {
        return 0;
    }

    @Override
    public double calculaArea() {
        return Math.PI * raio * raio;
    }
}