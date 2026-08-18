package eduvault.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            
            // Convertir bytes a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // MÉTODO PARA PROBAR
    public static void main(String[] args) {
        System.out.println("Hash de admin123: " + hashPassword("admin123"));
        System.out.println("Hash de estudiante123: " + hashPassword("estudiante123"));
        
        // Verificar que coincidan con los que están en la BD
        String esperadoAdmin = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        String esperadoEstudiante = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4";
        
        System.out.println("\n=== VERIFICACIÓN ===");
        System.out.println("admin123 coincide con BD? " + hashPassword("admin123").equals(esperadoAdmin));
        System.out.println("estudiante123 coincide con BD? " + hashPassword("estudiante123").equals(esperadoEstudiante));
    }
}