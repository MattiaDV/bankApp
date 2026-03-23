package Pages;
import javax.swing.*;

import security.RegexInputValidator;
import style.Colors;

import java.awt.*;

public class AccessPart {
    private final Colors color = new Colors();
    private final String[] accountType = {"Bank", "People"};
    private final RegexInputValidator validator = new RegexInputValidator();
    private final AccountPage userPage = new AccountPage();

    public void accessPart(JPanel panel, JPanel mainPanel) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("BancusMax", SwingConstants.CENTER);
        label.setForeground(color.iceWhite);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel accType_label = new JLabel("Tipo di account: ");
        accType_label.setFont(new Font("Arial", Font.BOLD, 14));
        accType_label.setForeground(color.iceWhite);
        accType_label.setMaximumSize(new Dimension(200, 30));
        accType_label.setMinimumSize(new Dimension(200, 30));
        accType_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> accType = new JComboBox<>(accountType);
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

        JButton access_button = new JButton("Accedi");
        access_button.setFont(new Font("Arial", Font.BOLD, 14));
        access_button.setPreferredSize(new Dimension(200, 30));
        access_button.setMinimumSize(new Dimension(200, 30));
        access_button.setMaximumSize(new Dimension(200, 30));
        access_button.setForeground(color.iceWhite);
        access_button.setBackground(color.electricBlue);
        access_button.setFocusPainted(false);
        access_button.setBorderPainted(false);
        access_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        access_button.addActionListener(e -> {
            String pasw_val = new String(password.getPassword());
            try {
                validator.checkerDatas(email.getText(), pasw_val);
                System.out.println("Dati checkkati correttamente!");
                userPage.accountPage(panel, mainPanel);
            } catch (IllegalArgumentException ex) {
                System.out.println("Errore: Input non valido");
            }
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(mainPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(accType_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(accType);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(email_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(email);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(pasw_label);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(password);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(access_button);

        panel.revalidate();
        panel.repaint();
    }
}