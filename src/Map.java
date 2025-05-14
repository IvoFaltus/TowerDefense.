import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map extends JFrame {
    boolean won = false;
    private ProgramToggle toggle;

    public Map(ProgramToggle toggle) {
        this.toggle =toggle;
    }

    public Map() {
    }

    JButton PauseButton = new JButton("Stop");
    JButton towerButton = new JButton("Place Tower");
    JButton HelpButton = new JButton("ask Help");
    JButton removeTower = new JButton("remove Tower");


    Color darkGreen = new Color(106, 170, 100);
    Color lightBrown = new Color(194, 155, 99);
    private static int x = 5;
    private static int y = 6;
    private static int TILE_SIZE = 90; // pixels

    JLabel[][] labels5x5 = new JLabel[5][6];

    public void createOptionLine(int additionLines) {
        JLabel tile = new JLabel(" ", SwingConstants.CENTER);
        //tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        tile.setBackground(new Color(80, 80, 80));


        tile.setOpaque(true);

        JToolBar toolBar1 = new JToolBar();
        JToolBar toolBar2 = new JToolBar();
        JToolBar toolBar3 = new JToolBar();
        JToolBar toolBar4 = new JToolBar();
        JToolBar toolBar5 = new JToolBar();

/*
        towerButton.addActionListener(e -> {
            try {
                Player p = new Player();
                p.OpenInventory(labels5x5);

                //Tower.placeTower(labels5x5,1);

            } catch (Exception E) {
            }
        });
*/

        toolBar1.setBackground(new Color(80, 80, 80));
        toolBar2.setBackground(new Color(80, 80, 80));
        toolBar3.setBackground(new Color(80, 80, 80));
        toolBar4.setBackground(new Color(80, 80, 80));
        toolBar5.setBackground(new Color(80, 80, 80));

        toolBar1.add(Box.createHorizontalStrut(20));
        toolBar2.add(Box.createHorizontalStrut(20));
        toolBar3.add(Box.createHorizontalStrut(5));
        toolBar4.add(Box.createHorizontalStrut(20));
        toolBar5.add(Box.createHorizontalStrut(5));

        toolBar1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBar2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBar3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBar4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBar5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        toolBar1.setFloatable(false);
        toolBar2.setFloatable(false);
        toolBar3.setFloatable(false);
        toolBar4.setFloatable(false);
        toolBar5.setFloatable(false);


        toolBar2.add(PauseButton);
        toolBar3.add(towerButton);
        toolBar4.add(HelpButton);
        toolBar5.add(removeTower);


        add(toolBar2, BorderLayout.CENTER);
        add(toolBar3, BorderLayout.CENTER);
        add(toolBar4, BorderLayout.CENTER);
        add(toolBar5, BorderLayout.CENTER);
        for (int i = 0; i < additionLines; i++) {
            add(tile);
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


    public void map5x5() {
        for (int i = 0; i < y; i++) {
            switch (i) {
                case 0:

                    createLine(5, " 0 1 2");
                    break;
                case 1:

                    createLine(5, "2");

                    break;
                case 2:
                    createLine(5, " 2 ");
                    break;
                case 3:
                    createLine(5, "2");
                    break;
                case 4:
                    createLine(5, "2 3 4");
                    break;

            }


        }
    }

    public void createTile(int lineLength, Color color) {
        JLabel tile = new JLabel(" ", SwingConstants.CENTER);
        tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tile.setBackground(color);
        tile.setOpaque(true);
        add(tile);


        boolean placed = false;
        for (int y = 0; y < lineLength && !placed; y++) {
            for (int x = 0; x < lineLength; x++) {
                if (labels5x5[x][y] == null) {
                    labels5x5[x][y] = tile;
                    placed = true;
                    break;
                }
            }
        }


    }


    public void createLine(int lineLength, String filledTiles) {
        // Parse input string into integer array
        String[] tiles = filledTiles.trim().split("\\s+");
        int[] numbers = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            numbers[i] = Integer.parseInt(tiles[i]);

        }

        // Create the line
        for (int i = 0; i < lineLength; i++) {
            if (contains(numbers, i)) {
                createTile(lineLength, lightBrown);
            } else {
                createTile(lineLength, darkGreen);
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

    public void MapWindow5x5() throws InterruptedException {

        setTitle("Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x * TILE_SIZE, y * TILE_SIZE);


        setLayout(new GridLayout(y, x));


        map5x5();
        createOptionLine(1);
//createLine(5," 0 1 2 3 4 5");
        pack(); // Adjust window to fit all tiles
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        Knight k = new Knight(100);
        k.setKnightIcon();


        revalidate();   // Recalculates the layout if needed

        Thread.sleep(500);


    }


    public void mapRender(Boolean pause, Runnable nextStep) {
        int delay = pause ? 9000 : 1000; // delay in milliseconds

        new Timer(delay, e -> {
            ((Timer) e.getSource()).stop(); // stop the timer after one run
            revalidate();
            repaint();
            nextStep.run(); // call the next step (e.g. move next enemy)
        }).start();
    }

    public void towerStrikeWatcher(ArrayList<Knight> knights, ArrayList<Integer> towerIndexes, ArrayList<Tower> towers, int finishX, int finishY) {
        System.out.println(towers.get(0).getDurability()+" dur");
        System.out.println(towers.get(1).getDurability()+" dur");
        System.out.println();

        new Thread(() -> {

            int knightsDead = 0;



            for (int i = 0; i < towerIndexes.size(); i += 2) {
                int x = towerIndexes.get(i);
                int y = towerIndexes.get(i + 1);

                int towerIndex = i / 2;
                if (towerIndex < towers.size()) {
                    towers.get(towerIndex).setPosition(x, y);
                }
            }


            try {
                while (true) {
                    boolean towerStrikes = false;
                    int temp = -1;
                    int indexOfHitKnight = -1;

                    for (int y = 0; y < this.y; y++) {
                        for (int x = 0; x < this.x; x++) {

                            // First tower
                            if (towerIndexes.size() >= 2 && towerIndexes.get(0) == x && towerIndexes.get(1) == y) {
                                indexOfHitKnight = -1;
                                for (Knight knight : knights) {
                                    indexOfHitKnight++;
                                    int kx = knight.getCurrentX();
                                    int ky = knight.getCurrentY();

                                    if ((x - 1 == kx && y == ky) ||
                                            (x + 1 == kx && y == ky) ||
                                            (x == kx && y - 1 == ky) ||
                                            (x == kx && y + 1 == ky)) {
                                        towerStrikes = true;
                                        temp = indexOfHitKnight;
                                        for (Tower tower : towers) {
                                            if (tower.isAt(x, y)) {
                                                tower.setDurability(tower.getDurability() - 1);
                                                System.out.println("yes1");
                                                break;
                                            }
                                        }


                                        for (Knight k : knights) {
                                            if ((k.isAt(x - 1, y))
                                                    || (k.isAt(x + 1, y))
                                                    || (k.isAt(x, y - 1))
                                                    || (k.isAt(x, y + 1))
                                            ) {

                                                k.setHealth(0);
                                            }
                                        }


                                        break;


                                    }
                                }
                            }

                            // Second tower
                            if (towerIndexes.size() >= 4 && towerIndexes.get(2) == x && towerIndexes.get(3) == y) {
                                indexOfHitKnight = -1;
                                for (Knight knight : knights) {
                                    indexOfHitKnight++;
                                    int kx = knight.getCurrentX();
                                    int ky = knight.getCurrentY();

                                    if ((x - 1 == kx && y == ky) ||
                                            (x + 1 == kx && y == ky) ||
                                            (x == kx && y - 1 == ky) ||
                                            (x == kx && y + 1 == ky)) {
                                        towerStrikes = true;
                                        temp = indexOfHitKnight;
                                        for (Tower tower : towers) {
                                            if (tower.isAt(x, y)) {
                                                tower.setDurability(tower.getDurability() - 1);
                                                System.out.println("yes");
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (towerStrikes && temp >= 0 && temp < knights.size()) {
                        Knight hitKnight = knights.get(temp);
                        Thread.sleep(100);
                        knights.get(temp).setKnightIcon2(null);
                        knights.get(temp).setHealth(0);
                        // Get knight's current position
                        int kx = hitKnight.getCurrentX();
                        int ky = hitKnight.getCurrentY();

                        // Remove icon from the tile
                        labels5x5[ky][kx].setIcon(null);

                        // Force UI to update immediately
                        labels5x5[ky][kx].revalidate();
                        labels5x5[ky][kx].repaint();

                        // Remove knight from list
                        int temp2 = 0;

                        for (int i = 0; i < knights.size(); i++) {

                            if (knights.get(i).getKnightIcon() != null) {
                                temp2++;

                            }

                        }
                        if (temp2 > 0) {
                            //
                        }


                    }
                    int velikost = 0;

                    Thread.sleep(50);

                    boolean allDead = true;
                    boolean atFinish = false;
                    for (Knight knight : knights) {

                        if (knight.getHealth() > 0) {
                            allDead = false;
                        }
                        if (knight.isAt(finishX, finishY)) {

                            atFinish = true;
                        }

                    }

                    if (allDead) {

                        Menu m = new Menu(toggle);
                        this.dispose();

                        toggle.setGameResult(ProgramToggle.Result.WON);

                        break;
                    }

                    if (atFinish) {


                        this.dispose();
                        Menu m = new Menu(toggle);
                        toggle.setGameResult(ProgramToggle.Result.LOST);

                        break;
                    }


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
