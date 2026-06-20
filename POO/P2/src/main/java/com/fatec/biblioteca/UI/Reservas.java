package com.fatec.biblioteca.UI;

import com.fatec.biblioteca.dao.*;
import com.fatec.biblioteca.modelos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Reservas extends JPanel {

    private JComboBox<Leitor> cbLeitores;
    private JComboBox<Livro> cbLivros;
    private JButton btnReservar;
    private JButton btnCancelar;

    private JTable tabelaReservas;
    private DefaultTableModel modeloTabela;

    private ReservaDAO reservaDAO;
    private LeitorDAO leitorDAO;
    private LivroDAO livroDAO;

    public Reservas() {
        this.reservaDAO = new ReservaDAOMySQL();
        this.leitorDAO = new LeitorDAOMySQL();
        this.livroDAO = new LivroDAOMySQL();

        // Organização elástica com gerenciador de layout do AWT
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. FORMULÁRIO DE CADASTRO DE RESERVA (Painel Superior)
        JPanel painelFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));

        cbLeitores = new JComboBox<>();
        cbLivros = new JComboBox<>();
        btnReservar = new JButton("Efetuar Reserva");

        btnCancelar = new JButton("Cancelar / Atender Reserva");
        btnCancelar.setBackground(new Color(235, 77, 75)); // Vermelho amigável
        btnCancelar.setForeground(Color.BLACK);

        painelFormulario.add(new JLabel("Leitor:"));
        painelFormulario.add(cbLeitores);
        painelFormulario.add(new JLabel("Livro da Obra:"));
        painelFormulario.add(cbLivros);
        painelFormulario.add(btnReservar);
        painelFormulario.add(btnCancelar);
        add(painelFormulario, BorderLayout.NORTH);

        // 2. TABELA GRÁFICA DA FILA DE ESPERA (Painel Centro)
        modeloTabela = new DefaultTableModel(new Object[]{"ID Reserva", "Nome do Leitor", "Título do Livro", "Data da Reserva"}, 0);
        tabelaReservas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaReservas);
        add(scrollPane, BorderLayout.CENTER);

        // 3. EVENTOS DOS BOTÕES
        btnReservar.addActionListener(e -> registrarReserva());
        btnCancelar.addActionListener(e -> cancelarReserva());

        // Inicializa os dados na tela
        carregarComponentes();
        atualizarTabela();
    }

    private void carregarComponentes() {
        try {
            cbLeitores.removeAllItems();
            List<Leitor> leitores = leitorDAO.listarLeitor();
            for (Leitor l : leitores) {
                cbLeitores.addItem(l); // Usa camelCase e o toString() automático
            }

            cbLivros.removeAllItems();
            List<Livro> livros = livroDAO.listarLivro();
            for (Livro liv : livros) {
                cbLivros.addItem(liv); // Usa camelCase e o toString() automático
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar seleções: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarReserva() {
        Leitor leitorSelecionado = (Leitor) cbLeitores.getSelectedItem();
        Livro livroSelecionado = (Livro) cbLivros.getSelectedItem();

        if (leitorSelecionado == null || livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um leitor e uma obra para incluir na fila de espera!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Instancia o objeto da Reserva ligando o ID do Leitor com o ID Geral do Livro
            Reserva novaReserva = new Reserva();
            novaReserva.setLeitor(leitorSelecionado);
            novaReserva.setLivro(livroSelecionado);

            // Envia os dados para a query INSERT do DAO do MySQL
            reservaDAO.registrarReserva(novaReserva);

            JOptionPane.showMessageDialog(this, "Reserva realizada! O cliente foi inserido na fila de espera de forma cronológica.");

            // Limpa a tela e redesenha a JTable com o novo cliente na fila na mesma hora
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar na fila de reservas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void cancelarReserva() {
        int linhaSelecionada = tabelaReservas.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva ativa na tabela para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pega o ID da linha selecionada (Coluna 0 da tabela)
        int idReserva = (int) modeloTabela.getValueAt(linhaSelecionada, 0);

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente remover esta reserva do sistema?", "Confirmar Ação", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            try {
                // Executa o método unificado de deletar do DAO que conversamos
                reservaDAO.deletar(idReserva);

                JOptionPane.showMessageDialog(this, "Reserva baixada/removida com sucesso!");
                atualizarTabela(); // Atualiza a tabela na tela
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao deletar do banco: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa as linhas visuais da tela
        try {
            List<Reserva> lista = reservaDAO.listarReserva();
            for (Reserva r : lista) {
                // Despeja os dados mapeados na tabela gráfica do Swing
                modeloTabela.addRow(new Object[]{
                        r.getIdReserva(),
                        r.getLeitor().getNome(),
                        r.getLivro().getTitulo(),
                        r.getDataReserva()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar lista de reservas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
