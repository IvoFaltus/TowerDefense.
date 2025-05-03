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

    JButton PauseButton = new JButton("Stop");
    //****

    //****
    JButton towerButton = new JButton("Place Tower");
    JButton HelpButton = new JButton("ask Help");
    JButton removeTower = new JButton("remove Tower");



    Color darkGreen = new Color(106, 170, 100);
    Color lightBrown  = new Color(194, 155, 99);
    private static  int x= 5;
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

    toolBar1.setBackground(new Color(80, 80, 80) );
    toolBar2.setBackground(new Color(80, 80, 80) );
    toolBar3.setBackground(new Color(80, 80, 80) );
    toolBar4.setBackground(new Color(80, 80, 80) );
    toolBar5.setBackground(new Color(80, 80, 80) );

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


add(toolBar2,BorderLayout.CENTER);
add(toolBar3,BorderLayout.CENTER);
add(toolBar4,BorderLayout.CENTER);
add(toolBar5,BorderLayout.CENTER);
    for(int i=0;i<additionLines;i++){
        add(tile);
    }








}



public ArrayList<JButton> StopResumePlaceHelpRemove(){
    ArrayList<JButton> buttons = new ArrayList<>();
    buttons.add(PauseButton);
    buttons.add(towerButton);
    buttons.add(HelpButton);
    buttons.add(removeTower);
return buttons;
}



/*
 JButton PauseButton = new JButton("Stop");
    //****
    JButton StartButton = new JButton("Resume");
    //****
    JButton towerButton = new JButton("Place Tower");
    JButton HelpButton = new JButton("ask Help");
    JButton removeTower = new JButton("remove Tower");
 */


public void map5x5(){
    for (int i = 0; i < y; i++) {
        switch (i) {
            case 0:

                createLine(5," 0 1 2");
                break;
            case 1:

                createLine(5,"2");

                break;
            case 2:
                createLine(5," 2 ");
                break;
            case 3:
                createLine(5,"2");
                break;
            case 4:
                createLine(5,"2 3 4" );
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
                createTile( lineLength,lightBrown);
            } else {
                createTile(lineLength,darkGreen);
            }
        }
    }

    public boolean contains(int[] array, int value) {
        for (int i =0;i<array.length;i++) {
            if(array[i]==value ){
                return true;
            }
        }
        return false;
    }

    public JLabel[][] MapWindow5x5() throws InterruptedException {

        setTitle("15x15 Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x * TILE_SIZE, y* TILE_SIZE);



         setLayout(new GridLayout(y, x));


       map5x5();
       createOptionLine(1);
//createLine(5," 0 1 2 3 4 5");
        pack(); // Adjust window to fit all tiles
       setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        Knight k = new Knight(100);
        k.setKnightIcon();









        //revalidate();   // Recalculates the layout if needed

        Thread.sleep(500);



/*
        for(int i2=0;i2<rows;i2++){
            labels5x5[0][i2].setText(String.valueOf(i2));
            for(int j=0;j<cols;j++){

                if(i2==0){
                    labels5x5[j][i2].setText(String.valueOf(j));
                }
            }
        }
*/



        return labels5x5;





    }



    public void mapRender(Boolean pause) throws Exception {

        Thread.sleep(1000);

        if (pause) {
            Thread.sleep(9000);
        }

        revalidate();
        repaint();

    }



    public void towerStrikeWatcher( ArrayList<Knight> knights, ArrayList<Integer> towerIndexes) {
        System.out.println(knights.size()+" velikost");
        new Thread(() -> {
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
                        // Get knight's current position
                        int kx = hitKnight.getCurrentX();
                        int ky = hitKnight.getCurrentY();

                        // Remove icon from the tile
                        labels5x5[ky][kx].setIcon(null);

                        // Force UI to update immediately
                        labels5x5[ky][kx].revalidate();
                        labels5x5[ky][kx].repaint();

                        // Remove knight from list


                        System.out.println("Knight removed!");
                    }

                    Thread.sleep(50); // delay between checks
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }








}
