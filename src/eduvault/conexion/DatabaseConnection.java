package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://ep-crimson-queen-ayo4q32o-pooler.c-5.us-east-2.aws.neon.tech/neondb?sslmode=require&channel_binding=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_KL72XVEMpsna";
    private static Connection connection = null;
    
    private DatabaseConnection() {}
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conectado a Neon Serverless");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error de conexion: " + e.getMessage());
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar: " + e.getMessage());
            }
        }
    }
}