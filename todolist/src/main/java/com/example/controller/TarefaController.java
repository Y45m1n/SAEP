package com.example.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.Database;
import com.example.model.Tarefa;
import com.example.model.Usuario;

public class TarefaController {

    public static void salvarTarefa(Tarefa tarefa) throws SQLException {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO tarefas (usuario_id, descricao, nome_setor, prioridade, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, tarefa.getUsuarioId());
                pstmt.setString(2, tarefa.getDescricao());
                pstmt.setString(3, tarefa.getNomeSetor());
                pstmt.setString(4, tarefa.getPrioridade());
                pstmt.setString(5, tarefa.getStatus());
                pstmt.executeUpdate();
            }
        }
    }

   public static List<Tarefa> listarTarefasPorStatus(String status) throws SQLException {
    List<Tarefa> tarefas = new ArrayList<>();
    String sql = "SELECT * FROM tarefas WHERE status = ?"; // Filtra as tarefas pelo status

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerencia_tarefas", "usuario", "senha");
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, status);  // Define o parâmetro da consulta (status)

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getInt("id"),
                    rs.getInt("usuario_id"),
                    rs.getString("descricao"),
                    rs.getString("nome_setor"),
                    rs.getString("prioridade"),
                    rs.getString("status")
                );
                tarefas.add(tarefa); // Adiciona a tarefa à lista
            }
        }
    }

    return tarefas;
}

public static Usuario buscarUsuarioPorId(int usuarioId) throws SQLException {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE id = ?"; // Consulta para encontrar o usuário

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerencia_tarefas", "usuario", "senha");
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setInt(1, usuarioId);  // Define o parâmetro para a consulta (usuario_id)

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                );
            }
        }
    }

    return usuario;
}


public static List<Tarefa> listarTarefas() throws SQLException {
    List<Tarefa> tarefas = new ArrayList<>();
    String sql = "SELECT * FROM tarefas"; // Consulta para listar todas as tarefas

    try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerencia_tarefas", "usuario", "senha");
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Tarefa tarefa = new Tarefa(
                rs.getInt("id"),
                rs.getInt("usuario_id"),
                rs.getString("descricao"),
                rs.getString("nome_setor"),
                rs.getString("prioridade"),
                rs.getString("status")
            );
            tarefas.add(tarefa); // Adiciona a tarefa à lista
        }
    }

    return tarefas;
}

}

