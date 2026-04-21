package Pages;
import javax.swing.*;

import DataBase.DBconnection;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import style.Colors;
import Function.AccountFunction;
import security.RegexInputValidator;

public class AccountPage {
    private final Colors color = new Colors();
    private final AccountFunction accFunction = new AccountFunction();
    private final RegexInputValidator riv = new RegexInputValidator();
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
    
    public void accountPage(JPanel panel, JPanel mainPanel, String email) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(color.nightBlue);

        JLabel title = new JLabel("BancusMax for users");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(color.iceWhite);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cashTotal = new JLabel("Cash: " + getCash(email).toString());
        cashTotal.setFont(new Font("Arial", Font.BOLD, 24));
        cashTotal.setForeground(color.iceWhite);
        cashTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField withdraw_number = new JTextField();
        withdraw_number.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdraw_number.setMaximumSize(new Dimension(200, 30));
        withdraw_number.setForeground(color.iceWhite);
        withdraw_number.setBackground(color.electricBlue);

        JButton button_withdraw = new JButton("Withdraw");
        button_withdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_withdraw.setMaximumSize(new Dimension(200, 30));
        button_withdraw.setForeground(color.iceWhite);
        button_withdraw.setBackground(color.electricBlue);
        button_withdraw.addActionListener(e -> {
            if (!withdraw_number.getText().isEmpty()) {
                try {
                    String data_wit = withdraw_number.getText();
                    if (data_wit == null || data_wit.isEmpty()) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (getCash(email).compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (data_wit.matches("\\d+(\\.\\d{1,2})?")) {
                        accFunction.withDraw(new BigDecimal(data_wit), email);
                        cashTotal.setText("Cash: " + getCash(email).toString());
                    } else {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }
                } catch (IllegalArgumentException err) {
                    System.out.println(err.getMessage());
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
            withdraw_number.setText("");
        });

        JTextField pay_number = new JTextField();
        pay_number.setAlignmentX(Component.CENTER_ALIGNMENT);
        pay_number.setMaximumSize(new Dimension(200, 30));
        pay_number.setForeground(color.iceWhite);
        pay_number.setBackground(color.electricBlue);

        JButton button_pay = new JButton("Pay");
        button_pay.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_pay.setMaximumSize(new Dimension(200, 30));
        button_pay.setForeground(color.iceWhite);
        button_pay.setBackground(color.electricBlue);
        button_pay.addActionListener(e -> {
            if (!pay_number.getText().isEmpty()) {
                try {
                    String data_pay = pay_number.getText();
                    if (data_pay == null || data_pay.isEmpty()) {
                        throw new IllegalArgumentException("Errore nell'input!");
                        JOptionPane message_no_data = new JOptionPane.showMessageDialog(null, "Non puoi lasciare il campo vuoto!");
                    }

                    if (getCash(email).compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (data_pay.matches("\\d+(\\.\\d{1,2})?")) {
                        accFunction.payOperation(new BigDecimal(data_pay), email);
                        cashTotal.setText("Cash: " + getCash(email).toString());
                    } else {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }
                } catch (IllegalArgumentException err) {
                    System.out.println(err.getMessage());
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
            pay_number.setText("");
        });

        JTextField trasnsfer_email = new JTextField();
        trasnsfer_email.setAlignmentX(Component.CENTER_ALIGNMENT);
        trasnsfer_email.setMaximumSize(new Dimension(200, 30));
        trasnsfer_email.setForeground(color.iceWhite);
        trasnsfer_email.setBackground(color.electricBlue);

        JTextField transfer_number = new JTextField();
        transfer_number.setAlignmentX(Component.CENTER_ALIGNMENT);
        transfer_number.setMaximumSize(new Dimension(200, 30));
        transfer_number.setForeground(color.iceWhite);
        transfer_number.setBackground(color.electricBlue);

        JButton button_trasnfer = new JButton("Transfer");
        button_trasnfer.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_trasnfer.setMaximumSize(new Dimension(200, 30));
        button_trasnfer.setForeground(color.iceWhite);
        button_trasnfer.setBackground(color.electricBlue);
        button_trasnfer.addActionListener(e -> {
            if (!transfer_number.getText().isEmpty() && !trasnsfer_email.getText().isEmpty()) {
                try {
                    String data_tra = transfer_number.getText();
                    String email_tra = trasnsfer_email.getText();
                    if (data_tra == null || data_tra.isEmpty()) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (getCash(email).compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (!riv.emailValidator(email_tra)) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (data_tra.matches("\\d+(\\.\\d{1,2})?") && riv.emailExist(email_tra)) {
                        accFunction.transferCash(new BigDecimal(data_tra), email, email_tra);
                        cashTotal.setText("Cash: " + getCash(email).toString());
                    } else {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }
                } catch (IllegalArgumentException err) {
                    System.out.println(err.getMessage());
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
            transfer_number.setText("");
            trasnsfer_email.setText("");
        });

        JTextField recharge_number = new JTextField();
        recharge_number.setAlignmentX(Component.CENTER_ALIGNMENT);
        recharge_number.setMaximumSize(new Dimension(200, 30));
        recharge_number.setForeground(color.iceWhite);
        recharge_number.setBackground(color.electricBlue);

        JButton button_recharge = new JButton("Recharge");
        button_recharge.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_recharge.setMaximumSize(new Dimension(200, 30));
        button_recharge.setForeground(color.iceWhite);
        button_recharge.setBackground(color.electricBlue);
        button_recharge.addActionListener(e -> {
            if (!recharge_number.getText().isEmpty()) {
                try {
                    String data_rec = recharge_number.getText();
                    if (data_rec == null || data_rec.isEmpty()) {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }

                    if (data_rec.matches("\\d+(\\.\\d{1,2})?")) {
                        accFunction.recharge(new BigDecimal(data_rec), email);
                        cashTotal.setText("Cash: " + getCash(email).toString());
                    } else {
                        throw new IllegalArgumentException("Errore nell'input!");
                    }
                } catch (IllegalArgumentException err) {
                    System.out.println(err.getMessage());
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            } else {
                System.out.println("Non puoi lasciare vuoto il campo");
            }
            recharge_number.setText("");
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
        mainPanel.add(recharge_number);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_recharge);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(withdraw_number);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_withdraw);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(trasnsfer_email);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(transfer_number);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_trasnfer);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(pay_number);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_pay);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(logout_button);

        panel.add(mainPanel, BorderLayout.CENTER);

        panel.revalidate();
        mainPanel.repaint();
    }
}
