import javax.swing.*;
import java.awt.*;

public class UnifiedWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public void start()throws Exception {
        setTitle("Tower Defense Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
Map m = new Map();
        // Add your screens
        mainPanel.add(m.MapWindow5x5(), "menu");


        add(mainPanel);
        setVisible(true);
    }
}
