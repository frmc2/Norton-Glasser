package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOMySQL implements LivroDAO {

    private static final String SQL_INSERT = "INSERT INTO livros (titulo, autor) VALUES (?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM livros";

    @Override
    public void salvar(Livro livro) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Livro> listarLivro() throws SQLException {
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("id_livro")); // Nome da coluna do banco
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livros.add(livro);
            }
        }
        return livros;
    }
}
