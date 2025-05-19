import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tower {
    private boolean isActive = false;


private int x;
private int y;



    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public void getPosition(){

        System.out.print("["+this.x+",");
        System.out.print(this.y+"]");
        System.out.println();
    }







    public boolean isAt(int x, int y) {
        return this.x == y && this.y == y;
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
    private int durability = 2; // default





    public void reduceDurability() {
        durability--;
        if (durability <= 0) {
            this.setActive(false); // remove tower from map logic
        }
    }


    public void removeTower(ArrayList<Tower> inActiveTowers, JLabel[][] labels, int rows, int cols,
                            int[] addedTowers, ArrayList<Integer> towerIndexes) {

        AtomicBoolean hasClicked = new AtomicBoolean(false); // shared click flag

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalI = i;
                int finalJ = j;

                JLabel tile = labels[finalI][finalJ];

                if (tile != null) {
                    tile.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!hasClicked.get() && tile.getIcon() != null) {
                                // Remove the visual icon
                                tile.setIcon(null);
                                hasClicked.set(true);

                                // Add tower back to inventory
                                Tower t1 = new Tower();
                                t1.setActive(false);
                                t1.setTowerIcon();
                                addedTowers[0]++;
                                inActiveTowers.add(t1);

                                // Remove coordinates from towerIndexes
                                for (int k = 0; k < towerIndexes.size(); k += 2) {
                                    int x = towerIndexes.get(k);
                                    int y = towerIndexes.get(k + 1);
                                    if (x == finalI && y == finalJ) {
                                        towerIndexes.remove(k + 1);
                                        towerIndexes.remove(k);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }


    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    static Wave w = new Wave();

    public Tower() {
    }

    Map map = new Map();
    private ImageIcon towerIcon;
    private int lvl;

    public static void placeTower(JLabel[][] labels, int[] inActiveTowers, int rows, int cols, ArrayList<Integer> towerIndexes, ArrayList<Tower> towers)throws Exception{



        AtomicBoolean hasClicked = new AtomicBoolean(false); // shared click flag

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
                                if (!hasClicked.get() && tile.getIcon() == null) {
                                    Tower t = new Tower();
                                    t.setTowerIcon();
                                    tile.setIcon(t.getTowerIcon());

                                    t.setActive(true);
                                    hasClicked.set(true);

                                    if(hasClicked.get()==true){

                                        towerIndexes.add(finalI);
                                        towerIndexes.add(finalJ);
                                       // System.out.println("VEZ JE NA INDEXECH "+ finalI+ " "+finalJ);
                                       // System.out.println(towerIndexes);

                                    }
                                }

                            }
                        });

                    }
                }
            }
        }







    }





    public ImageIcon setTowerIcon( ){
        int lvl=1;
        //incomplete
        URL url = null;
        switch (durability){
            case 1:
                url = getClass().getResource("/resources/tower1.png");
                break;
            case 2:
                url = getClass().getResource("/resources/tower2.png");
                break;
            case 3:
                url = getClass().getResource("/resources/tower3.png");
                break;


        }
        this.towerIcon = new ImageIcon(url);
        Image temp = this.towerIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        this.towerIcon = new ImageIcon(temp);
        return towerIcon;
    }







    public ImageIcon setTowerIcon2( ){
        towerIcon=null;
        return towerIcon;
    }









    public ImageIcon getTowerIcon() {
        return towerIcon;
    }

    public void setTowerIcon(ImageIcon towerIcon) {
        this.towerIcon = towerIcon;
    }

    public Tower(int lvl) {
        this.lvl = lvl;
    }

    public boolean isWait() {
        return wait;
    }
}
