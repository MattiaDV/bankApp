import javax.swing.*;
import java.awt.*;

public class GUI {
    private Colors color = new Colors();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();

    public void runGUI() {
        GUI start = new GUI();
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
        access.addActionListener(e -> {
            start.AccessPart();
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(mainPanel);

        mainPanel.add(access);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void AccessPart() {
        System.out.println("Accesso...");
    }
}