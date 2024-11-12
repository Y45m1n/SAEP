package com.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.example.controller.TarefaController;
import com.example.controller.UsuarioController;
import com.example.model.Tarefa;
import com.example.model.Usuario;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaListagemTarefas extends JFrame {
    private JTable tabelaTarefas;
    private DefaultTableModel tableModel;

    public TelaListagemTarefas() {
        // Configuração da janela
        setTitle("Listagem de Tarefas");
        setLayout(new BorderLayout()); // Layout principal para dividir entre tabela e botões
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fechar sem encerrar a aplicação
        setLocationRelativeTo(null); // Centraliza a janela
        getContentPane().setBackground(new Color(240, 240, 240)); // Fundo suave

        // Criar tabela para exibição das tarefas
        String[] colunas = {"ID", "Descrição", "Setor", "Prioridade", "Usuário", "Status"};
        tableModel = new DefaultTableModel(colunas, 0);  // Inicializa a tabela com as colunas definidas
        tabelaTarefas = new JTable(tableModel);

        // Configurações da tabela
        tabelaTarefas.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaTarefas.setRowHeight(25); // Tamanho das linhas
        tabelaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Seleção de uma linha por vez
        tabelaTarefas.setFillsViewportHeight(true); // Preencher a tela
        JScrollPane scrollPane = new JScrollPane(tabelaTarefas); // Adiciona a barra de rolagem

        // Adicionar o scrollPane com a tabela na janela
        add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a listagem
        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setFont(new Font("Arial", Font.BOLD, 14));
        atualizarButton.setBackground(new Color(66, 135, 245));
        atualizarButton.setForeground(Color.WHITE);
        atualizarButton.setPreferredSize(new Dimension(100, 40));
        atualizarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Adicionar o botão de atualizar no painel inferior
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Alinha o botão no centro
        panel.setBackground(new Color(240, 240, 240)); // Cor do painel
        panel.add(atualizarButton);
        add(panel, BorderLayout.SOUTH);

        // Ação para o botão de atualização
        atualizarButton.addActionListener(e -> carregarTarefas());

        // Carregar as tarefas ao iniciar a tela
        carregarTarefas();

        // Configuração da janela
        setSize(800, 400); // Ajuste de tamanho
        setVisible(true); // Tornar a janela visível
    }

    // Método para carregar as tarefas e exibi-las na tabela
    private void carregarTarefas() {
        try {
            // Limpa a tabela antes de adicionar novas tarefas
            tableModel.setRowCount(0);

            // Recupera a lista de tarefas do banco
            List<Tarefa> tarefas = TarefaController.listarTarefas();

            if (tarefas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma tarefa encontrada.");
            }

            // Preenche a tabela com as tarefas
            for (Tarefa tarefa : tarefas) {
                Usuario usuario = UsuarioController.getUsuarioPorId(tarefa.getUsuarioId()); // Busca o usuário relacionado
                String usuarioNome = usuario != null ? usuario.getNome() : "Usuário não encontrado"; // Nome do usuário
                
                Object[] linha = {
                    tarefa.getId(),
                    tarefa.getDescricao(),
                    tarefa.getNomeSetor(),
                    tarefa.getPrioridade(),
                    usuarioNome,
                    tarefa.getStatus()
                };
                tableModel.addRow(linha); // Adiciona uma nova linha à tabela
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar as tarefas.");
        }
    }
}
