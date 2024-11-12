package com.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.example.controller.TarefaController;
import com.example.model.Tarefa;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaListagemTarefas extends JFrame {
    
    private JTable tabelaAFazer;
    private JTable tabelaFazendo;
    private JTable tabelaFeito;

    public TelaListagemTarefas() {
        // Configuração da janela
        setTitle("Listagem de Tarefas");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Criando tabelas
        tabelaAFazer = criarTabela("A Fazer");
        tabelaFazendo = criarTabela("Fazendo");
        tabelaFeito = criarTabela("Feito");

        // Adicionando tabelas à janela
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JScrollPane(tabelaAFazer), gbc);

        gbc.gridy = 1;
        add(new JScrollPane(tabelaFazendo), gbc);

        gbc.gridy = 2;
        add(new JScrollPane(tabelaFeito), gbc);

        // Carregar tarefas do banco de dados
        carregarTarefas();

        // Configurações da janela
        setSize(600, 400); // Tamanho da janela
    }

    private JTable criarTabela(String status) {
        String[] colunas = {"ID", "Descrição", "Setor", "Prioridade", "Usuário"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setDefaultEditor(Object.class, null); // Desabilita edição

        // Adiciona título da tabela
        modelo.addRow(new Object[] {status, "", "", "", ""});
        
        return tabela;
    }

    private void carregarTarefas() {
        // Carrega tarefas de cada status (A Fazer, Fazendo, Feito)
        List<Tarefa> tarefasAFazer = TarefaController.listarTarefasPorStatus("A Fazer");
        List<Tarefa> tarefasFazendo = TarefaController.listarTarefasPorStatus("Fazendo");
        List<Tarefa> tarefasFeito = TarefaController.listarTarefasPorStatus("Feito");

        // Preenche as tabelas com as tarefas
        preencherTabela(tabelaAFazer, tarefasAFazer);
        preencherTabela(tabelaFazendo, tarefasFazendo);
        preencherTabela(tabelaFeito, tarefasFeito);
    }

    private void preencherTabela(JTable tabela, List<Tarefa> tarefas) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(1); // Limpa as linhas, mantendo o cabeçalho

        for (Tarefa tarefa : tarefas) {
            modelo.addRow(new Object[] {
                tarefa.getId(),
                tarefa.getDescricao(),
                tarefa.getNomeSetor(),
                tarefa.getPrioridade() //,
              //  tarefa.
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaListagemTarefas().setVisible(true);
            }
        });
    }
}
