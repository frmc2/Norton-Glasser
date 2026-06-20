package com.fatec.biblioteca.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int idEmprestimo;
    private Leitor leitor;
    private Copia copia;
    private Funcionario funcionario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataEntregaReal;

    public Emprestimo() {}

    public Emprestimo(Leitor leitor, Copia copia, Funcionario funcionario) {
        this.leitor = leitor;
        this.copia = copia;
        this.funcionario = funcionario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(7); // Adiciona 7 dias de prazo
    }

    public double calcularMulta(double valorPorDiaAtrasado) {
        if (dataEntregaReal == null || !dataEntregaReal.isAfter(dataDevolucao)) {
            return 0.0;
        }
        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucao, dataEntregaReal);
        return diasAtraso * valorPorDiaAtrasado;
    }

    public LocalDate getDataEntregaReal() { return dataEntregaReal; }
    public void setDataEntregaReal(LocalDate dataEntregaReal) { this.dataEntregaReal = dataEntregaReal; }

    public int getIdEmprestimo() {return idEmprestimo;}
    public void setIdEmprestimo(int idEmprestimo) {this.idEmprestimo = idEmprestimo;}

    public Leitor getLeitor() {return leitor;}
    public void setLeitor(Leitor leitor) {this.leitor = leitor;}

    public Copia getCopia() {return copia;}
    public void setCopia(Copia copia) {this.copia = copia;}

    public Funcionario getFuncionario() {return funcionario;}
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario;}

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {this.dataEmprestimo = dataEmprestimo;}

    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) {this.dataDevolucao = dataDevolucao;}
}