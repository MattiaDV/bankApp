package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBconnection;

public class RegexInputValidator {
    private final DBconnection db_check = new DBconnection();

    private boolean emailValidator(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Errore input");
        }

        email = email.trim();

        if (email.length() > 100) {
            throw new IllegalArgumentException("Errore input");
        }

        if (email.isEmpty() || !email.matches("[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+\\.[a-z]+")) {
            throw new IllegalArgumentException("Errore input");
        }

        return true;
    }

    private boolean passwordValidator(String email, String pasw, String role) {
        if (pasw == null || pasw.length() < 8 || pasw.length() > 30 || pasw.isEmpty()) {
            throw new IllegalArgumentException("Errore input");
        }

        final String query = "SELECT email, password FROM users WHERE email = ? AND password = ? AND role = ?";

        if ("People".equals(role)) {
            role = "user";
        } else if ("Bank".equals(role)) {
            role = "bank";
        } else {
            throw new IllegalArgumentException("Input non valido");
        }

        try (Connection conn = db_check.getConn();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, pasw);
            stmt.setString(3, role);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new IllegalArgumentException("input non valido!");
        }

        throw new IllegalArgumentException("input non valido");
    }

    public boolean checkerDatas(String email, String pasw, String role) {
        emailValidator(email);
        passwordValidator(email, pasw, role);
        return true;
    }
}
