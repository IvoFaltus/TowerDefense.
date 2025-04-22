import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.lang.reflect.Array;

public class Map extends JFrame {


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

    JButton PauseButton = new JButton("Stop");
    JButton StartButton = new JButton("Resume");
    JButton towerButton = new JButton("Place Tower");
    JButton HelpButton = new JButton("ask Help");

    toolBar1.setBackground(new Color(80, 80, 80) );
    toolBar2.setBackground(new Color(80, 80, 80) );
    toolBar3.setBackground(new Color(80, 80, 80) );
    toolBar4.setBackground(new Color(80, 80, 80) );

    toolBar1.add(Box.createHorizontalStrut(20));
    toolBar2.add(Box.createHorizontalStrut(20));
    toolBar3.add(Box.createHorizontalStrut(5));
    toolBar4.add(Box.createHorizontalStrut(20));

    toolBar1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    toolBar2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    toolBar3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    toolBar4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




    toolBar1.setFloatable(false);
    toolBar2.setFloatable(false);
    toolBar3.setFloatable(false);
    toolBar4.setFloatable(false);

    toolBar1.add(StartButton);
    toolBar2.add(PauseButton);
toolBar3.add(towerButton);
toolBar4.add(HelpButton);

add(toolBar1,BorderLayout.CENTER);
add(toolBar2,BorderLayout.CENTER);
add(toolBar3,BorderLayout.CENTER);
add(toolBar4,BorderLayout.CENTER);
    for(int i=0;i<additionLines;i++){
        add(tile);
    }
}

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







    public void createTile(int lineLength,Color color){

        JLabel tile = new JLabel(" ", SwingConstants.CENTER);
        tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        tile.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        tile.setBackground(color);
        tile.setOpaque(true);
        add(tile);

boolean temp = false;
        for(int y=0;y<lineLength;y++){

            for(int x=0;x<lineLength;x++){
                if(labels5x5[x][y]==null){
                    labels5x5[x][y]=tile;
                    temp = true;
                    break;

                }


            }
            if(temp){
                break;
            }
        }








    }


public void printLabelsAdded( ){

    System.out.println(labels5x5.length * labels5x5[0].length);
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
        return labels5x5;
    }

public void mapRender()throws Exception{

            Thread.sleep(2000);

            revalidate();
            repaint();




}






}
