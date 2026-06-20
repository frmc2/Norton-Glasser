package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Reserva;
import com.fatec.biblioteca.modelos.Leitor;
import com.fatec.biblioteca.modelos.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOMySQL implements ReservaDAO {

    private static final String SQL_INSERT = "INSERT INTO reservas (leitor_id, livro_id, data_reserva) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT r.*, l.nome, b.titulo FROM reservas r " +
            "INNER JOIN leitores l ON r.leitor_id = l.id_leitor " +
            "INNER JOIN livros b ON r.livro_id = b.id_livro";

    @Override
    public void registrarReserva (Reserva reserva) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, reserva.getLeitor().getId());
            stmt.setInt(2, reserva.getLivro().getIdLivro());
            stmt.setDate(3, Date.valueOf(reserva.getDataReserva()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int idReserva) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Reserva> listarReserva() throws SQLException {
        List<Reserva> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Leitor leitor = new Leitor();
                leitor.setId(rs.getInt("leitor_id"));
                leitor.setNome(rs.getString("nome"));

                Livro livro = new Livro();
                livro.setIdLivro(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));

                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reserva.setLeitor(leitor);
                reserva.setLivro(livro);
                reserva.setDataReserva(rs.getDate("data_reserva").toLocalDate());

                lista.add(reserva);
            }
        }
        return lista;
    }
}
