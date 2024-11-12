package com.example;

import javax.swing.*;

import com.example.view.TelaCadastroUsuario;
import com.example.view.TelaInicial;

public class Main {
    public static void main(String[] args) {
        // Configuração para garantir que a interface gráfica seja criada na thread adequada
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Aqui você pode escolher qual tela abrir inicialmente
                // No caso, vamos abrir a tela de cadastro de usuário
                new TelaInicial().setVisible(true);
                
                // Caso queira abrir a tela de cadastro de tarefa, basta descomentar a linha abaixo:
                // new TelaCadastroTarefa().setVisible(true);
            }
        });
    }
}
