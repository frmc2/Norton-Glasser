package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAOMySQL implements EmprestimoDAO {

    private static final String SQL_INSERT =
            "INSERT INTO emprestimos (leitor_id, copia_id, funcionario_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_COPIA =
            "UPDATE copias SET disponivel = ? WHERE id_copia = ?";

    private static final String SQL_UPDATE_DEVOLUCAO =
            "UPDATE emprestimos SET data_entrega_real = ? WHERE id_emprestimo = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT e.*, lei.nome AS leitor_nome, func.nome AS func_nome, c.codigo_hex, l.titulo " +
                    "FROM emprestimos e " +
                    "INNER JOIN leitores lei ON e.leitor_id = lei.id_leitor " +
                    "INNER JOIN funcionarios func ON e.funcionario_id = func.id_funcionario " +
                    "INNER JOIN copias c ON e.copia_id = c.id_copia " +
                    "INNER JOIN livros l ON c.livro_id = l.id_livro";

    @Override
    public void registrar(Emprestimo emp) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false); // Inicia uma transação segura

            try (PreparedStatement stmtIns = conn.prepareStatement(SQL_INSERT);
                 PreparedStatement stmtUpd = conn.prepareStatement(SQL_UPDATE_COPIA)) {

                stmtIns.setInt(1, emp.getLeitor().getId());
                stmtIns.setInt(2, emp.getCopia().getIdCopia());
                stmtIns.setInt(3, emp.getFuncionario().getId());
                stmtIns.setDate(4, Date.valueOf(emp.getDataEmprestimo()));
                stmtIns.setDate(5, Date.valueOf(emp.getDataDevolucao()));
                stmtIns.executeUpdate();

                stmtUpd.setBoolean(1, false);
                stmtUpd.setInt(2, emp.getCopia().getIdCopia());
                stmtUpd.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    @Override
    public void registrarDevolucao(Emprestimo emp) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtDev = conn.prepareStatement(SQL_UPDATE_DEVOLUCAO);
                 PreparedStatement stmtCopia = conn.prepareStatement(SQL_UPDATE_COPIA)) {

                // 1. Atualiza a data em que o livro foi devolvido de verdade
                stmtDev.setDate(1, Date.valueOf(emp.getDataEntregaReal()));
                stmtDev.setInt(2, emp.getIdEmprestimo());
                stmtDev.executeUpdate();

                // 2. Libera a cópia física de volta para a estante (true)
                stmtCopia.setBoolean(1, true);
                stmtCopia.setInt(2, emp.getCopia().getIdCopia());
                stmtCopia.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Emprestimo> listarEmprestimo() throws SQLException {
        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Monta o Leitor
                Leitor leitor = new Leitor();
                leitor.setId(rs.getInt("leitor_id"));
                leitor.setNome(rs.getString("leitor_nome"));

                // Monta o Funcionário
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("funcionario_id"));
                func.setNome(rs.getString("func_nome"));

                // Monta o Livro e a Cópia associada
                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));

                Copia copia = new Copia();
                copia.setIdCopia(rs.getInt("copia_id"));
                copia.setLivro(livro);

                Emprestimo emp = new Emprestimo();
                emp.setIdEmprestimo(rs.getInt("id_emprestimo"));
                emp.setLeitor(leitor);
                emp.setFuncionario(func);
                emp.setCopia(copia);
                emp.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                emp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());

                Date entregaReal = rs.getDate("data_entrega_real");
                if (entregaReal != null) {
                    emp.setDataEntregaReal(entregaReal.toLocalDate());
                }

                lista.add(emp);
            }
        }
        return lista;
    }
}
