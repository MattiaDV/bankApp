import javax.swing.*;
import java.awt.*;

public class GUI {
    public void runGUI() {
        Colors color = new Colors();
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        panel.setBackground(color.nightBlue);
        panel.setLayout(new BorderLayout());

        frame.setTitle("BancusMax");
        frame.setSize(500, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("BANCUSMAX", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(color.iceWhite);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(color.nightBlue);

        JButton access = new JButton("Accedi");
        access.setFont(new Font("Arial", Font.BOLD, 20));
        access.setBackground(color.electricBlue);
        access.setForeground(color.iceWhite);
        access.setPreferredSize(new Dimension(200, 30));
        access.setFocusPainted(false);
        access.setBorderPainted(false);

        panel.add(title, BorderLayout.NORTH);
        panel.add(mainPanel);

        mainPanel.add(access);

        frame.add(panel);
        frame.setVisible(true);
    }
}