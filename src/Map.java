import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Map extends JFrame {


    Color darkGreen = new Color(106, 170, 100);
    Color lightBrown  = new Color(194, 155, 99);
    private static  int x= 5;
    private static int y = 5;
    private static int TILE_SIZE = 70; // pixels
    public void createTile(Color color){
        JLabel tile = new JLabel(" ", SwingConstants.CENTER);
        tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        tile.setBackground(color);
        tile.setOpaque(true);
        add(tile);
    }
    public void createLine(int lineLength, String filledTiles) {
        // Parse input string into integer array
        String[] tiles = filledTiles.trim().split("\\s+");
        int[] numbers = new int[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            numbers[i] = Integer.parseInt(tiles[i]);
            System.out.println(numbers[i]);
        }

        // Create the line
        for (int i = 0; i < lineLength; i++) {
            if (contains(numbers, i)) {
                createTile(lightBrown);
            } else {
                createTile(darkGreen);
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
    public void MapWindow5x5() {
        setTitle("15x15 Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x * TILE_SIZE, y* TILE_SIZE);



         setLayout(new GridLayout(x, y));

        // Fill the window with buttons or labels representing tiles
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

        pack(); // Adjust window to fit all tiles
       setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }



}
