import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    int addedTowersUsed =0;
    int[] addedTowers={0};
    private int towersCount;


    public boolean OpenInventory(JLabel[][] labels, boolean wannaRemove, ArrayList<Integer> TowerIndexes, ArrayList<Tower> towers) throws Exception {
        boolean successful = false;
int[] inActiveTowers={0};

for(int i =0;i<towers.size();i++){

    if(towers.get(i).isActive()==false){
        inActiveTowers[0]++;
    }
}
        if (!wannaRemove) {
            ArrayList<JButton> buttons = new ArrayList<>();
            JFrame frame = new JFrame();
            JPanel jp = new JPanel();
            jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

            Label label = new Label("Choose a tower to play:");
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            jp.add(label);

            int availableTowers = 0;





            for (Tower currentTower : towers) {
                if (!currentTower.isActive() && currentTower.getDurability() > 0) {
                    availableTowers++;

                    JButton jb = new JButton("Tower " + currentTower.getDurability() + "/2 durability");
                    JLabel jl = new JLabel(currentTower.getTowerIcon());

                    if (currentTower.getDurability() <= 0) {
                        jb.setEnabled(false); // Disable button if durability is 0
                    } else {
                        jb.addActionListener(e -> {
                            try {
                                frame.dispose();
                                Tower.placeTower(labels,inActiveTowers ,5, 5, TowerIndexes, towers);
                                currentTower.setActive(true);
                                currentTower.setDurability(currentTower.getDurability() - 1);
                                if (currentTower.getDurability() <= 0) {
                                    currentTower.setActive(true); // prevent reuse
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }

                    jp.add(jb);
                    jp.add(jl);
                    buttons.add(jb);
                }
            }

            if (availableTowers == 0) {
                JFrame f = new JFrame("Warning");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(300, 120);
                f.setLayout(new BorderLayout());

                JLabel message = new JLabel("You're out of towers!", SwingConstants.CENTER);
                JButton okButton = new JButton("OK");
                okButton.addActionListener(e1 -> f.dispose());

                f.add(message, BorderLayout.CENTER);
                f.add(okButton, BorderLayout.SOUTH);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            } else {
                JButton closeButton = new JButton("Close");
                closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                closeButton.addActionListener(e -> frame.dispose());
                jp.add(Box.createVerticalStrut(10));
                jp.add(closeButton);

                jp.setVisible(true);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = screenSize.width;
                int height = screenSize.height;

                frame.setSize(new Dimension(300, 100));
                frame.setTitle("Inventory");
                frame.add(jp);
                frame.setLayout(new FlowLayout());
                frame.pack();
                frame.setLocation(width / 3, height / 2);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        } else {
            for (Tower t : towers) {
                t.removeTower(towers, labels, 5, 5, addedTowers, TowerIndexes);
            }
        }

        successful = true;
        return successful;
    }










}
