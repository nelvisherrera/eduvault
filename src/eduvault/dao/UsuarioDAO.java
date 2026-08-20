package eduvault.dao;

import eduvault.conexion.DatabaseConnection;
import eduvault.model.Usuario;
import eduvault.utils.PasswordHasher;

import java.sql.*;

public class UsuarioDAO {
    
    // MÉTODO LOGIN CON DEBUG
    public Usuario login(String correo, String contrasena) {
        System.out.println("Intentando login...");
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasena);
        
        // Generar hash de la contraseña
        String contrasenaHash = PasswordHasher.hashPassword(contrasena);
        System.out.println("🔐 Hash generado: " + contrasenaHash);
        
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ? AND estado = 'ACTIVO'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasenaHash);
            
            System.out.println("🔍 Ejecutando consulta...");
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("✅ Usuario encontrado!");
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre_completo"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setCarrera(rs.getString("carrera"));
                usuario.setSemestre(rs.getInt("semestre"));
                return usuario;
            } else {
                System.out.println("❌ Usuario NO encontrado en la base de datos");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // MÉTODO LOGIN - Versión SIN CIFRAR para pruebas (IMPORTANTE: SOLO PARA PROBAR)
    public Usuario loginSinCifrar(String correo, String contrasena) {
        System.out.println("🔍 LOGIN SIN CIFRAR - SOLO PRUEBAS");
        
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ? AND estado = 'ACTIVO'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena); // Sin cifrar - SOLO PRUEBAS
            
            System.out.println("🔍 Ejecutando consulta sin cifrar...");
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("✅ Usuario encontrado (sin cifrar)!");
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre_completo"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setCarrera(rs.getString("carrera"));
                usuario.setSemestre(rs.getInt("semestre"));
                return usuario;
            } else {
                System.out.println("❌ Usuario NO encontrado (sin cifrar)");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error en login sin cifrar: " + e.getMessage());
        }
        return null;
    }
    
    public boolean registrar(Usuario usuario) {
        String contrasenaHash = PasswordHasher.hashPassword(usuario.getContrasena());
        String sql = "INSERT INTO usuarios (nombre_completo, correo, contrasena, rol, estado, carrera, semestre) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, contrasenaHash);
            pstmt.setString(4, "ESTUDIANTE");
            pstmt.setString(5, "ACTIVO");
            pstmt.setString(6, usuario.getCarrera());
            pstmt.setInt(7, usuario.getSemestre());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
        }
        return false;
    }
}