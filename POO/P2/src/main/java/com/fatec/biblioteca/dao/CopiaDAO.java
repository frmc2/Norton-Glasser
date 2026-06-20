package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Copia;
import java.sql.SQLException;
import java.util.List;

public interface CopiaDAO {
    void salvar(Copia copia) throws SQLException;

    void deletar(int id) throws SQLException;

    List<Copia> listarCopia() throws SQLException;
}
