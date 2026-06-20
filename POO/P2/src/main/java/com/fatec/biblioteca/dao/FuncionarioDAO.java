package com.fatec.biblioteca.dao;

import com.fatec.biblioteca.modelos.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {
    void salvar(Funcionario funcionario) throws SQLException;

    Funcionario autenticar(String login, String senha) throws SQLException;

    void deletar(int id) throws SQLException;

    List<Funcionario> listarFuncionario() throws SQLException;
}
