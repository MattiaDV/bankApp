package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class Registration {
    private final DBconnection con = new DBconnection();

    public void registerUser(String email, String pasw, String role) {
        final String query = "INSERT INTO users (email, password, role) VALUES(?, ?, ?)";
        try (Connection conn = con.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            String hashedPasw = BCrypt.hashpw(pasw, BCrypt.gensalt(10));
            stmt.setString(1, email);
            stmt.setString(2, hashedPasw);
            stmt.setString(3, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al DB!");
        }
    }
}
