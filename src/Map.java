import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Map extends JFrame {

    private int enemySpeed;

    public Map(int enemySpeed) {

        this.enemySpeed = enemySpeed;
    }

    public Map(ProgramToggle toggle, int enemySpeed) {
        this.toggle = toggle;
        this.enemySpeed = enemySpeed;
        if (enemySpeed == 0) {
            this.enemySpeed = 1000;
        }
    }

    boolean won = false;
    private ProgramToggle toggle;
    boolean stop2 = false;

    public Map(ProgramToggle toggle) {
        this.toggle = toggle;
    }

    public Map() {
    }

    ArrayList<Knight> knights2 = new ArrayList<>();
    JButton PauseButton = new JButton("Stop");
    JButton towerButton = new JButton("Place Tower");
    JButton HelpButton = new JButton("ask Help");
    JButton removeTower = new JButton("Pick Tower up");
    boolean stop = false;

    Color darkGreen = new Color(106, 170, 100);
    Color lightBrown = new Color(194, 155, 99);
    Color finish= new Color(194, 155, 110);
    private static int x = 5;
    private static int y = 6;
    private static int TILE_SIZE = 90; // pixels

    JLabel[][] labels5x5 = new JLabel[5][6];
    JLabel[][] labels7x7 = new JLabel[7][7];
    JLabel[][] labels10x10 = new JLabel[10][10];


    /**
     * Handles the rendering of the map based on the current game state.
     * It updates the map's display by triggering a repaint and recalculating the layout.
     * Additionally, it controls the delay between rendering steps based on whether the game is paused or not.
     *
     * @param pause a boolean indicating whether the game is paused or not. If true, the game will pause the render.
     * @param nextStep a Runnable that represents the next action to perform (e.g., moving the next enemy).
     */
    public void mapRender(Boolean pause, Runnable nextStep) {
        int delay = pause ? 9000 : enemySpeed; // delay in milliseconds
        // System.out.println("enemy speed is "+ enemySpeed);
        new Timer(delay, e -> {
            ((Timer) e.getSource()).stop(); // stop the timer after one run
            revalidate();
            repaint();
            nextStep.run(); // call the next step (e.g. move next enemy)
        }).start();
    }
    /**
     * Creates toolbars for pause, tower placement, help, and remove tower functionality
     * and adds them to the main window with improved styling.
     *
     * @param additionLines the number of additional lines to add below the toolbars
     */
    public void createOptionLine(int additionLines) {
        JToolBar toolBarPause = new JToolBar();
        JToolBar toolBarPlace = new JToolBar();
        JToolBar toolBarHelp = new JToolBar();
        JToolBar toolBarRemove = new JToolBar();

        toolBarPause.add(Box.createHorizontalStrut(20));
        toolBarHelp.add(Box.createHorizontalStrut(10));

        Color barColor = new Color(80, 80, 80);

        toolBarPause.setBackground(barColor);
        toolBarPlace.setBackground(barColor);
        toolBarHelp.setBackground(barColor);
        toolBarRemove.setBackground(barColor);

        toolBarPause.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBarPlace.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBarHelp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBarRemove.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        toolBarPause.setFloatable(false);
        toolBarPlace.setFloatable(false);
        toolBarHelp.setFloatable(false);
        toolBarRemove.setFloatable(false);

        styleButton(PauseButton);
        styleButton(towerButton);
        styleButton(HelpButton);
        styleButton(removeTower);

        toolBarPause.add(PauseButton);
        toolBarPlace.add(towerButton);
        toolBarHelp.add(HelpButton);
        toolBarRemove.add(removeTower);

        add(toolBarPause, BorderLayout.SOUTH);
        add(toolBarPlace, BorderLayout.SOUTH);
        add(toolBarHelp, BorderLayout.SOUTH);
        add(toolBarRemove, BorderLayout.SOUTH);

        for (int i = 0; i < additionLines; i++) {
            JToolBar filler = new JToolBar();
            filler.setFloatable(false);
            filler.setBackground(barColor);
            filler.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            filler.add(Box.createHorizontalStrut(10));
            add(filler, BorderLayout.SOUTH);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(80, 40));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
    }

    /**
     * Returns a list of buttons used for pausing, placing towers, asking for help, and removing towers.
     * @return a list of the buttons
     */
    public ArrayList<JButton> StopResumePlaceHelpRemove() {
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(PauseButton);
        buttons.add(towerButton);
        buttons.add(HelpButton);
        buttons.add(removeTower);
        return buttons;
    }
//region map drawing
    /**
     * Creates a map with 5x5 grid.
     * @param labels 2D array of labels for the map
     */
    public void map5x5(JLabel[][] labels) {
        for (int i = 0; i < y; i++) {
            switch (i) {
                case 0:
                    createLine(5, " 0 1 2", labels,1);
                    break;
                case 1:
                    createLine(5, "2", labels,1);
                    break;
                case 2:
                    createLine(5, " 2 ", labels,1);
                    break;
                case 3:
                    createLine(5, "2", labels,1);
                    break;
                case 4:
                    createFinishLine(5, "2 3 4", labels,1);
                    break;
            }
        }
    }

    /**
     * Creates a map with 5x5 grid and additional variations.
     * @param labels 2D array of labels for the map
     */
    public void map5x5_w2(JLabel[][] labels) {
        for (int i = 0; i < y; i++) {
            switch (i) {
                case 0:
                    createLine(5, "0 1 2 3 4", labels,2);
                    break;
                case 1:
                    createLine(5, "0 4", labels,2);
                    break;
                case 2:
                    createLine(5, "0 1 2 3 4", labels,2);
                    break;
                case 3:
                    createLine(5, "0 4", labels,2);
                    break;
                case 4:
                    createFinishLine(5, "0 1 2 3 4", labels,2);
                    break;
            }
        }
    }

    /**
     * Creates a map with 7x7 grid and additional variations.
     * @param labels 2D array of labels for the map
     */
    public void map7x7_w3(JLabel[][] labels) {
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    createLine(7, "0 1 2 3 4 5", labels,3);
                    break;
                case 1:
                    createLine(7, "2 5", labels,3);
                    break;
                case 2:
                    createLine(7, "2 5", labels,3);
                    break;
                case 3:
                    createLine(7, "2 5 6", labels,3);
                    break;
                case 4:
                    createLine(7, "2 3 6", labels,3);
                    break;
                case 5:
                    createLine(7, "3 4 6", labels,3);
                    break;
                case 6:
                    createFinishLine(7, "4 5 6", labels,3);
                    break;
            }
        }
    }

    /**
     * Creates a map with 10x10 grid and additional variations.
     * @param labels 2D array of labels for the map
     */
    public void map10x10_w4(JLabel[][] labels) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    createLine(10, "1", labels,4);
                    break;
                case 1:
                    createLine(10, "1", labels,4);
                    break;
                case 2:
                    createLine(10, "1", labels,4);
                    break;
                case 3:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels,4);
                    break;
                case 4:
                    createLine(10, "1 4 9", labels,4);
                    break;
                case 5:
                    createLine(10, "1 4 9", labels,4);
                    break;
                case 6:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels,4);
                    break;
                case 7:
                    createLine(10, "4 7 9 ", labels,4);
                    break;
                case 8:
                    createLine(10, "4 7 9", labels,4);
                    break;
                case 9:
                    createFinishLine(10, "4 5 6 7 8 9", labels,4);
                    break;
            }
        }
    }

    /**
     * Creates a map with 10x10 grid and additional variations.
     * @param labels 2D array of labels for the map
     */
    public void map10x10_w5(JLabel[][] labels) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    createLine(10, "4", labels,5);
                    break;
                case 1:
                    createLine(10, "0 1 2 3 4 5 6 7 8 9 ", labels,5);
                    break;
                case 2:
                    createLine(10, "0 9", labels,5);
                    break;
                case 3:
                    createLine(10, " 0 1 2 3 4 5 6 7 9", labels,5);
                    break;
                case 4:
                    createLine(10, " 0 7 9", labels,5);
                    break;
                case 5:
                    createLine(10, "0 1 2 3 4 5 7 9", labels,5);
                    break;
                case 6:
                    createLine(10, "0 5 7 9", labels,5);
                    break;
                case 7:
                    createLine(10, "0 1 2 3 5 7 9", labels,5);
                    break;
                case 8:
                    createLine(10, "3 5 7 9", labels,5);
                    break;
                case 9:
                    createFinishLine(10, "3 4 5 6 7 8 9", labels,5);
                    break;
            }
        }
    }


    public void map10x10_w6(JLabel[][] labels){}









//endregion
public void createTile(int lineLength, Color baseColor, JLabel[][] labels, int mapType) {
    JLabel tile = new JLabel(" ", SwingConstants.CENTER);
    tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
    tile.setOpaque(true);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);








    // Get path and finish colors for this map
    Color pathColor = getPathColor(mapType);
    Color finishColor = getFinishColor(mapType);

    // Identify and tag tile type
    boolean isPath = baseColor.equals(pathColor);
    boolean isFinish = baseColor.equals(finishColor);

    tile.putClientProperty("isPath", isPath);
    tile.putClientProperty("isFinish", isFinish);

    // Shade the base color for texture
    Color texturedColor = blendShades(baseColor, 0.5, 0.3, 0.2);
    tile.setBackground(texturedColor);

    // Border
    tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

    // Gloss only on terrain (not on path or finish)
    tile.setLayout(new BorderLayout());
    if (!isPath && !isFinish) {
        JLabel gloss = new JLabel();
        gloss.setOpaque(true);
        gloss.setBackground(new Color(255, 255, 255, 18));
        gloss.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE / 2));
        tile.add(gloss, BorderLayout.NORTH);
    }

    add(tile);

    // Place in grid
    boolean placed = false;
    for (int y = 0; y < lineLength && !placed; y++) {
        for (int x = 0; x < lineLength; x++) {
            if (labels[x][y] == null) {
                labels[x][y] = tile;
                placed = true;
                break;
            }
        }
    }
}

    // Helper to simulate textured shading
    private Color blendShades(Color base, double baseWeight, double darkerWeight, double darker2Weight) {
        Color darker = base.darker();
        Color muchDarker = darker.darker();
        return new Color(
                (int)(base.getRed() * baseWeight + darker.getRed() * darkerWeight + muchDarker.getRed() * darker2Weight),
                (int)(base.getGreen() * baseWeight + darker.getGreen() * darkerWeight + muchDarker.getGreen() * darker2Weight),
                (int)(base.getBlue() * baseWeight + darker.getBlue() * darkerWeight + muchDarker.getBlue() * darker2Weight)
        );
    }

    public void createLine(int lineLength, String filledTiles, JLabel[][] labels5x5, int mapType) {
        String[] tiles = filledTiles.trim().split("\\s+");
        int[] numbers = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            numbers[i] = Integer.parseInt(tiles[i]);
        }

        Color pathColor = getPathColor(mapType);
        Color terrainColor = getTerrainColor(mapType);

        for (int i = 0; i < lineLength; i++) {
            if (contains(numbers, i)) {
                createTile(lineLength, pathColor, labels5x5, mapType);
            } else {
                createTile(lineLength, terrainColor, labels5x5, mapType);
            }
        }
    }

    public void createFinishLine(int lineLength, String filledTiles, JLabel[][] labels5x5, int mapType) {
        String[] tiles = filledTiles.trim().split("\\s+");
        int[] numbers = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            numbers[i] = Integer.parseInt(tiles[i]);
        }

        Color pathColor = getPathColor(mapType);
        Color terrainColor = getTerrainColor(mapType);
        Color finishColor = getFinishColor(mapType);

        for (int i = 0; i < lineLength; i++) {
            if (i == lineLength - 1) {
                createTile(lineLength, finishColor, labels5x5, mapType);
                break;
            }

            if (contains(numbers, i)) {
                createTile(lineLength, pathColor, labels5x5, mapType);
            } else {
                createTile(lineLength, terrainColor, labels5x5, mapType);
            }
        }
    }

    // Get unique terrain color for each map type
    private Color getTerrainColor(int mapType) {
        return switch (mapType) {
            case 0 -> new Color(110, 140, 80);      // Grassland
            case 1 -> new Color(170, 220, 130);     // Bright Plains
            case 2 -> new Color(230, 235, 240);     // Snowfield
            case 3 -> new Color(200, 180, 140);     // 🌵 Canyon terrain (new)
            case 4 -> new Color(90, 100, 150);      // Crystal Cavern
            case 5 -> new Color(80, 110, 90);       // Swamp
            default -> new Color(110, 140, 80);
        };
    }

    private Color getPathColor(int mapType) {
        return switch (mapType) {
            case 0 -> new Color(100, 90, 60);       // Dirt path
            case 1 -> new Color(130, 160, 90);      // Grass trail
            case 2 -> new Color(190, 200, 210);     // Snow path
            case 3 -> new Color(150, 120, 90);      // 🟤 Dry sand path (new)
            case 4 -> new Color(120, 160, 200);     // Crystal path
            case 5 -> new Color(50, 70, 60);        // Mud path
            default -> new Color(100, 90, 60);
        };
    }

    private Color getFinishColor(int mapType) {
        return switch (mapType) {
            case 0 -> new Color(150, 120, 80);      // Forest base
            case 1 -> new Color(170, 200, 100);     // Hilltop
            case 2 -> new Color(255, 255, 255);     // Ice crystal
            case 3 -> new Color(180, 140, 110);     // 🟠 Stone gate (new desert style)
            case 4 -> new Color(160, 190, 240);     // Crystal exit
            case 5 -> new Color(100, 130, 110);     // Swamp den
            default -> new Color(150, 120, 80);
        };
    }










    /**
     * Checks if a value exists in an array.
     * @param array the array to search through
     * @param value the value to search for
     * @return true if the value exists, false otherwise
     */
    public boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates the map window and sets up the knight path based on the current wave.
     * @param wave the current wave number
     * @return a list of points representing the knight's path
     * @throws InterruptedException if the thread is interrupted
     */
    private boolean isDialogShown = false;
    public ArrayList<Point> MapWindow5x5(int wave) throws InterruptedException {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);









        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isDialogShown) return; // prevent multiple dialogs
                isDialogShown = true;

                int choice = JOptionPane.showConfirmDialog(
                        Map.this,
                        "Do you exit the game",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(32);
                    dispose();
                }

                isDialogShown = false; // allow future close attempts

            }
        });







        ArrayList<Point> KnightPath = new ArrayList<>();
        Point finishTile = null;

        switch (wave) {
            case 1, 2 -> {
                x = 5;
                y = 6;
            }
            case 3 -> {
                x = 7;
                y = 8;
            }
            case 4, 5,6 -> {
                x = 10;
                y = 11;
            }

        }

        setTitle("Map");

        setSize(x * TILE_SIZE, y * TILE_SIZE);
        setLayout(new GridLayout(y, x));

        JLabel[][] usedlabels;
        switch (wave) {
            case 1 -> {
                map5x5(labels5x5);
                createOptionLine(1);
                usedlabels = labels5x5;
            }
            case 2 -> {
                map5x5_w2(labels5x5);
                createOptionLine(1);
                usedlabels = labels5x5;
            }
            case 3 -> {
                map7x7_w3(labels7x7);
                createOptionLine(3);
                usedlabels = labels7x7;
            }
            case 4 -> {
                map10x10_w4(labels10x10);
                createOptionLine(6);
                usedlabels = labels10x10;
            }
            case 5 -> {
                map10x10_w5(labels10x10);
                createOptionLine(6);
                usedlabels = labels10x10;
            }  case 6 -> {
                map10x10_w5(labels10x10);
                createOptionLine(6);
                usedlabels = labels10x10;
            }
            default -> usedlabels = labels5x5;
        }

        // Detect all path tiles and finish tile
        if (usedlabels != null) {
            for (int row = 0; row < usedlabels.length; row++) {
                for (int col = 0; col < usedlabels[row].length; col++) {
                    JLabel label = usedlabels[row][col];
                    if (label != null) {
                        if (Boolean.TRUE.equals(label.getClientProperty("isPath"))||Boolean.TRUE.equals(label.getClientProperty("isFinish"))


                        ) {
                            KnightPath.add(new Point(row, col));
                        }

                    }
                }
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Knight k = new Knight(100);
        k.setKnightIcon();

        revalidate();
        System.out.println("map drawn");
        Thread.sleep(500);

        System.out.println("Wave: " + wave + " | Grid: " + y + "x" + x);

        return KnightPath;
    }


    private boolean watcherRunning = false;
    /**
     * Handles the movement and actions of the tower strikes based on knight positions.
     * @param knights the list of knights
     * @param towerIndexes the list of tower positions
     * @param towers the list of towers
     * @param finishX the x-coordinate of the finish line
     * @param finishY the y-coordinate of the finish line
     * @param labelss the 2D array of labels for the map
     */
    public void towerStrikeWatcher(ArrayList<Knight> knights, ArrayList<Integer> towerIndexes, ArrayList<Tower> towers, int finishX, int finishY, JLabel[][] labelss)
    {
        if (watcherRunning) return;
        watcherRunning = true;
        stop = false;

        for (Tower t : towers) {
            t.setPosition(-99, -99);
        }

        for (int i = 0; i < towers.size(); i++) {
            if (i * 2 + 1 < towerIndexes.size() && towers.get(i).getTowerIcon() != null) {
                int x = towerIndexes.get(i * 2);
                int y = towerIndexes.get(i * 2 + 1);
                towers.get(i).setPosition(x, y);
            }
        }

        new Thread(() -> {
            try {
                ArrayList<Tower> towersToRemove = new ArrayList<>();

                while (true) {
                    for (Tower tower : new ArrayList<>(towers)) {
                        tower.setTowerIcon();

                        if (tower.getDurability() <= 0 || tower.getX() < 0 || tower.getY() < 0) continue;

                        int x = tower.getX();
                        int y = tower.getY();
                        boolean towerHitThisRound = false;

                        for (Knight knight : knights) {
                            if (knight.getHealth() <= 0) continue;

                            int kx = knight.getCurrentX();
                            int ky = knight.getCurrentY();

                            boolean adjacent = (x - 1 == kx && y == ky) ||
                                    (x + 1 == kx && y == ky) ||
                                    (x == kx && y - 1 == ky) ||
                                    (x == kx && y + 1 == ky);

                            if (adjacent) {
                                if (!towerHitThisRound) {
                                    tower.setTowerIcon();
                                    if (tower.getDurability() > 0) {
                                        labelss[x][y].setIcon(tower.getTowerIcon());
                                        labelss[x][y].revalidate();
                                        labelss[x][y].repaint();
                                    }

                                    towerHitThisRound = true;
                                }

                                knight.setHealth(0);
                                knight.setKnightIcon2(null);
                                int finalKx = kx;
                                int finalKy = ky;
                                SwingUtilities.invokeLater(() -> {
                                    labelss[finalKy][finalKx].setIcon(null);
                                    labelss[finalKy][finalKx].revalidate();
                                    labelss[finalKy][finalKx].repaint();
                                });
                                Thread.sleep(50);
                            }
                        }

                        if (tower.getDurability() <= 0 && !towersToRemove.contains(tower)) {
                            tower.setTowerIcon2();
                            tower.setPosition(-99, -99); // mark as removed

                            SwingUtilities.invokeLater(() -> {
                                labelss[x][y].setIcon(null);
                                labelss[x][y].revalidate();
                                labelss[x][y].repaint();
                            });
                        }
                    }
                    boolean allDead = true;
                    boolean reachedEnd = false;

                    for (Knight knight : knights) {
                        if (knight.getHealth() > 0) {
                            allDead = false;
                            if (knight.isAt(finishX, finishY)) {
                                reachedEnd = true;
                                break;
                            }
                        }
                    }

                    if (reachedEnd) {
                        if (!stop) {
                            toggle.setGameResult(ProgramToggle.Result.LOST);

                            stop = true;
                        }
                        this.dispose();
                        break;
                    }

                    if (allDead) {
                        if (!stop) {
                            toggle.setGameResult(ProgramToggle.Result.WON);

                            stop = true;
                        }
                        this.dispose();
                        break;
                    }

                    Thread.sleep(50);
                    watcherRunning = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public ProgramToggle getToggle() {
        return toggle;
    }

    public void setToggle(ProgramToggle toggle) {
        this.toggle = toggle;
    }

}
