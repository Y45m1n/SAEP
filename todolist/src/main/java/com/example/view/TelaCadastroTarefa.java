package com.example.view;

import javax.swing.*;
import com.example.controller.TarefaController;
import com.example.controller.UsuarioController;
import com.example.model.Tarefa;
import com.example.model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaCadastroTarefa extends JFrame {
    private JTextField descricaoField;
    private JTextField nomeSetorField;
    private JComboBox<String> prioridadeComboBox;
    private JComboBox<Usuario> usuarioComboBox;
    private JComboBox<String> statusComboBox;  // Novo JComboBox para status

    public TelaCadastroTarefa() {
        // Configuração da janela
        setTitle("Cadastro de Tarefa");
        setLayout(new GridBagLayout()); // Usando GridBagLayout para um melhor alinhamento
        GridBagConstraints gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fechar sem encerrar a aplicação
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Fundo suave

        // Fonte e cores
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        
        Color buttonColor = new Color(66, 135, 245);  // Azul
        Color labelColor = new Color(50, 50, 50);  // Cinza escuro para os labels
        Color fieldColor = Color.WHITE;  // Cor de fundo para os campos de texto

        // Criando os componentes
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(labelFont);
        descricaoLabel.setForeground(labelColor);

        descricaoField = new JTextField(20);
        descricaoField.setFont(inputFont);
        descricaoField.setBackground(fieldColor);
        descricaoField.setPreferredSize(new Dimension(350, 40)); // Aumentado para melhor acomodar textos

        JLabel nomeSetorLabel = new JLabel("Nome do Setor:");
        nomeSetorLabel.setFont(labelFont);
        nomeSetorLabel.setForeground(labelColor);

        nomeSetorField = new JTextField(20);
        nomeSetorField.setFont(inputFont);
        nomeSetorField.setBackground(fieldColor);
        nomeSetorField.setPreferredSize(new Dimension(350, 40)); // Aumentado

        JLabel prioridadeLabel = new JLabel("Prioridade:");
        prioridadeLabel.setFont(labelFont);
        prioridadeLabel.setForeground(labelColor);

        prioridadeComboBox = new JComboBox<>(new String[]{"baixa", "média", "alta"});
        prioridadeComboBox.setFont(inputFont);
        prioridadeComboBox.setBackground(fieldColor);
        prioridadeComboBox.setPreferredSize(new Dimension(350, 40)); // Aumentado

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(labelFont);
        usuarioLabel.setForeground(labelColor);

        // Carregar usuários do banco de dados
        try {
            List<Usuario> usuarios = UsuarioController.listarUsuarios();
            usuarioComboBox = new JComboBox<>();
            for (Usuario usuario : usuarios) {
                usuarioComboBox.addItem(usuario); // O toString() vai ser chamado automaticamente
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuários.");
        }
        usuarioComboBox.setFont(inputFont);
        usuarioComboBox.setBackground(fieldColor);
        usuarioComboBox.setPreferredSize(new Dimension(350, 40)); // Aumentado

        // Novo JLabel e JComboBox para Status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(labelFont);
        statusLabel.setForeground(labelColor);

        statusComboBox = new JComboBox<>(new String[]{"a fazer", "fazendo", "pronto"});
        statusComboBox.setFont(inputFont);
        statusComboBox.setBackground(fieldColor);
        statusComboBox.setPreferredSize(new Dimension(350, 40));

        // Botão Salvar
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setFont(buttonFont);
        salvarButton.setBackground(buttonColor);
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        salvarButton.setPreferredSize(new Dimension(350, 50)); // Ajustando o tamanho do botão
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Definindo as posições no GridBagLayout
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Margens entre os componentes
        add(descricaoLabel, gbc);

        gbc.gridx = 1;
        add(descricaoField, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1;
        add(nomeSetorLabel, gbc);

        gbc.gridx = 1;
        add(nomeSetorField, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2;
        add(prioridadeLabel, gbc);

        gbc.gridx = 1;
        add(prioridadeComboBox, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 3;
        add(usuarioLabel, gbc);

        gbc.gridx = 1;
        add(usuarioComboBox, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 4;
        add(statusLabel, gbc);  // Adiciona o campo de status

        gbc.gridx = 1;
        add(statusComboBox, gbc);  // Adiciona o JComboBox de status

        gbc.gridx = 0; 
        gbc.gridy = 5;
        gbc.gridwidth = 2;  // O botão ocupa duas colunas
        add(salvarButton, gbc);

        // Ação do botão "Salvar"
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = descricaoField.getText();
                String nomeSetor = nomeSetorField.getText();
                String prioridade = (String) prioridadeComboBox.getSelectedItem();
                Usuario usuarioSelecionado = (Usuario) usuarioComboBox.getSelectedItem();
                String status = (String) statusComboBox.getSelectedItem(); // Pega o status selecionado

                // Validação dos campos
                if (descricao.isEmpty() || nomeSetor.isEmpty() || usuarioSelecionado == null || prioridade == null || status == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }

                // Criação da tarefa com o status selecionado
                Tarefa tarefa = new Tarefa(0, usuarioSelecionado.getId(), descricao, nomeSetor, prioridade, status);
                try {
                    TarefaController.salvarTarefa(tarefa);
                    JOptionPane.showMessageDialog(null, "Cadastro concluído com sucesso!");
                    // Limpar os campos após o cadastro
                    descricaoField.setText("");  
                    nomeSetorField.setText("");
                    prioridadeComboBox.setSelectedIndex(0);
                    usuarioComboBox.setSelectedIndex(0);
                    statusComboBox.setSelectedIndex(0); // Resetando o status
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar a tarefa.");
                }
            }
        });

        // Configurações da janela
        setSize(500, 500); // Ajuste do tamanho da janela para acomodar os campos aumentados
        setVisible(true);  // Garantir que a janela seja visível
    }
}
