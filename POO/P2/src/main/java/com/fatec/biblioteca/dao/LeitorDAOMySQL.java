package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Leitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAOMySQL implements LeitorDAO {

    private static final String SQL_INSERT = "INSERT INTO leitores (nome, cpf, matricula) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM leitores";

    @Override
    public void salvar(Leitor leitor) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, leitor.getNome()); // herdado de Pessoa
            stmt.setString(2, leitor.getCpf());  // herdado de Pessoa
            stmt.setString(3, leitor.getMatricula());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int idLeitor) throws SQLException {
        // Consulta se há livros na rua para este leitor
        String sqlChecar = "SELECT COUNT(*) FROM emprestimos WHERE leitor_id = ? AND data_entrega_real IS NULL";
        String sqlDelete = "DELETE FROM leitores WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            // Passo 1: Validação de segurança
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlChecar)) {
                stmtCheck.setInt(1, idLeitor);
                try (ResultSet rs = stmtCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Lança uma exceção controlada se o cliente tiver pendências
                        throw new SQLException("Este leitor possui empréstimos ativos em aberto e não pode ser removido!");
                    }
                }
            }

            // Passo 2: Se passou na checagem, deleta fisicamente
            try (PreparedStatement stmtDel = conn.prepareStatement(sqlDelete)) {
                stmtDel.setInt(1, idLeitor);
                stmtDel.executeUpdate();
            }
        }
    }



    @Override
    public List<Leitor> listarLeitor() throws SQLException {
        List<Leitor> leitores = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Leitor leitor = new Leitor();

                // Preenche os atributos herdados e os próprios
                leitor.setId(rs.getInt("id_leitor"));
                leitor.setNome(rs.getString("nome"));
                leitor.setCpf(rs.getString("cpf"));
                leitor.setMatricula(rs.getString("matricula"));

                leitores.add(leitor);
            }
        }
        return leitores;
    }
}
