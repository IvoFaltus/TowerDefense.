import javax.swing.*;
import javax.swing.*;
import java.awt.*;

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
    public void createLine(int mapSize,int typeOfLine) {
        int number = (mapSize / 2);
        if (typeOfLine == 0) {
            for (int j = 0; j < x; j++) {
                if (j == number) {
                    createTile(lightBrown);
                } else {
                    createTile(darkGreen);
                }
            }
        } else {
            for (int j = 0; j < x; j++) {

                createTile(lightBrown);


            }

        }
    }
    public void MapWindow() {
        setTitle("15x15 Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x * TILE_SIZE, y* TILE_SIZE);



         setLayout(new GridLayout(x, y));

        // Fill the window with buttons or labels representing tiles
        for (int i = 0; i < y; i++) {
           switch (i) {
               case 0:

              createLine(5,0);
               break;
               case 1:

                   createLine(5,0);

                   break;
               case 2:
                   createLine(5,1);
                   break;
               case 3:
                   createLine(5,0);
                   break;
               case 4:
                   createLine(5,0);
                   break;

           }


        }

        pack(); // Adjust window to fit all tiles
       setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }



}
