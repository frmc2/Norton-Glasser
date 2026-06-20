package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.dao.ConnectionFactory;
import com.fatec.biblioteca.modelos.Copia;
import com.fatec.biblioteca.modelos.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CopiaDAOMySQL implements CopiaDAO {

    // Comandos SQL puros que o MySQL vai executar
    private static final String SQL_INSERT = "INSERT INTO copias (codigo_hex, disponivel, livro_id) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT c.*, l.titulo, l.autor FROM copias c INNER JOIN livros l ON c.livro_id = l.id_livro";

    @Override
    public void salvar(Copia copia) throws SQLException {
        // 1. Descobre qual será o ID da próxima cópia para gerar o Hexadecimal real
        String sqlProximoId = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                "WHERE TABLE_SCHEMA = 'biblioteca_db' AND TABLE_NAME = 'copias'";

        String sqlInsert = "INSERT INTO copias (codigo_hex, disponivel, livro_id) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            int proximoId = 1;

            // Executa a busca do contador do MySQL
            try (PreparedStatement stmtId = conn.prepareStatement(sqlProximoId);
                 ResultSet rs = stmtId.executeQuery()) {
                if (rs.next()) {
                    proximoId = rs.getInt("AUTO_INCREMENT");
                }
            }

            // 2. Calcula o código Hex no padrão do projeto (Ex: Livro 6, copia 1 -> "6-1")
            String hexCalculado = copia.getLivro().getIdLivro() + "-" + proximoId;
            copia.setCodigoHex(hexCalculado); // Garante que o objeto não vai nulo

            // 3. Executa o insert com o Hex preenchido na marra
            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                stmt.setString(1, hexCalculado);
                stmt.setBoolean(2, copia.getDisponivel());
                stmt.setInt(3, copia.getLivro().getIdLivro());
                stmt.executeUpdate();
            }
        }
    }


    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM copias WHERE id = ?";

        try (java.sql.Connection conn = ConnectionFactory.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Copia> listarCopia() throws SQLException {
        List<Copia> copias = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             // 5. ResultSet: Uma tabela virtual que guarda as linhas devolvidas pelo banco
             ResultSet rs = stmt.executeQuery()) {

            // 6. rs.next(): Pula para a próxima linha do resultado. Retorna false se acabar.
            while (rs.next()) {
                // Criamos o Livro pai primeiro
                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));

                // Criamos a cópia e associamos o livro a ela
                Copia copia = new Copia();
                copia.setLivro(livro); // Monta a associação/agregação

                // O setIdCopia já calcula o Hexadecimal internamente como você programou!
                copia.setIdCopia(rs.getInt("id_copia"));
                copia.setDisponivel(rs.getBoolean("disponivel"));

                copias.add(copia);
            }
        }
        return copias;
    }
}
