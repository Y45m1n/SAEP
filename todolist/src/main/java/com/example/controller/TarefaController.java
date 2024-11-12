package com.example.controller;

import java.sql.*;
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

    public static List<Tarefa> listarTarefasPorStatus(String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTarefasPorStatus'");
    }

    public static Usuario buscarUsuarioPorId(int usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUsuarioPorId'");
    }
}

