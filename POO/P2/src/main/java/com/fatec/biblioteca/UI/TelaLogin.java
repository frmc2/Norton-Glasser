package com.fatec.biblioteca.UI;

import com.fatec.biblioteca.dao.FuncionarioDAO;
import com.fatec.biblioteca.dao.FuncionarioDAOMySQL;
import com.fatec.biblioteca.modelos.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private FuncionarioDAO funcionarioDAO;

    public TelaLogin() {
        this.funcionarioDAO = new FuncionarioDAOMySQL();

        // 1. CONFIGURAÇÃO DA PALETA DE CORES (Suave para a visão)
        Color azulEscuroProfundo = new Color(44, 62, 80);   // #2c3e50 (Títulos e Botões)
        Color cinzaAzuladoFundo = new Color(240, 244, 248); // #f0f4f8 (Fundo Confortável)
        Color textoEscuro = new Color(52, 73, 94);          // #34495e (Labels)

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = (int) (screenSize.width * 0.3);
        int altura = (int) (screenSize.height * 0.35);

        setTitle("Login de Funcionário");
        setSize(largura, altura);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // Painel Superior de Título (Azul Escuro Profundo)
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(azulEscuroProfundo);
        JLabel lblTitulo = new JLabel("CONTROLE DE BIBLIOTECA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);

        Font fonteLabels = new Font("Arial", Font.BOLD, 14);
        Font fonteCampos = new Font("Arial", Font.PLAIN, 14);

        // Formato Centralizado (Pintado com o Cinza Azulado Confortável)
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(cinzaAzuladoFundo); // Cor aplicada no fundo central
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsuario = new JLabel("Usuário / Login:");
        lblUsuario.setFont(fonteLabels);
        lblUsuario.setForeground(textoEscuro); // Texto escuro combinando com a paleta
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        painelFormulario.add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        txtUsuario.setFont(fonteCampos);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        painelFormulario.add(txtUsuario, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fonteLabels);
        lblSenha.setForeground(textoEscuro);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        painelFormulario.add(lblSenha, gbc);

        txtSenha = new JPasswordField(15);
        txtSenha.setFont(fonteCampos);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.7;
        painelFormulario.add(txtSenha, gbc);

        add(painelFormulario, BorderLayout.CENTER);

        // Painel Inferior do Botão (Alinhado ao fundo confortável)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(cinzaAzuladoFundo); // Garante que a parte de baixo não fique branca

        btnEntrar = new JButton("Entrar no Sistema");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEntrar.setBackground(azulEscuroProfundo); // Botão combinando com o título superior
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setPreferredSize(new Dimension(160, 35));

        // CORREÇÃO VISUAL: Remove a névoa padrão do Windows para ativar o azul real e limpo
        btnEntrar.setContentAreaFilled(false);
        btnEntrar.setOpaque(true);
        btnEntrar.setBorderPainted(false);

        painelBotoes.add(btnEntrar);
        add(painelBotoes, BorderLayout.SOUTH);

        btnEntrar.addActionListener(e -> efetuarLogin());
    }

    private void efetuarLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!","Campos Vazios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Funcionario funcionarioLogado = funcionarioDAO.autenticar(usuario, senha);

            if (funcionarioLogado != null) {
                JOptionPane.showMessageDialog(this, "Bem-vindo(a), " + funcionarioLogado.getNome() + "!");
                new MenuPrincipal(funcionarioLogado).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro crítico ao conectar ao banco: " + ex.getMessage(), "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
