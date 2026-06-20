package Atividade;

import Atividade.Interface.FormaGeometrica;
import Atividade.Classes.Circulo;
import Atividade.Classes.Quadrado;
import Atividade.Classes.Retangulo;
import Atividade.Classes.Triangulo;
import Atividade.Interface.FormaGeometrica;

public class App {
    public static void main(String[] args[]) {
        FormaGeometrica c = new Circulo(2);
        FormaGeometrica q = new Quadrado(4);
        FormaGeometrica r = new Retangulo(3, 5);
        FormaGeometrica t = new Triangulo(6, 2);

        System.out.println("Círculo - Arestas: " + c.quantidadeArestas() + ", Área: " + c.calculaArea());
        System.out.println("Quadrado - Arestas: " + q.quantidadeArestas() + ", Área: " + q.calculaArea());
        System.out.println("Retângulo - Arestas: " + r.quantidadeArestas() + ", Área: " + r.calculaArea());
        System.out.println("Triângulo - Arestas: " + t.quantidadeArestas() + ", Área: " + t.calculaArea());
    }
}