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

        JButton bottone_test = new JButton("Test");
        bottone_test.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottone_test.setMaximumSize(new Dimension(200, 30));
        bottone_test.setForeground(color.iceWhite);
        bottone_test.setBackground(color.electricBlue);
        bottone_test.addActionListener(e -> {
            accFunction.transferCash(10);
            accFunction.withDraw(10);
            accFunction.payOperation(10);
            accFunction.viewHistory();
        });

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(bottone_test);

        panel.add(mainPanel, BorderLayout.CENTER);

        panel.revalidate();
        mainPanel.repaint();
    }
}
