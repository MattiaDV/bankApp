package Pages;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import style.Colors;
import Function.BankFunction;
import security.RegexInputValidator;
import DataBase.DBconnection;
import DataBase.Registration;

public class BankAccount {
    private final String[] acc_tye = {"user", "bank"};
    private final Colors color = new Colors();
    private final BankFunction accFunction = new BankFunction();
    private final RegexInputValidator checker = new RegexInputValidator();
    private final Registration reg = new Registration();
    private final DBconnection db = new DBconnection();

    private BigDecimal getCash(String email) {
        String query = "SELECT cash FROM users WHERE email = ?";
        try (Connection conn = db.getConn(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet ris = stmt.executeQuery();

            if (ris.next()) {
                return ris.getBigDecimal("cash");
            }
        } catch (SQLException er) {
            System.out.println("Errore nel DB: " + er.getMessage());
        }

        return BigDecimal.ZERO;
    }
    
    public void bankAccount(JPanel panel, JPanel mainPanel, String email_user) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(color.nightBlue);

        JLabel title = new JLabel("BancusMax for Banker");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(color.iceWhite);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cashTotal = new JLabel("Cash: " + getCash(email_user).toString());
        cashTotal.setFont(new Font("Arial", Font.BOLD, 24));
        cashTotal.setForeground(color.iceWhite);
        cashTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel add_label = new JLabel("Add new user");
        add_label.setFont(new Font("Arial", Font.BOLD, 14));
        add_label.setForeground(color.iceWhite);
        add_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> accType = new JComboBox<>(acc_tye);
        accType.setPreferredSize(new Dimension(200, 30));
        accType.setMaximumSize(new Dimension(200, 30));
        accType.setMinimumSize(new Dimension(200, 30));
        accType.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel email_label = new JLabel("Email: ");
        email_label.setFont(new Font("Arial", Font.BOLD, 14));
        email_label.setForeground(color.iceWhite);
        email_label.setMaximumSize(new Dimension(200, 30));
        email_label.setMinimumSize(new Dimension(200, 30));
        email_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField email = new JTextField();
        email.setPreferredSize(new Dimension(200, 30));
        email.setMaximumSize(new Dimension(200, 30));
        email.setMinimumSize(new Dimension(200, 30));
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pasw_label = new JLabel("Password: ");
        pasw_label.setFont(new Font("Arial", Font.BOLD, 14));
        pasw_label.setForeground(color.iceWhite);
        pasw_label.setMaximumSize(new Dimension(200, 30));
        pasw_label.setMinimumSize(new Dimension(200, 30));
        pasw_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));
        password.setMaximumSize(new Dimension(200, 30));
        password.setMinimumSize(new Dimension(200, 30));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton create_button = new JButton("Create user");
        create_button.setForeground(color.iceWhite);
        create_button.setBackground(color.electricBlue);
        create_button.setMaximumSize(new Dimension(200, 30));
        create_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        create_button.addActionListener(e -> {
            String pasw_val = new String(password.getPassword());
            try {
                boolean val = checker.checkerDatasRegistration(email.getText(), pasw_val, accType.getSelectedItem().toString());
                boolean existingUser = false;

                try {
                    existingUser = accFunction.userAlreadyExist(email.getText());
                } catch(RuntimeException err) {
                    System.out.println("Errore!");
                    throw new IllegalArgumentException("Input non valido");
                }

                if (val && !existingUser) {
                    try {
                        reg.registerUser(email.getText(), pasw_val, accType.getSelectedItem().toString());
                        System.out.println("Utente registrato!");
                    } catch (Exception err) {
                        System.out.println("Impossibile registrare");
                    }
                }
            } catch (IllegalArgumentException er) {
                System.out.println("Input non valido");
            }
            email.setText("");
            password.setText("");
        });

        JTextField email_to_delete = new JTextField();
        email_to_delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        email_to_delete.setMaximumSize(new Dimension(200, 30));
        email_to_delete.setForeground(color.iceWhite);
        email_to_delete.setBackground(color.electricBlue);

        JButton button_delete = new JButton("Delete account");
        button_delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_delete.setMaximumSize(new Dimension(200, 30));
        button_delete.setForeground(color.iceWhite);
        button_delete.setBackground(color.electricBlue);
        button_delete.addActionListener(e -> {
            if (!email_to_delete.getText().isEmpty()) {
                String email_del = email_to_delete.getText();
                accFunction.delete(email_del);
                email_to_delete.setText("");
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
        });

        JTextField email_to_block = new JTextField();
        email_to_block.setAlignmentX(Component.CENTER_ALIGNMENT);
        email_to_block.setMaximumSize(new Dimension(200, 30));
        email_to_block.setForeground(color.iceWhite);
        email_to_block.setBackground(color.electricBlue);

        JButton button_block = new JButton("Block account");
        button_block.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_block.setMaximumSize(new Dimension(200, 30));
        button_block.setForeground(color.iceWhite);
        button_block.setBackground(color.electricBlue);
        button_block.addActionListener(e -> {
            if (!email_to_block.getText().isEmpty()) {
                String email_blo = email_to_block.getText();
                accFunction.block(email_blo);
                email_to_block.setText("");
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
        });

        JTextField email_to_unlock = new JTextField();
        email_to_unlock.setAlignmentX(Component.CENTER_ALIGNMENT);
        email_to_unlock.setMaximumSize(new Dimension(200, 30));
        email_to_unlock.setForeground(color.iceWhite);
        email_to_unlock.setBackground(color.electricBlue);

        JButton button_view = new JButton("Unlock account");
        button_view.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_view.setMaximumSize(new Dimension(200, 30));
        button_view.setForeground(color.iceWhite);
        button_view.setBackground(color.electricBlue);
        button_view.addActionListener(e -> {
            if (!email_to_unlock.getText().isEmpty()) {
                String email_unl = email_to_unlock.getText();
                accFunction.unblock(email_unl);
                email_to_unlock.setText("");
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
        });

        JButton logout_button = new JButton("Log out");
        logout_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout_button.setMaximumSize(new Dimension(200, 30));
        logout_button.setForeground(color.iceWhite);
        logout_button.setBackground(color.red);
        logout_button.addActionListener(e -> {
            new AccessPart().accessPart(panel, mainPanel);
        });

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(cashTotal);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(add_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(accType);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(email_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(email);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(pasw_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(password);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(create_button);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(email_to_block);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(button_block);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(email_to_delete);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(button_delete);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(email_to_unlock);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(button_view);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(logout_button);

        panel.add(mainPanel, BorderLayout.CENTER);

        panel.revalidate();
        mainPanel.repaint();
    }
}
