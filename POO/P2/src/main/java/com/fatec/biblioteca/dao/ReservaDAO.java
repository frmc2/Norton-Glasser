package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Reserva;
import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {
    void registrarReserva(Reserva reserva) throws SQLException;

    void deletar(int idReserva) throws SQLException;

    List<Reserva> listarReserva() throws SQLException;
}
