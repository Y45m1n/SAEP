package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        // Configurações da janela
        setTitle("Tela Inicial");
        setLayout(new GridBagLayout()); // Usando GridBagLayout para mais flexibilidade
        GridBagConstraints gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cor de fundo
        getContentPane().setBackground(new Color(50, 50, 50));

        // Configuração de fontes
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = new Color(66, 135, 245); // Azul

        // Criando os botões
        JButton cadastroUsuarioButton = new JButton("Cadastro de Usuário");
        JButton cadastroTarefaButton = new JButton("Cadastro de Tarefa");
        JButton listarTarefasButton = new JButton("Listar Tarefas");

        // Personalizando os botões
        customizeButton(cadastroUsuarioButton, buttonFont, buttonColor);
        customizeButton(cadastroTarefaButton, buttonFont, buttonColor);
        customizeButton(listarTarefasButton, buttonFont, buttonColor);

        // Definindo o GridBagLayout para os botões
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(cadastroUsuarioButton, gbc);

        gbc.gridy = 1;
        add(cadastroTarefaButton, gbc);

        gbc.gridy = 2;
        add(listarTarefasButton, gbc);

        // Ação para abrir a tela de cadastro de usuário
        cadastroUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroUsuario().setVisible(true);  // Abre a tela de cadastro de usuário
            }
        });

        // Ação para abrir a tela de cadastro de tarefa
        cadastroTarefaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroTarefa().setVisible(true);  // Abre a tela de cadastro de tarefa
            }
        });

        // Ação para abrir a tela de listar tarefas
        listarTarefasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaListagemTarefas().setVisible(true);  // Abre a tela de listar tarefas
            }
        });

        // Definindo o tamanho da janela
        setSize(400, 300);
    }

    // Método para estilizar os botões
    private void customizeButton(JButton button, Font font, Color bgColor) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE); // Cor do texto
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borda branca
        button.setPreferredSize(new Dimension(300, 50)); // Tamanho do botão
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
        
    }
}
