package Function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBconnection;

public class BankFunction {
    private final DBconnection db = new DBconnection();

    private boolean alreadyBlocked(String email) {
        String query = "SELECT 1 FROM users WHERE email = ? AND active = true";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet ris = stmt.executeQuery();
            return ris.next();
        } catch (SQLException e) {
            throw new RuntimeException("[Errore] errore col db");
        }
    }

    public void unblock(String email) {
        String query = "UPDATE users SET active = false WHERE email = ?";
        boolean alreadyBlock = alreadyBlocked(email);

        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            if (alreadyBlock) {
                stmt.setString(1, email);
                int ris = stmt.executeUpdate();
                if (ris > 0) {
                    System.out.println("[Successo] Utente sbloccato con successo: " + email);
                } else {
                    System.out.println("[Errore] Errore con l'utente: " + email);
                }
            } else {
                System.out.println("[Errore] Errore con l'utente (già sbloccato): " + email);
            }
        } catch(SQLException err) {
            System.out.println("[Errore] Errore con il DB");
        }
    }

    public void block(String email) {
        String query = "UPDATE users SET active = true WHERE email = ?";
        boolean alreadyBlock = alreadyBlocked(email);

        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!alreadyBlock) {
                stmt.setString(1, email);
                int ris = stmt.executeUpdate();
                if (ris > 0) {
                    System.out.println("[Successo] account bloccato: " + email);
                } else {
                    System.out.println("[Errore] Account non bloccato: " + email);
                }
            } else {
                System.out.println("[Errore] Account già bloccato!: " + email);
            }
        } catch (SQLException e) {
            System.out.println("[Errore] Errore col DB");
        }
    }

    public void delete(String email) {
        String query = "DELETE FROM users WHERE email = ?";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            int ris = stmt.executeUpdate();
            if (ris > 0) {
                System.out.println("[Account eliminato correttamente] - " + email);
            } else {
                System.out.println("[Errore] errore nell'eliminazione dell'account: " + email);
            }
        } catch (SQLException e) {
            System.out.println("[Errore] Errore con la connessione al DB");
        }
    }

    public boolean userAlreadyExist(String email) {
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet ris = stmt.executeQuery();
            if (ris.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel db");
        }
    }
}