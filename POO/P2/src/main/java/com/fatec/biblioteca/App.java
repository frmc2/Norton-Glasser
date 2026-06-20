package com.fatec.biblioteca;

import com.fatec.biblioteca.UI.TelaLogin;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível carregar o visual nativo: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
