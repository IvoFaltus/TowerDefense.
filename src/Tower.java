import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tower {
    private boolean isActive = false;


    private int x = -10;
    private int y = -10;


    private static int idCounter = 0;
    private final int id;

    public Tower() {
        this.id = idCounter++;
        // ... other init code
    }

    public int getId() {
        return id;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int[] getPosition() {


        return new int[]{this.x, this.y};
    }


    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isActive() {
        return isActive;
    }

    private boolean wait = true;

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLvl() {
        return lvl;
    }

    private int durability; // default

    public void reduceDurability() {
        durability--;
        if (durability <= 0) {
            this.setActive(false); // remove tower from map logic
        }
    }



    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    static Wave w = new Wave();



    private ImageIcon towerIcon;
    private int lvl;

    private static int towersPlaced = 0;

    public static void placeTower(JLabel[][] labels, int[] inActiveTowers, int rows, int cols,
                                  ArrayList<Integer> towerIndexes, ArrayList<Tower> towers,
                                  ArrayList<Point> knightPath, AtomicBoolean towerIsBeingPlaced, AtomicBoolean atleastOneTowerIsPlaced) throws Exception {

        towerIsBeingPlaced.set(true);
        AtomicBoolean hasClicked = new AtomicBoolean(false);

        JWindow placePopup = new JWindow();
        JLabel placeLabel = new JLabel("✔ Place Tower", SwingConstants.CENTER);
        placeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        placeLabel.setForeground(Color.WHITE);

        JPanel placePanel = new JPanel();
        placePanel.setBackground(new Color(46, 204, 113));
        placePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 130, 76), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        placePanel.add(placeLabel);

        placePopup.add(placePanel);
        placePopup.pack();
        placePopup.setLocation(50, 50);
        placePopup.setAlwaysOnTop(true);
        placePopup.setVisible(true);

        Timer autoHide = new Timer(10000, e -> {
            if (!hasClicked.get()) {
                placePopup.setVisible(false);
                placePopup.dispose();
                towerIsBeingPlaced.set(false);
            }
        });
        autoHide.setRepeats(false);
        autoHide.start();

        if (inActiveTowers[0] != 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int finalI = i;
                    int finalJ = j;
                    JLabel tile = labels[finalI][finalJ];

                    if (tile != null) {
                        tile.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (hasClicked.get()) return;

                                boolean isOnPath = knightPath.stream()
                                        .anyMatch(p -> p.x == finalI && p.y == finalJ);

                                if (tile.getIcon() == null && !isOnPath) {
                                    Tower t = new Tower();
                                    t.setTowerIcon();
                                    tile.setIcon(t.getTowerIcon());

                                    t.setActive(true);
                                    t.setPosition(finalI, finalJ);
                                    hasClicked.set(true);

                                    for (Tower tower : towers) {
                                        if (tower.getPosition() == null) {
                                            tower.setPosition(finalI, finalJ);
                                        }
                                    }

                                    towerIndexes.add(finalI);
                                    towerIndexes.add(finalJ);

                                    placePopup.setVisible(false);
                                    placePopup.dispose();

                                    autoHide.stop();

                                    towerIsBeingPlaced.set(false);

                                    towersPlaced++; // Increment the towers placed count
                                    if (towersPlaced == 0) {
                                        atleastOneTowerIsPlaced.set(false);
                                    } else {
                                        atleastOneTowerIsPlaced.set(true);
                                    }
                                } else {
                                    JWindow popup = new JWindow();
                                    JLabel label = new JLabel("✖ You cannot place tower here", SwingConstants.CENTER);
                                    label.setFont(new Font("Arial", Font.BOLD, 16));
                                    label.setForeground(Color.WHITE);

                                    JPanel panel = new JPanel();
                                    panel.setBackground(new Color(231, 76, 60));
                                    panel.setBorder(BorderFactory.createCompoundBorder(
                                            BorderFactory.createLineBorder(new Color(192, 57, 43), 2),
                                            BorderFactory.createEmptyBorder(10, 20, 10, 20)
                                    ));
                                    panel.add(label);

                                    popup.add(panel);
                                    popup.pack();
                                    popup.setLocationRelativeTo(null);
                                    popup.setAlwaysOnTop(true);
                                    popup.setVisible(true);

                                    new Timer(1000, ev -> {
                                        popup.setVisible(false);
                                        popup.dispose();
                                    }).start();
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public void removeTower(ArrayList<Tower> towers, JLabel[][] labels, int rows, int cols,
                            int[] addedTowers, ArrayList<Integer> towerIndexes,
                            AtomicBoolean towerIsBeingPlaced, AtomicBoolean atleastOneTowerIsPlaced) {

        towerIsBeingPlaced.set(true);
        AtomicBoolean hasClicked = new AtomicBoolean(false);

        JWindow removePopup = new JWindow();
        JLabel removeLabel = new JLabel("🗑 Remove Tower", SwingConstants.CENTER);
        removeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        removeLabel.setForeground(Color.WHITE);

        JPanel removePanel = new JPanel();
        removePanel.setBackground(new Color(255, 140, 0));
        removePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 90, 0), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        removePanel.add(removeLabel);

        removePopup.add(removePanel);
        removePopup.pack();
        removePopup.setLocation(50, 50);
        removePopup.setAlwaysOnTop(true);
        removePopup.setVisible(true);

        // monitor if tower is placed and close popup if so
        Timer monitor = new Timer(100, e -> {
            if (!towerIsBeingPlaced.get()) {
                removePopup.setVisible(false);
                removePopup.dispose();
                ((Timer) e.getSource()).stop();
            }
        });
        monitor.start();

        // auto-close after 10 sec if user does nothing
        Timer autoClosePopup = new Timer(10000, e -> {
            if (!hasClicked.get()) {
                removePopup.setVisible(false);
                removePopup.dispose();
                towerIsBeingPlaced.set(false);
                ((Timer) e.getSource()).stop();
            }
        });
        autoClosePopup.setRepeats(false);
        autoClosePopup.start();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalI = i;
                int finalJ = j;

                JLabel tile = labels[finalI][finalJ];

                if (tile != null) {
                    for (MouseListener ml : tile.getMouseListeners()) {
                        tile.removeMouseListener(ml);
                    }

                    tile.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!hasClicked.get() && tile.getIcon() != null) {
                                tile.setIcon(null);
                                hasClicked.set(true);

                                for (Tower t : towers) {
                                    if (t.getX() == finalI && t.getY() == finalJ && t.isActive()) {
                                        t.setActive(false);
                                        t.setPosition(-1, -1);
                                        addedTowers[0]++;
                                        break;
                                    }
                                }

                                for (int k = 0; k < towerIndexes.size() - 1; k += 2) {
                                    int x = towerIndexes.get(k);
                                    int y = towerIndexes.get(k + 1);
                                    if (x == finalI && y == finalJ) {
                                        towerIndexes.remove(k + 1);
                                        towerIndexes.remove(k);
                                        break;
                                    }
                                }

                                towersPlaced--; // Decrement the towers placed count
                                if (towersPlaced == 0) {
                                    atleastOneTowerIsPlaced.set(false);
                                } else {
                                    atleastOneTowerIsPlaced.set(true);
                                }

                                towerIsBeingPlaced.set(false);
                            }
                        }
                    });
                }
            }
        }
    }







    public ImageIcon setTowerIcon() {
        int lvl = 1;
        //incomplete
        URL url = null;
        switch (this.durability) {
            case 1:
                url = getClass().getResource("/resources/towerDmg.png");
                break;
            case 2:
                url = getClass().getResource("/resources/towerDmg.png");
                break;
            case 3:
                url = getClass().getResource("/resources/towerDmg.png");
                break;
            case 4:
                url = getClass().getResource("/resources/tower.png");
                break;
            case 0:
                url = getClass().getResource("/resources/tower.png");
                break;
            default:
                url = getClass().getResource("/resources/tower.png");
                break;


        }
        this.towerIcon = new ImageIcon(url);
        Image temp = this.towerIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        this.towerIcon = new ImageIcon(temp);
        return towerIcon;
    }


    public ImageIcon setTowerIcon2() {
        towerIcon = null;
        return towerIcon;
    }


    @Override
    public String toString() {
        return "Tower{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

    public ImageIcon getTowerIcon() {
        return towerIcon;
    }

    public void setTowerIcon(ImageIcon towerIcon) {
        this.towerIcon = towerIcon;
    }

    public Tower(int lvl) {
        this.lvl = lvl;
        this.id = idCounter++;
    }

    public boolean isWait() {
        return wait;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
