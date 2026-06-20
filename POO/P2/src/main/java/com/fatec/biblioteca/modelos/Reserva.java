package com.fatec.biblioteca.modelos;

import java.time.LocalDate;

public class Reserva {
    private int idReserva;
    private Leitor leitor;
    private Livro livro;
    private LocalDate dataReserva;

    public Reserva() {}

    public Reserva(int idReserva, Leitor leitor, Livro livro, LocalDate dataReserva, boolean ativa) {
        this.idReserva = idReserva;
        this.leitor = leitor;
        this.livro = livro;
        this.dataReserva = dataReserva;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public Leitor getLeitor() { return leitor; }
    public void setLeitor(Leitor leitor) { this.leitor = leitor; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public LocalDate getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDate dataReserva) { this.dataReserva = dataReserva; }
}