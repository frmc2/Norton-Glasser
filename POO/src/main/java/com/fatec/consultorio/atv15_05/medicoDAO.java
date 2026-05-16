package com.fatec.consultorio.atv15_05;

import com.fatec.consultorio.atv15_05.Medico;
import com.fatec.consultorio.atv15_05.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicoDAO {

    private Connection conexao;

    public MedicoDAO() throws Exception {
        conexao = ConnectionFactory.getConnection();
        criarTabela();
    }

    private void criarTabela() throws Exception {
        String sql = """
            CREATE TABLE IF NOT EXISTS medico (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(100),
                telefone VARCHAR(20),
                senha VARCHAR(100),
                crm VARCHAR(30),
                especialidade VARCHAR(100)
            )
        """;

        Statement stmt = conexao.createStatement();
        stmt.execute(sql);
    }

    public void inserir(Medico medico) throws Exception {
        String sql =
            "INSERT INTO medico(nome, telefone, senha, crm, especialidade) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, medico.getNome());
        stmt.setString(2, medico.getTelefone());
        stmt.setString(3, medico.getSenha());
        stmt.setString(4, medico.getCrm());
        stmt.setString(5, medico.getEspecialidade());
        stmt.execute();

        System.out.println("Médico salvo no banco!");
    }

    public void listar() throws Exception {
        String sql = "SELECT * FROM medico";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
    }

    public void atualizar(Medico medico, int id) throws Exception {
        String sql = "UPDATE medico SET nome=?, telefone=?, senha=?, crm=?, especialidade=? WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1, medico.getNome());
        stmt.setString(2, medico.getTelefone());
        stmt.setString(3, medico.getSenha());
        stmt.setString(4, medico.getCrm());
        stmt.setString(5, medico.getEspecialidade());
        stmt.setInt(6, id);
        stmt.executeUpdate();

        System.out.println("Médico atualizado!");
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM medico WHERE id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.executeUpdate();

        System.out.println("Médico deletado!");
    }
}
