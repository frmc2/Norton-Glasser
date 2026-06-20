package com.fatec.biblioteca.UI; // Mantém o seu padrão de pacote

import com.fatec.biblioteca.modelos.Funcionario;
import javax.swing.*;
import java.awt.*;

// O 'extends JFrame' aplica a herança nativa do Java para transformar a classe em uma janela
public class MenuPrincipal extends JFrame {

    private Funcionario funcionarioLogado;
    private JTabbedPane painelAbas;

    // O construtor recebe o objeto do funcionário vindo direto da validação da Tela de Login
    public MenuPrincipal(Funcionario funcionario) {
        this.funcionarioLogado = funcionario;

        // 1. PALETA DE CORES INTEGRADA (Mesma da Tela de Login para manter a identidade)
        Color azulEscuroProfundo = new Color(44, 62, 80);   // #2c3e50 (Status e Destaques)
        Color cinzaAzuladoFundo = new Color(240, 244, 248); // #f0f4f8 (Fundo Confortável)

        // 2. TAMANHO DINÂMICO (Abordagem B - Ocupa 70% do monitor do usuário)
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = (int) (screenSize.width * 0.7);
        int altura = (int) (screenSize.height * 0.7);
        setSize(largura, altura);

        setTitle("Menu Principal - Sistema de Controle de Biblioteca FATEC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o processo do Java ao fechar a janela
        setLocationRelativeTo(null); // Centraliza milimetricamente a janela no meio do monitor

        // 3. LAYOUT BASE
        setLayout(new BorderLayout());
        getContentPane().setBackground(cinzaAzuladoFundo); // Pinta o fundo base da janela mãe

        // Barra superior estilizada para mostrar o operador logado no momento
        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 8));
        painelStatus.setBackground(azulEscuroProfundo); // Mudado de cinza claro para Azul Escuro Profundo

        JLabel lblUsuario = new JLabel("Operador: " + funcionarioLogado.getNome() + " [" + funcionarioLogado.getCargo() + "]");
        lblUsuario.setForeground(Color.black); // Texto branco para dar contraste com o fundo escuro
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 13));

        painelStatus.add(lblUsuario);
        add(painelStatus, BorderLayout.NORTH);

        // 4. O PAINEL DE ABAS COM OS ACIONADORES REAIS PLUGADOS
        painelAbas = new JTabbedPane();
        painelAbas.setBackground(cinzaAzuladoFundo); // Pinta a barra de navegação das abas

        // Aba 1: Acervo (Livros e Cópias)
        Acervo abaAcervo = new Acervo();
        painelAbas.addTab("Acervo & Livros", abaAcervo);

        // Aba 2: Pessoas (Leitores / Clientes)
        GerenciamentoLeitor abaClientes = new GerenciamentoLeitor();
        painelAbas.addTab("Controle de Leitores", abaClientes);

        // Aba 3: Serviços (Empréstimos e Devoluções)
        Serviços abaServicos = new Serviços(funcionarioLogado);
        painelAbas.addTab("Serviços & Movimentações", abaServicos);

        // Aba 4: Fila de Espera (Reservas exigidas no enunciado)
        Reservas abaReservas = new Reservas();
        painelAbas.addTab("Reservas", abaReservas);

        // Aba 5: Segurança (Equipe - Restrito para ADMINISTRADOR)
        if (funcionarioLogado.getCargo().equalsIgnoreCase("ADMINISTRADOR")) {
            GerenciamentoFuncionarios abaEquipe = new GerenciamentoFuncionarios();
            painelAbas.addTab("Configurações de Equipe", abaEquipe);
        }

        // Adiciona o painel contendo todas as abas organizadas no centro da janela elástica
        add(painelAbas, BorderLayout.CENTER);
    }
}
