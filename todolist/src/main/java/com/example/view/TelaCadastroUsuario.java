package com.example.view;

import javax.swing.*;
import com.example.controller.UsuarioController;
import com.example.model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaCadastroUsuario extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;

    public TelaCadastroUsuario() {
        // Configurações da janela
        setTitle("Cadastro de Usuário");
        setLayout(new GridBagLayout());  // Usando GridBagLayout para melhor controle de layout
        GridBagConstraints gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));  // Cor de fundo suave

        // Fonte para os labels e botões
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        
        // Cores para os componentes
        Color buttonColor = new Color(66, 135, 245);  // Azul
        Color labelColor = new Color(50, 50, 50);  // Cinza escuro para os labels
        Color fieldColor = Color.WHITE;  // Cor de fundo para os campos de texto

        // Criando os componentes
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(labelFont);
        nomeLabel.setForeground(labelColor);

        nomeField = new JTextField(20);
        nomeField.setFont(inputFont);
        nomeField.setBackground(fieldColor);
        nomeField.setPreferredSize(new Dimension(250, 30));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(labelColor);

        emailField = new JTextField(20);
        emailField.setFont(inputFont);
        emailField.setBackground(fieldColor);
        emailField.setPreferredSize(new Dimension(250, 30));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(buttonFont);
        salvarButton.setBackground(buttonColor);
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        salvarButton.setPreferredSize(new Dimension(250, 40));
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Definindo as posições no GridBagLayout
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        add(nomeLabel, gbc);

        gbc.gridx = 1; 
        add(nomeField, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1;
        add(emailLabel, gbc);

        gbc.gridx = 1; 
        add(emailField, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 2;  // O botão ocupa duas colunas
        add(salvarButton, gbc);

        // Ação do botão "Salvar"
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();

                if (nome.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }

                try {
                    Usuario usuario = new Usuario(0, nome, email);  // id é gerado automaticamente no DB
                    UsuarioController.salvarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    nomeField.setText("");  // Limpar os campos após o cadastro
                    emailField.setText(""); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário.");
                }
            }
        });

        // Configurações da janela
        setSize(400, 250);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroUsuario().setVisible(true));
    }
}
