package Pages;
import javax.swing.*;
import java.awt.*;
import style.Colors;
import Function.BankFunction;

public class BankAccount {
    private final Colors color = new Colors();
    private final BankFunction accFunction = new BankFunction();
    
    public void bankAccount(JPanel panel, JPanel mainPanel) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(color.nightBlue);

        JLabel title = new JLabel("BancusMax for Banker");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(color.iceWhite);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button_delete = new JButton("Delete account");
        button_delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_delete.setMaximumSize(new Dimension(200, 30));
        button_delete.setForeground(color.iceWhite);
        button_delete.setBackground(color.electricBlue);
        button_delete.addActionListener(e -> {
            accFunction.delete();
        });

        JButton button_block = new JButton("Block account");
        button_block.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_block.setMaximumSize(new Dimension(200, 30));
        button_block.setForeground(color.iceWhite);
        button_block.setBackground(color.electricBlue);
        button_block.addActionListener(e -> {
            accFunction.block();
        });

        JButton button_view = new JButton("View account");
        button_view.setAlignmentX(Component.CENTER_ALIGNMENT);
        button_view.setMaximumSize(new Dimension(200, 30));
        button_view.setForeground(color.iceWhite);
        button_view.setBackground(color.electricBlue);
        button_view.addActionListener(e -> {
            accFunction.view();
        });

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_block);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_delete);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(button_view);

        panel.add(mainPanel, BorderLayout.CENTER);

        panel.revalidate();
        mainPanel.repaint();
    }
}
