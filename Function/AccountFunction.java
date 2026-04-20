package Function;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataBase.DBconnection;

public class AccountFunction {
    private final DBconnection db = new DBconnection();
    public void withDraw(BigDecimal valore, String email) {
        String query = "UPDATE users SET cash = cash - ? WHERE email = ?";

        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, valore);
            stmt.setString(2, email);

            int ris = stmt.executeUpdate();

            if (ris > 0) {
                System.out.println("Prelievo avvenuto con successo!");
            } else {
                System.out.println("Prelievo non riuscito!");
            }
        } catch (SQLException e) {
            System.out.println("Errore nel prelievo!");
        }
    }

    public void transferCash(BigDecimal valore, String mittente, String destinatario) {
        payOperation(valore, mittente);
        String query = "UPDATE users SET cash = cash + ? WHERE email = ?";

        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, valore);
            stmt.setString(2, destinatario);

            int ris = stmt.executeUpdate();

            if (ris > 0) {
                System.out.println("Trasferimento avvenuto con successo!");
            } else {
                System.out.println("Trasferimento non riuscito!");
            }
        } catch(SQLException e) {
            System.out.println("Trasferimento non riuscito!");
        }
    }

    public void payOperation(BigDecimal valore, String email) {
        String query = "UPDATE users SET cash = cash - ? WHERE email = ?";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, valore);
            stmt.setString(2, email);

            int ris = stmt.executeUpdate();

            if (ris > 0) {
                System.out.println("Pagamento avvenuto con successo!");
            } else {
                System.out.println("Pagamento non riuscito!");
            }
        } catch (SQLException e) {
            System.out.println("Pagamento non riuscito!");
        }
    }

    public void recharge(BigDecimal valore, String email) {
        String query = "UPDATE users SET cash = cash + ? WHERE email = ?";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, valore);
            stmt.setString(2, email);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Ricarica avvenuta con successo!");
            } else {
                System.out.println("Ricarica fallita");
            }
        } catch (SQLException e) {
            System.out.println("Errore nella ricarica");
        }
    }
}