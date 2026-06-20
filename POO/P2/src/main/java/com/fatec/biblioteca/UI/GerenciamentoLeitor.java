package com.fatec.biblioteca.UI;

import com.fatec.biblioteca.dao.LeitorDAO;
import com.fatec.biblioteca.dao.LeitorDAOMySQL;
import com.fatec.biblioteca.modelos.Leitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GerenciamentoLeitor extends JPanel {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtMatricula;
    private JButton btnSalvar;

    private JTable tabelaLeitores;
    private DefaultTableModel modeloTabela;
    private LeitorDAO leitorDAO;

    public GerenciamentoLeitor() {
        this.leitorDAO = new LeitorDAOMySQL();

        // Organiza o painel com bordas e espaçamento elástico do AWT
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. FORMULÁRIO DE CADASTRO (Painel Superior)
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

        txtNome = new JTextField(15);
        txtCpf = new JTextField(10);
        txtMatricula = new JTextField(8);
        btnSalvar = new JButton("Cadastrar Leitor");

        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(txtNome);
        painelCadastro.add(new JLabel("CPF:"));
        painelCadastro.add(txtCpf);
        painelCadastro.add(new JLabel("Matrícula:"));
        painelCadastro.add(txtMatricula);
        painelCadastro.add(btnSalvar);
        add(painelCadastro, BorderLayout.NORTH);

        // 2. TABELA GRÁFICA DE LISTAGEM (Painel Centro)
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome do Cliente", "CPF", "Matrícula"}, 0);
        tabelaLeitores = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaLeitores); // Adiciona barra de rolagem se crescer
        add(scrollPane, BorderLayout.CENTER);

        // 3. AÇÃO DO BOTÃO
        btnSalvar.addActionListener(e -> salvarLeitor());

        // painel inferior para o botão de remoção
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemover = new JButton("Remover Cliente Selecionado");
        btnRemover.setBackground(new Color(235, 77, 75)); // Vermelho amigável
        btnRemover.setForeground(Color.black);
        painelInferior.add(btnRemover);
        add(painelInferior, BorderLayout.SOUTH);

        // Evento de clique para disparar a exclusão
        btnRemover.addActionListener(e -> deletarLeitor());


        // Carrega os leitores do banco de dados assim que a aba se abre
        atualizarTabela();
    }

    private void salvarLeitor() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String matricula = txtMatricula.getText().trim();

        // Validação rápida de interface
        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos do leitor!", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Captura do erro colada na interface (UI) conforme manda o enunciado
        try {
            // O ID vai como 0 porque o MySQL gera o número real no AUTO_INCREMENT
            Leitor novoLeitor = new Leitor(0, nome, cpf, matricula);
            leitorDAO.salvar(novoLeitor);

            JOptionPane.showMessageDialog(this, "Leitor cadastrado com sucesso!");

            // Limpa os campos para o próximo cadastro
            txtNome.setText("");
            txtCpf.setText("");
            txtMatricula.setText("");

            atualizarTabela(); // Recarrega a tabela visual na hora
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados: " + ex.getMessage(), "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarLeitor() {
        int linhaSelecionada = tabelaLeitores.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pega o ID da linha selecionada (Coluna 0)
        int idLeitor = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeLeitor = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o leitor " + nomeLeitor + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                leitorDAO.deletar(idLeitor);
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
                atualizarTabela(); // Recarrega a tabela na tela imediatamente
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível remover! Verifique se este leitor não possui empréstimos ativos.\nErro: " + ex.getMessage(), "Erro de Integridade", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa as linhas antigas da tela

        try {
            List<Leitor> lista = leitorDAO.listarLeitor();
            for (Leitor l : lista) {
                modeloTabela.addRow(new Object[]{l.getId(), l.getNome(), l.getCpf(), l.getMatricula()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar lista de leitores: " + ex.getMessage(), "Erro de Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }
}
