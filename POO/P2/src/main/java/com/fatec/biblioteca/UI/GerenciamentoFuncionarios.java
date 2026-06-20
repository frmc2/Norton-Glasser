package com.fatec.biblioteca.UI; // Mantém o seu padrão de pacote

import com.fatec.biblioteca.dao.FuncionarioDAO;
import com.fatec.biblioteca.dao.FuncionarioDAOMySQL;
import com.fatec.biblioteca.modelos.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GerenciamentoFuncionarios extends JPanel {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtLogin;
    private JTextField txtCargo;
    private JPasswordField txtSenha;
    private JButton btnSalvar;

    private JTable tabelaFuncionarios;
    private DefaultTableModel modeloTabela;
    private FuncionarioDAO funcionarioDAO; // CORRIGIDO: Agora usa o DAO de Funcionários

    public GerenciamentoFuncionarios() {
        this.funcionarioDAO = new FuncionarioDAOMySQL();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. FORMULÁRIO DE CADASTRO (Painel Superior)
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));

        txtNome = new JTextField(10);
        txtCpf = new JTextField(8);
        txtLogin = new JTextField(6);
        txtSenha = new JPasswordField(6);
        txtCargo = new JTextField(8); // Ex: ADMINISTRADOR ou ATENDENTE
        btnSalvar = new JButton("Salvar Funcionário");

        painelCadastro.add(new JLabel("Nome:")); painelCadastro.add(txtNome);
        painelCadastro.add(new JLabel("CPF:")); painelCadastro.add(txtCpf);
        painelCadastro.add(new JLabel("Login:")); painelCadastro.add(txtLogin);
        painelCadastro.add(new JLabel("Senha:")); painelCadastro.add(txtSenha);
        painelCadastro.add(new JLabel("Cargo:")); painelCadastro.add(txtCargo);
        painelCadastro.add(btnSalvar);
        add(painelCadastro, BorderLayout.NORTH);

        // 2. TABELA GRÁFICA DE LISTAGEM (Painel Centro)
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome do Operador", "CPF", "Login", "Cargo"}, 0);
        tabelaFuncionarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaFuncionarios);
        add(scrollPane, BorderLayout.CENTER);

        // 3. PAINEL INFERIOR: Botão elástico de exclusão de operadores
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemover = new JButton("Remover Funcionário Selecionado");
        btnRemover.setBackground(new Color(235, 77, 75)); // Vermelho moderno
        btnRemover.setForeground(Color.black);
        painelInferior.add(btnRemover);
        add(painelInferior, BorderLayout.SOUTH);

        // 4. EVENTOS DOS BOTÕES
        btnRemover.addActionListener(e -> deletarFuncionario());
        btnSalvar.addActionListener(e -> salvarFuncionario());

        // Carrega a listagem do banco ao abrir
        atualizarTabela();
    }

    private void salvarFuncionario() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String cargo = txtCargo.getText().toUpperCase().trim();

        if (nome.isEmpty() || cpf.isEmpty() || login.isEmpty() || senha.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos do funcionário!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario novoFuncionario = new Funcionario(0, nome, cpf, login, senha, cargo);
            funcionarioDAO.salvar(novoFuncionario);

            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");

            txtNome.setText(""); txtCpf.setText(""); txtLogin.setText(""); txtSenha.setText(""); txtCargo.setText("");
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarFuncionario() {
        int linhaSelecionada = tabelaFuncionarios.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um funcionário na tabela para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // CORRIGIDO: Pega os dados reais da tabela de funcionários
        int idFuncionario = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeFuncionario = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        // Trava de segurança para o desenvolvedor não se apagar sem querer do sistema
        if (nomeFuncionario.equals("foo")) {
            JOptionPane.showMessageDialog(this, "Por motivos de segurança, o usuário master 'foo' não pode ser removido do sistema!", "Ação Bloqueada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente revogar o acesso e excluir o funcionário " + nomeFuncionario + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                funcionarioDAO.deletar(idFuncionario);
                JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso do sistema!");
                atualizarTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Não foi possível remover o funcionário! Ele possui históricos de atendimentos vinculados no banco de dados.\nErro: " + ex.getMessage(), "Erro de Integridade", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        try {
            // CORRIGIDO: Mapeia a lista de funcionários de verdade vindas do MySQL
            List<Funcionario> lista = funcionarioDAO.listarFuncionario();
            for (Funcionario f : lista) {
                modeloTabela.addRow(new Object[]{f.getId(), f.getNome(), f.getCpf(), f.getLogin(), f.getCargo()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar equipe: " + ex.getMessage(), "Erro de Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }
}
