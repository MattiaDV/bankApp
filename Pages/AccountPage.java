package Pages;
import javax.swing.*;
import java.awt.*;
import style.Colors;

public class AccountPage {
    private final Colors color = new Colors();
    
    public void accountPage(JPanel panel, JPanel mainPanel) {
        panel.removeAll();
        mainPanel.removeAll();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("BancuMAX for users");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(color.iceWhite);

        panel.add(title, BorderLayout.NORTH);
        panel.add(mainPanel);

        panel.revalidate();
        mainPanel.repaint();
    }
}
