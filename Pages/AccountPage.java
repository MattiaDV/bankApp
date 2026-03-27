package Pages;
import javax.swing.*;
import java.awt.*;
import style.Colors;
import Function.AccountFunction;

public class AccountPage {
    private final Colors color = new Colors();
    private final AccountFunction accFunction = new AccountFunction();
    
    public void accountPage(JPanel panel, JPanel mainPanel) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(color.nightBlue);

        JLabel title = new JLabel("BancusMax for users");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(color.iceWhite);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button_withdraw = new JButton("Withdraw");
        button_withdraw.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_withdraw.setMaximumSize(new Dimension(200, 30));
        button_withdraw.setForeground(color.iceWhite);
        button_withdraw.setBackground(color.electricBlue);
        button_withdraw.addActionListener(e -> {
            accFunction.withDraw(10);
        });

        JButton button_pay = new JButton("Pay");
        button_pay.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_pay.setMaximumSize(new Dimension(200, 30));
        button_pay.setForeground(color.iceWhite);
        button_pay.setBackground(color.electricBlue);
        button_pay.addActionListener(e -> {
            accFunction.payOperation(0);
        });

        JButton button_trasnfer = new JButton("Transfer");
        button_trasnfer.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_trasnfer.setMaximumSize(new Dimension(200, 30));
        button_trasnfer.setForeground(color.iceWhite);
        button_trasnfer.setBackground(color.electricBlue);
        button_trasnfer.addActionListener(e -> {
            accFunction.transferCash(0);
        });

        JButton button_history = new JButton("Withdraw");
        button_history.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_history.setMaximumSize(new Dimension(200, 30));
        button_history.setForeground(color.iceWhite);
        button_history.setBackground(color.electricBlue);
        button_history.addActionListener(e -> {
            accFunction.viewHistory();
        });

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_withdraw);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_trasnfer);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_pay);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_history);

        panel.add(mainPanel, BorderLayout.CENTER);

        panel.revalidate();
        mainPanel.repaint();
    }
}
