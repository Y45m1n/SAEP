package com.example.controller;

import java.sql.*;
import java.util.*;

import com.example.Database;
import com.example.model.Usuario;

public class UsuarioController {

    public static void salvarUsuario(Usuario usuario) throws SQLException {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.executeUpdate();
            }
        }
    }

    public static List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = Database.connect()) {
            String sql = "SELECT * FROM usuarios";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }
}
