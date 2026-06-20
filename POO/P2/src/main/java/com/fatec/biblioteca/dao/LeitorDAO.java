package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Leitor;
import java.sql.SQLException;
import java.util.List;

public interface LeitorDAO {
    void salvar(Leitor leitor) throws SQLException;

    void deletar(int id) throws SQLException;

    List<Leitor> listarLeitor() throws SQLException;
}
