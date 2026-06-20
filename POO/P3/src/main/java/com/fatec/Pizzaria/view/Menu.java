package com.fatec.Pizzaria.view; // Ajuste para o seu pacote correto!

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.fatec.Pizzaria.DAO.ItemMenuDAO;
import com.fatec.Pizzaria.modelos.*;

import java.sql.SQLException;

public class Menu extends JFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JComboBox<String> cbTipo;
    private JTextField txtIngredientes;
    private JTextField txtTamanhoMl;
    private JButton btnCadastrar;

    private JPanel painelPizza;
    private JPanel painelBebida;

    private ItemMenuDAO itemDAO;

    public Menu() {
        setTitle("Pizzaria do Bairro - Cadastro");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Empilha os itens verticalmente

        itemDAO = new ItemMenuDAO();

        // Inicializando os campos normais
        txtNome = new JTextField(20);
        txtPreco = new JTextField(10);
        cbTipo = new JComboBox<>(new String[]{"Pizza", "Bebida"});
        btnCadastrar = new JButton("Cadastrar Item");

        // Inicializando os campos específicos
        txtIngredientes = new JTextField(20);
        txtTamanhoMl = new JTextField(10);

        // Criando blocos separados para os campos específicos
        painelPizza = new JPanel(new FlowLayout());
        painelPizza.add(new JLabel("Ingredientes:"));
        painelPizza.add(txtIngredientes);

        painelBebida = new JPanel(new FlowLayout());
        painelBebida.add(new JLabel("Tamanho (ml):"));
        painelBebida.add(txtTamanhoMl);
        painelBebida.setVisible(false); // Bebida começa escondida porque "Pizza" é o padrão selecionado

        // Adicionando os componentes na janela principal
        add(new JLabel("Nome do Item:"));
        add(txtNome);
        add(new JLabel("Preço Base (R$):"));
        add(txtPreco);
        add(new JLabel("Tipo do Item:"));
        add(cbTipo);

        add(painelPizza);
        add(painelBebida);

        add(btnCadastrar);

        cbTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = cbTipo.getSelectedItem().toString();
                painelPizza.setVisible("Pizza".equals(selecionado));
                painelBebida.setVisible("Bebida".equals(selecionado));
                revalidate(); // Força a tela a se readequar visualmente
                repaint();
            }
        });
        // 4. EVENTO DO BOTÃO CADASTRAR (O motor do sistema)
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados básicos comuns da tela
                String nome = txtNome.getText().trim();
                String precoTexto = txtPreco.getText().trim();
                String tipo = cbTipo.getSelectedItem().toString();

                // Validação de campos vazios básicos
                if (nome.isEmpty() || precoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(Menu.this, "Por favor, preencha o Nome e o Preço!", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 1. Converte o preço de texto para double de forma segura (Evita que o programa feche em caso de letras)
                double preco;
                try {
                    preco = Double.parseDouble(precoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Menu.this, "Preço inválido! Digite apenas números usando ponto (Ex: 45.50).", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Variável genérica da classe abstrata mãe para usar o Polimorfismo
                ItemMenu novoItem = null;

                try {
                    // 2. Verifica a caixinha de seleção e instancia o objeto correto
                    if ("Pizza".equals(tipo)) {
                        String ingredientes = txtIngredientes.getText().trim();
                        if (ingredientes.isEmpty()) {
                            JOptionPane.showMessageDialog(Menu.this, "Por favor, insira os ingredientes da pizza!", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        // Instancia a classe filha Pizza (ID vai como 0 porque o banco gera no auto_increment)
                        novoItem = new Pizza(0, nome, preco, ingredientes);

                    } else if ("Bebida".equals(tipo)) {
                        String tamanhoTexto = txtTamanhoMl.getText().trim();
                        if (tamanhoTexto.isEmpty()) {
                            JOptionPane.showMessageDialog(Menu.this, "Por favor, insira o tamanho em ML da bebida!", "Aviso", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        int tamanhoMl = Integer.parseInt(tamanhoTexto);
                        // Instancia a classe filha Bebida
                        novoItem = new Bebida(0, nome, preco, tamanhoMl);
                    }

                    // 3. CHAMA O DAO PARA SALVAR NO BANCO DE DADOS (Protegido contra travamentos)
                    if (novoItem != null) {
                        itemDAO.salvar(novoItem);

                        // Exibe mensagem de sucesso mostrando o cálculo do preço final (Polimorfismo funcionando!)
                        JOptionPane.showMessageDialog(Menu.this,
                                "Item cadastrado com sucesso!\n" +
                                        "Nome: " + novoItem.getNome() + "\n" +
                                        "Preço de Venda: R$ " + String.format("%.2f", novoItem.calcularPreçoFinal()),
                                "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

                        // Limpa os campos da tela após o cadastro com sucesso
                        txtNome.setText("");
                        txtPreco.setText("");
                        txtIngredientes.setText("");
                        txtTamanhoMl.setText("");
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(Menu.this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Menu.this, "Erro ao persistir no Banco de Dados: " + ex.getMessage(), "Erro no JDBC", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
