import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Player class handles player-specific interactions during gameplay,
 * specifically managing tower placement and removal through an inventory GUI.
 */
public class Player {

    /** Array wrapper to track removed towers (used in removal logic). */
    int[] addedTowers = {0};



    /**
     * Opens the inventory GUI to either place or remove towers depending on the flag.
     *
     * If placing:
     * - Displays a list of inactive towers with their durability.
     * - Allows the player to select a tower and place it on a valid map tile.
     * - If no towers are available, shows a warning dialog.
     *
     * If removing:
     * - Activates removal mode, allowing the player to click a tower and remove it from the grid.
     *
     * @param labels the grid of map tiles represented as JLabels
     * @param wannaRemove true if the player wants to remove a tower, false to place one
     * @param TowerIndexes list of x and y positions of placed towers
     * @param towers list of all Tower objects in the current wave
     * @param knightPath path that enemies follow, used to prevent placing towers on it
     * @param towerIsBeningPlaced atomic flag to indicate if a tower is being placed
     * @param lineLength size of one side of the map grid (e.g., 5, 7, 10)
     * @param atleastOneTowerIsPLaced flag to indicate if at least one tower exists on the map
     * @return true if the inventory action was completed successfully
     * @throws Exception if an error occurs during tower placement or GUI setup
     */
    public boolean OpenInventory(JLabel[][] labels,
                                 boolean wannaRemove,
                                 ArrayList<Integer> TowerIndexes,
                                 ArrayList<Tower> towers,
                                 ArrayList<Point> knightPath,
                                 AtomicBoolean towerIsBeningPlaced,
                                 int lineLength,
                                 AtomicBoolean atleastOneTowerIsPLaced) throws Exception {

        boolean successful = false;
        int[] inActiveTowers = {0};

        for (int i = 0; i < towers.size(); i++) {
            if (!towers.get(i).isActive()) {
                inActiveTowers[0]++;
            }
        }

        if (!wannaRemove) {
            ArrayList<JButton> buttons = new ArrayList<>();
            JFrame frame = new JFrame();
            JPanel jp = new JPanel();
            jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
            jp.setBackground(new Color(194, 155, 99));
            jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            Label label = new Label("Choose a tower to play:");
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            label.setAlignment(Label.CENTER);
            label.setForeground(Color.DARK_GRAY);
            jp.add(label);
            jp.add(Box.createVerticalStrut(10));

            int availableTowers = 0;

            for (Tower currentTower : towers) {
                if (!currentTower.isActive() && currentTower.getDurability() > 0) {
                    availableTowers++;

                    JButton jb = new JButton("Tower " + currentTower.getDurability() + "/2 durability");
                    jb.setFocusPainted(false);
                    jb.setPreferredSize(new Dimension(200, 40));
                    jb.setMaximumSize(new Dimension(220, 40));
                    jb.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jb.setBackground(new Color(150, 150, 160));
                    jb.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                    jb.setFont(new Font("Impact", Font.PLAIN, 16));
                    jb.setForeground(Color.BLACK);

                    Image scaled = currentTower.getTowerIcon().getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    JLabel jl = new JLabel(new ImageIcon(scaled));
                    jl.setAlignmentX(Component.CENTER_ALIGNMENT);

                    if (currentTower.getDurability() <= 0) {
                        jb.setEnabled(false);
                    } else {
                        jb.addActionListener(e -> {
                            try {
                                frame.dispose();
                                Tower.placeTower(labels, inActiveTowers, lineLength, lineLength,
                                        TowerIndexes, towers, knightPath, towerIsBeningPlaced, atleastOneTowerIsPLaced);
                                currentTower.setActive(true);
                                if (currentTower.getDurability() <= 0) {
                                    currentTower.setActive(true);
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }

                    jp.add(jb);
                    jp.add(Box.createVerticalStrut(5));
                    jp.add(jl);
                    jp.add(Box.createVerticalStrut(10));
                    buttons.add(jb);
                }
            }

            if (availableTowers == 0) {
                JFrame f = new JFrame("⚠ Warning");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setSize(300, 130);

                JPanel warningPanel = new JPanel();
                warningPanel.setLayout(new BoxLayout(warningPanel, BoxLayout.Y_AXIS));
                warningPanel.setBackground(new Color(255, 220, 220));
                warningPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

                JLabel message = new JLabel("You're out of towers!", SwingConstants.CENTER);
                message.setFont(new Font("SansSerif", Font.BOLD, 16));
                message.setForeground(new Color(180, 0, 0));
                message.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton okButton = new JButton("OK");
                okButton.setFont(new Font("SansSerif", Font.BOLD, 14));
                okButton.setFocusPainted(false);
                okButton.setBackground(new Color(220, 0, 0));
                okButton.setForeground(Color.WHITE);
                okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                okButton.setPreferredSize(new Dimension(80, 30));
                okButton.setMaximumSize(new Dimension(100, 30));
                okButton.addActionListener(e1 -> f.dispose());

                warningPanel.add(Box.createVerticalStrut(15));
                warningPanel.add(message);
                warningPanel.add(Box.createVerticalStrut(10));
                warningPanel.add(okButton);
                warningPanel.add(Box.createVerticalStrut(15));

                f.setContentPane(warningPanel);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            } else {
                JButton closeButton = new JButton("Close");
                closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                closeButton.setFont(new Font("Impact", Font.PLAIN, 16));
                closeButton.setFocusPainted(false);
                closeButton.setBackground(new Color(150, 150, 160));
                closeButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                closeButton.setPreferredSize(new Dimension(150, 40));
                closeButton.setMaximumSize(new Dimension(160, 40));

                closeButton.addActionListener(e -> frame.dispose());
                jp.add(Box.createVerticalStrut(10));
                jp.add(closeButton);

                jp.setVisible(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = screenSize.width;
                int height = screenSize.height;
                frame.setUndecorated(true);
                frame.setSize(new Dimension(350, 400));
                frame.setTitle("Inventory");
                frame.add(jp);
                frame.setLayout(new FlowLayout());
                frame.pack();
                frame.setLocation((width / 3) - 300, height / 2 - 30);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        } else {
            for (Tower t : towers) {
                t.removeTower(towers, labels, lineLength, lineLength, addedTowers,
                        TowerIndexes, towerIsBeningPlaced, atleastOneTowerIsPLaced);
            }
        }

        successful = true;
        return successful;
    }
}
