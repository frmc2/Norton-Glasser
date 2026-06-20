package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Emprestimo;
import java.sql.SQLException;
import java.util.List;

public interface EmprestimoDAO {
    void registrar(Emprestimo emprestimo) throws SQLException;

    void registrarDevolucao(Emprestimo emprestimo) throws SQLException;

    List<Emprestimo> listarEmprestimo()  throws SQLException;
}

