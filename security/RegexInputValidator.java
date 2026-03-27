package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

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

    private boolean passwordChecker(String pasw) {
        if (pasw == null || pasw.length() < 8 || pasw.length() > 30 || pasw.isEmpty()) {
            throw new IllegalArgumentException("Errore input");
        }

        return true;
    }

    private boolean passwordValidator(String email, String pasw, String role) {
        if (pasw == null || pasw.isEmpty() || pasw.length() < 8 || pasw.length() > 30) {
            throw new IllegalArgumentException("Errore input");
        }

        if ("People".equals(role)) {
            role = "user";
        } else if ("Bank".equals(role)) {
            role = "bank";
        } else {
            throw new IllegalArgumentException("Input non valido");
        }

        final String query = "SELECT password FROM users WHERE email = ? AND role = ?";

        try (Connection conn = db_check.getConn();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, role);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPasw = rs.getString("password");
                    if (BCrypt.checkpw(pasw, hashedPasw)) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new IllegalArgumentException("Errore DB!");
        }

        throw new IllegalArgumentException("Credenziali non valide");
    }

    private boolean checkerRoleForUser(String role) {
        if (role.isEmpty() || role == null) {
            throw new IllegalArgumentException("input non valido!");
        }

        return true;
    }

    public boolean checkerDatas(String email, String pasw, String role) {
        emailValidator(email);
        passwordValidator(email, pasw, role);
        return true;
    }

    public boolean checkerDatasRegistration(String email, String pasw, String role) {
        emailValidator(email);
        passwordChecker(pasw);
        checkerRoleForUser(role);
        return true;
    }
}
