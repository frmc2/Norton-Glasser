package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOMySQL implements FuncionarioDAO {

    private static final String SQL_INSERT =
            "INSERT INTO funcionarios (nome, cpf, login, senha, cargo) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM funcionarios";

    private static final String SQL_AUTH =
            "SELECT * FROM funcionarios WHERE login = ? AND senha = ?";

    @Override
    public void salvar(Funcionario funcionario) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, funcionario.getNome()); // herdado de Pessoa
            stmt.setString(2, funcionario.getCpf());  // herdado de Pessoa
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setString(5, funcionario.getCargo());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Funcionario> listarFuncionario() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setLogin(rs.getString("login"));
                f.setCargo(rs.getString("cargo"));

                funcionarios.add(f);
            }
        }
        return funcionarios;
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (java.sql.Connection conn = ConnectionFactory.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    @Override
    public Funcionario autenticar(String login, String senha) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_AUTH)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setId(rs.getInt("id_funcionario"));
                    f.setNome(rs.getString("nome"));
                    f.setCpf(rs.getString("cpf"));
                    f.setLogin(rs.getString("login"));
                    f.setCargo(rs.getString("cargo"));
                    return f; // Login válido, devolve o funcionário
                }
            }
        }
        return null; // Login ou senha incorretos
    }
}
