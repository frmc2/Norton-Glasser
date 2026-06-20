package com.fatec.biblioteca.UI;

import com.fatec.biblioteca.dao.*;
import com.fatec.biblioteca.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Serviços extends JPanel {

    private JComboBox<Leitor> cbLeitores;
    private JComboBox<Copia> cbCopias;
    private JButton btnEmprestar;
    private JButton btnDevolver;

    private JTable tabelaEmprestimos;
    private DefaultTableModel modeloTabela;

    private EmprestimoDAO emprestimoDAO;
    private LeitorDAO leitorDAO;
    private CopiaDAO copiaDAO;
    private Funcionario funcionarioSessao;

    public Serviços(Funcionario funcionario) {
        this.funcionarioSessao = funcionario;
        this.emprestimoDAO = new EmprestimoDAOMySQL();
        this.leitorDAO = new LeitorDAOMySQL();
        this.copiaDAO = new CopiaDAOMySQL();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. FORMULÁRIO DE SELEÇÃO (Painel Superior)
        JPanel painelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

        cbLeitores = new JComboBox<>();
        cbCopias = new JComboBox<>();
        btnEmprestar = new JButton("Registrar Empréstimo");
        btnDevolver = new JButton("Registrar Devolução");
        btnDevolver.setBackground(new Color(235, 77, 75)); //
        btnDevolver.setForeground(Color.BLACK);

        painelFormulario.add(new JLabel("Leitor / Cliente:"));
        painelFormulario.add(cbLeitores);
        painelFormulario.add(new JLabel("Cópia do Livro:"));
        painelFormulario.add(cbCopias);
        painelFormulario.add(btnEmprestar);
        painelFormulario.add(btnDevolver);
        add(painelFormulario, BorderLayout.NORTH);

        // 2. TABELA GRÁFICA DE MOVIMENTAÇÕES (Painel Centro)
        modeloTabela = new DefaultTableModel(new Object[]{
                "ID", "Leitor", "Livro (Código)", "Data Empréstimo", "Prazo Devolução", "Data Entrega Real", "Multa"
        }, 0);
        tabelaEmprestimos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaEmprestimos);
        add(scrollPane, BorderLayout.CENTER);

        // 3. EVENTOS DOS BOTÕES
        btnEmprestar.addActionListener(e -> registrar());
        btnDevolver.addActionListener(e -> registrarDevolucao());

        // Carrega as caixas de seleção e a tabela ao abrir
        carregarComponentes();
        atualizarTabela();
    }

    private void carregarComponentes() {
        try {
            // Limpa e recarrega os Leitores no ComboBox
            cbLeitores.removeAllItems();
            List<Leitor> leitores = leitorDAO.listarLeitor();
            for (Leitor l : leitores) {
                cbLeitores.addItem(l); // O Swing usa o toString() automático que programamos!
            }

            // Limpa e recarrega apenas as Cópias que estão DISPONÍVEIS
            cbCopias.removeAllItems();
            List<Copia> copias = copiaDAO.listarCopia();
            for (Copia c : copias) {
                if (c.getDisponivel()) {
                    cbCopias.addItem(c);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar seleções: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrar() {
        // 1. Captura os objetos selecionados nas caixinhas de clique (ComboBox)
        Leitor leitorSelecionado = (Leitor) cbLeitores.getSelectedItem();
        Copia copiaSelecionada = (Copia) cbCopias.getSelectedItem();

        // Validação visual de segurança
        if (leitorSelecionado == null || copiaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione um leitor e uma cópia disponível para realizar o empréstimo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Cria o objeto do empréstimo associando os IDs corretos do banco de dados
            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setLeitor(leitorSelecionado);
            novoEmprestimo.setCopia(copiaSelecionada);
            novoEmprestimo.setFuncionario(new Funcionario(1, "foo", "", "", "", "")); // Pega o funcionário foo/bar que fez o login

            // Dispara o cano de cobre até o DAO do MySQL
            emprestimoDAO.registrar(novoEmprestimo);

            JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso! O livro foi retirado da estante.");

            // Recarrega os seletores e a tabela visual na mesma hora
            carregarComponentes();
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar empréstimo: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarDevolucao() {
        int linhaSelecionada = tabelaEmprestimos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um empréstimo ativo na tabela para devolver!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pega o ID do Empréstimo (Coluna 0) e o ID da Cópia Física (Coluna 1) vindos da JTable
        int idEmprestimo = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        int idCopia = (int) modeloTabela.getValueAt(linhaSelecionada, 1);
        String tituloLivro = (String) modeloTabela.getValueAt(linhaSelecionada, 2);

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Confirmar a devolução da obra: " + tituloLivro + "?",
                "Registrar Devolução", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                Emprestimo emprestimoParaDevolver = new Emprestimo();
                emprestimoParaDevolver.setIdEmprestimo(idEmprestimo);
                emprestimoDAO.registrarDevolucao(emprestimoParaDevolver);

                JOptionPane.showMessageDialog(this, "Devolvido com sucesso! O livro retornou para a lista de disponíveis na estante.");

                carregarComponentes();
                atualizarTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar devolução: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        try {
            List<Emprestimo> lista = emprestimoDAO.listarEmprestimo();
            for (Emprestimo e : lista) {
                double multaAtual = e.getDataEntregaReal() != null ? e.calcularMulta(2.00) : 0.0;

                modeloTabela.addRow(new Object[]{
                        e.getIdEmprestimo(),
                        e.getLeitor().getNome(),
                        e.getCopia().getLivro().getTitulo() + " (" + e.getCopia().getCodigoHex() + ")",
                        e.getDataEmprestimo(),
                        e.getDataDevolucao(),
                        e.getDataEntregaReal() != null ? e.getDataEntregaReal() : "ATIVO",
                        multaAtual > 0 ? "R$ " + String.format("%.2f", multaAtual) : "R$ 0,00"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar movimentações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
