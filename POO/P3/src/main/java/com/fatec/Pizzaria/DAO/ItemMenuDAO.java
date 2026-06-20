package com.fatec.Pizzaria.DAO;

import com.fatec.Pizzaria.modelos.ItemMenu;
import com.fatec.Pizzaria.modelos.Pizza;
import com.fatec.Pizzaria.modelos.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class ItemMenuDAO {

    public void salvar(ItemMenu item) throws SQLException {
        String sqlProximoId = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                "WHERE TABLE_SCHEMA = 'Pizzaria_db' AND TABLE_NAME = 'ItemMenu'";

        String sqlInsert = "INSERT INTO ItemMenu (nome, preco, tipo, ingredientes, tamanhoEmML) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, item.getNome());
            stmt.setDouble(2, item.getPreco());

            if (item instanceof Pizza) {
                Pizza pizza = (Pizza) item;

                stmt.setString(3, "Pizza");
                stmt.setString(4, pizza.getIngredientes());
                stmt.setNull(5, Types.INTEGER);
            } else if (item instanceof Bebida) {
                Bebida bebida = (Bebida) item;

                stmt.setString(3, "Bebida");
                stmt.setNull(4, java.sql.Types.VARCHAR);
                stmt.setInt(5, bebida.getTamanhoEmML());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar o item no banco: " + e.getMessage(), e);
        }
    }


    public List<ItemMenu> listarTodos() throws SQLException {
        List<ItemMenu> lista = new ArrayList<>();

        String sql = "SELECT id, nome, preco, tipo, ingredientes, tamanhoEmML FROM ItemMenu";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String tipo = rs.getString("tipo");

                if ("Pizza".equalsIgnoreCase(tipo)) {
                    String ingredientes = rs.getString("ingredientes");

                    Pizza pizza = new Pizza(id, nome, preco, ingredientes);
                    lista.add(pizza);

                } else if ("Bebida".equalsIgnoreCase(tipo)) {
                    int tamanhoEmML = rs.getInt("tamanhoEmML");

                    Bebida bebida = new Bebida(id, nome, preco, tamanhoEmML);
                    lista.add(bebida);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os itens do banco de dados! Detalhe: " + e.getMessage(), e);
        }
        return lista;
    }
}