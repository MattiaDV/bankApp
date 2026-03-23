package Pages;
import javax.swing.*;

import style.Colors;

import java.awt.*;

public class GUI {
    private Colors color = new Colors();
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private AccessPart access_part_new = new AccessPart();

    public void runGUI() {
        panel.setBackground(color.nightBlue);
        panel.setLayout(new BorderLayout());

        frame.setTitle("BancusMax");
        frame.setSize(500, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("BANCUSMAX", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(color.iceWhite);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        mainPanel.setBackground(color.nightBlue);

        JButton access = new JButton("Accedi");
        access.setFont(new Font("Arial", Font.BOLD, 20));
        access.setBackground(color.electricBlue);
        access.setForeground(color.iceWhite);
        access.setPreferredSize(new Dimension(200, 30));
        access.setFocusPainted(false);
        access.setBorderPainted(false);
        access.addActionListener(e -> {
            access_part_new.accessPart(panel, mainPanel);
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(mainPanel);

        mainPanel.add(access);

        frame.add(panel);
        frame.setVisible(true);
    }
}