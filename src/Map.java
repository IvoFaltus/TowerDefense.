import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    JButton removeTower = new JButton("remove Tower");
    boolean stop = false;

    Color darkGreen = new Color(106, 170, 100);
    Color lightBrown = new Color(194, 155, 99);
    private static int x = 5;
    private static int y = 6;
    private static int TILE_SIZE = 90; // pixels

    JLabel[][] labels5x5 = new JLabel[5][6];
    JLabel[][] labels7x7 = new JLabel[7][7];
    JLabel[][] labels10x10 = new JLabel[10][10];

    public void createOptionLine(int additionLines) {
        // Toolbar s hlavními tlačítky
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

        toolBarPause.add(PauseButton);
        toolBarPlace.add(towerButton);
        toolBarHelp.add(HelpButton);
        toolBarRemove.add(removeTower);

        // Přidání toolbarů do hlavního okna
        add(toolBarPause, BorderLayout.SOUTH);
        add(toolBarPlace, BorderLayout.SOUTH);
        add(toolBarHelp, BorderLayout.SOUTH);
        add(toolBarRemove, BorderLayout.SOUTH);

        // Přidání dalších prázdných řádků podle potřeby
        for (int i = 0; i < additionLines; i++) {
            JToolBar filler = new JToolBar();
            filler.setFloatable(false);
            filler.setBackground(barColor);
            filler.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            filler.add(Box.createHorizontalStrut(10));
            add(filler, BorderLayout.SOUTH);
        }
    }



    public ArrayList<JButton> StopResumePlaceHelpRemove() {
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(PauseButton);
        buttons.add(towerButton);
        buttons.add(HelpButton);
        buttons.add(removeTower);
        return buttons;
    }


    public void map5x5(JLabel[][] labels) {
        for (int i = 0; i < y; i++) {
            switch (i) {
                case 0:

                    createLine(5, " 0 1 2", labels);
                    break;
                case 1:

                    createLine(5, "2", labels);

                    break;
                case 2:
                    createLine(5, " 2 ", labels);
                    break;
                case 3:
                    createLine(5, "2", labels);
                    break;
                case 4:
                    createLine(5, "2 3 4", labels);
                    break;

            }


        }
    }
    public void map5x5_w2(JLabel[][] labels) {
        for (int i = 0; i < y; i++) {
            switch (i) {
                case 0:
                    createLine(5, "0 1 2 3 4", labels);  // top row → →
                    break;
                case 1:
                    createLine(5, "0 4", labels);        // ↓ ↓
                    break;
                case 2:
                    createLine(5, "0 1 2 3 4", labels);  // middle → →
                    break;
                case 3:
                    createLine(5, "0 4", labels);        // ↓ ↓
                    break;
                case 4:
                    createLine(5, "0 1 2 3 4", labels);  // bottom → →
                    break;
            }
        }
    }
    public void map7x7_w3(JLabel[][] labels) {
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    createLine(7, "0 1 2 3 4 5", labels); // vstup
                    break;
                case 1:
                    createLine(7, "2 5", labels);
                    break;
                case 2:
                    createLine(7, "2 5", labels);
                    break;
                case 3:
                    createLine(7, "2 5 6", labels); // bod rozdělení
                    break;
                case 4:
                    createLine(7, "2 3 6", labels); // dvě cesty
                    break;
                case 5:
                    createLine(7, "3 4 6", labels);
                    break;
                case 6:
                    createLine(7, "4 5 6", labels); // výstupy
                    break;
            }
        }
    }
    public void map10x10_w4(JLabel[][] labels) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    createLine(10, "1", labels); // vstup
                    break;
                case 1:
                    createLine(10, "1", labels);
                    break;
                case 2:
                    createLine(10, "1", labels); // rozdělení
                    break;
                case 3:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels);
                    break;
                case 4:
                    createLine(10, "1 4 9", labels);
                    break;
                case 5:
                    createLine(10, "1 4 9", labels); // průsečík
                    break;
                case 6:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels); // spojení cest
                    break;
                case 7:
                    createLine(10, "4 7 9 ", labels);
                    break;
                case 8:
                    createLine(10, "4 7 9", labels);
                    break;
                case 9:
                    createLine(10, "4 5 6 7 8 9", labels); // výstup
                    break;
            }
        }
    }
    public void map10x10_w5(JLabel[][] labels) {
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    createLine(10, "1", labels); // vstup
                    break;
                case 1:
                    createLine(10, "1", labels);
                    break;
                case 2:
                    createLine(10, "1", labels); // rozdělení
                    break;
                case 3:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels);
                    break;
                case 4:
                    createLine(10, "1 4 9", labels);
                    break;
                case 5:
                    createLine(10, "1 4 9", labels); // průsečík
                    break;
                case 6:
                    createLine(10, "1 2 3 4 5 6 7 8 9", labels); // spojení cest
                    break;
                case 7:
                    createLine(10, "4 7 9 ", labels);
                    break;
                case 8:
                    createLine(10, "4 7 9", labels);
                    break;
                case 9:
                    createLine(10, "4 5 6 7 8 9", labels); // výstup
                    break;
            }
        }
    }



    public void createTile(int lineLength, Color color, JLabel[][] labels) {
        JLabel tile = new JLabel(" ", SwingConstants.CENTER);
        tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tile.setBackground(color);
        tile.setOpaque(true);
        add(tile);


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


    public void createLine(int lineLength, String filledTiles, JLabel[][] labels5x5) {
        // Parse input string into integer array
        String[] tiles = filledTiles.trim().split("\\s+");
        int[] numbers = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            numbers[i] = Integer.parseInt(tiles[i]);

        }

        // Create the line
        for (int i = 0; i < lineLength; i++) {
            if (contains(numbers, i)) {
                createTile(lineLength, lightBrown, labels5x5);
            } else {
                createTile(lineLength, darkGreen, labels5x5);
            }
        }
    }

    public boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void MapWindow5x5(int wave) throws InterruptedException {


        switch (wave) {
            case 1:
               // System.out.println(x);
                //System.out.println(y);
              x=5;
               y=6;
                break;
            case 2:

                x=5;
                y=6;
                break;
            case 3:

                x=7;
                y=8;
                break;
            case 4:
                x=10;
                y=11;
                break;
            case 5:
                x=10;
                y=10;
                break;
            case 6:
                break;

        }








        setTitle("Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x * TILE_SIZE, y * TILE_SIZE);


        setLayout(new GridLayout(y, x));


        switch (wave) {
            case 1:

                map5x5(labels5x5);
                createOptionLine(1);
                break;
            case 2:

                map5x5_w2(labels5x5);
                createOptionLine(1);
                break;
            case 3:

                map7x7_w3(labels7x7);
                createOptionLine(3);
                break;
            case 4:
                map10x10_w4(labels10x10);
                createOptionLine(6);
                break;
            case 5:
                map10x10_w5(labels10x10);
                createOptionLine(6);
                break;
            case 6:
                break;

        }


//createLine(5," 0 1 2 3 4 5");
        pack(); // Adjust window to fit all tiles
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        Knight k = new Knight(100);
        k.setKnightIcon();


        revalidate();   // Recalculates the layout if needed
        System.out.println("map drawn");
        Thread.sleep(500);

        System.out.println("Wave: " + wave + " | Grid: " + y + "x" + x);
    }


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

    private boolean watcherRunning = false;

    public void towerStrikeWatcher(ArrayList<Knight> knights, ArrayList<Integer> towerIndexes, ArrayList<Tower> towers, int finishX, int finishY) {
        if (watcherRunning) return;         // ✅ prevent multiple threads
        watcherRunning = true;              // ✅ mark as running
        stop = false;








/*
        System.out.println("Tower at (" + targetTower[0].getX() + "," + targetTower[0].getY() + ") durability now: " + targetTower[0].getDurability());
        if (destroyTower) {
            targetTower[0].setDurability(targetTower[0].getDurability() - 1);
        }


        destroyTower = false;
        */
        // Place towers on the map
        for (Tower t : towers) {
            t.setPosition(-99, -99); // mark as "not placed"

        }

        for (int i = 0; i < towers.size(); i++) {
            if (i * 2 + 1 < towerIndexes.size() && towers.get(i).getTowerIcon() != null) {

                int x = towerIndexes.get(i * 2);
                int y = towerIndexes.get(i * 2 + 1);

                // System.out.println("tower indexes " + towerIndexes.size());
                towers.get(i).setPosition(x, y);
                // System.out.println("Tower[" + i + "] at (" + towers.get(i).getX() + ", " + towers.get(i).getY() + ")");
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

                            boolean adjacent =
                                    (x - 1 == kx && y == ky) ||
                                            (x + 1 == kx && y == ky) ||
                                            (x == kx && y - 1 == ky) ||
                                            (x == kx && y + 1 == ky);

                            if (adjacent) {
                                if (!towerHitThisRound) {
                                    //tower.setDurability(tower.getDurability()-1);
                                    // System.out.println("Tower at (" + x + "," + y + ") durability now: " + tower.getDurability());

                                    tower.setTowerIcon();
                                    if (tower.getDurability() > 0) {
                                        labels5x5[x][y].setIcon(tower.getTowerIcon());
                                        labels5x5[x][y].revalidate();
                                        labels5x5[x][y].repaint();
                                    }

                                    towerHitThisRound = true;
                                }

                                knight.setHealth(0);
                                knight.setKnightIcon2(null);
                                int finalKx = kx;
                                int finalKy = ky;
                                SwingUtilities.invokeLater(() -> {
                                    labels5x5[finalKy][finalKx].setIcon(null);
                                    labels5x5[finalKy][finalKx].revalidate();
                                    labels5x5[finalKy][finalKx].repaint();
                                });

                                Thread.sleep(200);
                            }
                        }

                        if (tower.getDurability() <= 0 && !towersToRemove.contains(tower)) {
                            // System.out.println("Tower at (" + x + "," + y + ") destroyed!");
                            tower.setTowerIcon2();
                            tower.setPosition(-99, -99); // mark as removed

                            SwingUtilities.invokeLater(() -> {
                                labels5x5[x][y].setIcon(null);
                                labels5x5[x][y].revalidate();
                                labels5x5[x][y].repaint();
                            });

                            //towersToRemove.add(tower);
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
                        // System.out.println("Knight reached the end! Game lost.");
                        if (!stop) {
                            toggle.setGameResult(ProgramToggle.Result.LOST);

                            stop = true;
                        }
                        this.dispose();
                        break;
                    }

                    if (allDead) {
                        //  System.out.println("All knights dead! Game won.");
                        if (!stop) {


                            toggle.setGameResult(ProgramToggle.Result.WON);

                            stop = true;
                        }
                        this.dispose();
                        break;
                    }

                    //towers.removeAll(towersToRemove);
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
