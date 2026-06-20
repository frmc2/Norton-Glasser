package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Livro;
import java.sql.SQLException;
import java.util.List;

public interface LivroDAO {
    void salvar(Livro livro) throws SQLException;

    List<Livro> listarLivro() throws SQLException;
}
