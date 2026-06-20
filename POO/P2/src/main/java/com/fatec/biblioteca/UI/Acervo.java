package com.fatec.biblioteca.UI;

import com.fatec.biblioteca.dao.*;
import com.fatec.biblioteca.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Acervo extends JPanel {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JButton btnSalvarLivro;

    private JComboBox<Livro> cbLivrosParaCopia;
    private JButton btnGerarCopia;

    private JTable tabelaCopias;
    private DefaultTableModel modeloTabela;

    private LivroDAO livroDAO;
    private CopiaDAO copiaDAO;

    public Acervo() {
        this.livroDAO = new LivroDAOMySQL();
        this.copiaDAO = new CopiaDAOMySQL();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. PAINEL SUPERIOR: DIVIDIDO EM CADASTRO DE OBRA E GERADOR DE CÓPIA
        JPanel painelTop = new JPanel(new GridLayout(2, 1, 5, 5));

        // Linha A: Cadastrar a Obra Geral (Livro)
        JPanel linhaLivro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        txtTitulo = new JTextField(15);
        txtAutor = new JTextField(12);
        btnSalvarLivro = new JButton("1. Cadastrar Obra");
        linhaLivro.add(new JLabel("Título:")); linhaLivro.add(txtTitulo);
        linhaLivro.add(new JLabel("Autor:")); linhaLivro.add(txtAutor);
        linhaLivro.add(btnSalvarLivro);

        // Linha B: Gerar a Unidade Física (Cópia com Hexadecimal)
        JPanel linhaCopia = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        cbLivrosParaCopia = new JComboBox<>();
        btnGerarCopia = new JButton("2. Gerar Cópia Física (Hex)");
        linhaCopia.add(new JLabel("Selecionar Obra para Criar Cópia:"));
        linhaCopia.add(cbLivrosParaCopia);
        linhaCopia.add(btnGerarCopia);

        painelTop.add(linhaLivro);
        painelTop.add(linhaCopia);
        add(painelTop, BorderLayout.NORTH);

        // 2. TABELA GRÁFICA DE INVENTÁRIO (Painel Centro)
        modeloTabela = new DefaultTableModel(new Object[]{"ID Cópia", "Título do Livro", "Código Hexadecimal", "Disponibilidade"}, 0);
        tabelaCopias = new JTable(modeloTabela);
        add(new JScrollPane(tabelaCopias), BorderLayout.CENTER);

        // 3. AÇÕES DOS BOTÕES
        btnSalvarLivro.addActionListener(e -> salvarLivro());
        btnGerarCopia.addActionListener(e -> gerarCopiaFisica());

        // Painel inferior elástico para o botão de remoção física de cópias
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemoverCopia = new JButton("Dar Baixa / Remover Cópia");
        btnRemoverCopia.setBackground(new Color(235, 77, 75)); // Vermelho moderno
        btnRemoverCopia.setForeground(Color.BLACK);
        painelInferior.add(btnRemoverCopia);
        add(painelInferior, BorderLayout.SOUTH);

        // Vincula o acionador de clique com a função de deletar
        btnRemoverCopia.addActionListener(e -> deletarCopia());


        // Inicializa as informações na tela
        carregarLivrosNoCombo();
        atualizarTabela();
    }

    private void carregarLivrosNoCombo() {
        try {
            cbLivrosParaCopia.removeAllItems();
            List<Livro> lista = livroDAO.listarLivro();
            for (Livro l : lista) {
                cbLivrosParaCopia.addItem(l); // Usa o toString() automático
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar seletor: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarLivro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o título e o autor da obra!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Livro novoLivro = new Livro(titulo, autor);
            livroDAO.salvar(novoLivro);

            JOptionPane.showMessageDialog(this, "Obra cadastrada com sucesso! Agora você já pode gerar cópias físicas para ela.");
            txtTitulo.setText("");
            txtAutor.setText("");

            carregarLivrosNoCombo(); // Atualiza o seletor de cópias automaticamente
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar livro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarCopiaFisica() {
        Livro livroSelecionado = (Livro) cbLivrosParaCopia.getSelectedItem();

        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma obra cadastrada para gerar a cópia!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Copia novaCopia = new Copia();
            novaCopia.setLivro(livroSelecionado); // Faz a agregação forte exigida pelo enunciado
            novaCopia.setDisponivel(true); // Nasce disponível na estante

            // Salva no banco de dados. O método setIdCopia do seu DAO vai calcular o Hexadecimal na listagem!
            copiaDAO.salvar(novaCopia);

            JOptionPane.showMessageDialog(this, "Cópia física criada com sucesso e adicionada ao inventário!");
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao criar cópia: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarCopia() {
        int linhaSelecionada = tabelaCopias.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma cópia física na tabela para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Captura o ID da Cópia (Coluna 0) e o Código Hexadecimal (Coluna 2) da linha clicada
        int idCopia = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String codigoHex = (String) modeloTabela.getValueAt(linhaSelecionada, 2);

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente dar baixa e excluir a cópia (" + codigoHex + ") do inventário?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                // Executa a chamada até o MySQL através da fiação do DAO
                copiaDAO.deletar(idCopia);
                JOptionPane.showMessageDialog(this, "Unidade física removida do acervo com sucesso!");

                // Recarrega a JTable visual no monitor na mesma hora
                atualizarTabela();
            } catch (SQLException ex) {
                // Tratamento perto da UI caso a cópia esteja alugada (Regra de Integridade)
                JOptionPane.showMessageDialog(this, "Não foi possível remover! Esta cópia possui históricos de empréstimos ativos vinculados.\nErro: " + ex.getMessage(), "Erro de Integridade", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        try {
            List<Copia> lista = copiaDAO.listarCopia();
            for (Copia c : lista) {
                modeloTabela.addRow(new Object[]{
                        c.getIdCopia(),
                        c.getLivro().getTitulo(),
                        c.getCodigoHex(), // Exibe o ID calculado em formato Hexadecimal (ex: 1-a)
                        c.getDisponivel() ? "Disponível na Estante" : "Emprestado / Alugado"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar inventário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
