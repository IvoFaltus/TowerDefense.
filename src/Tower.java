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

    public Tower(boolean isActive) {
        this.isActive = isActive;
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




public void removeTower(ArrayList<Tower> inActiveTowers, JLabel[][] labels, int rows, int cols, int[] addedTowers){











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
                            if (!hasClicked.get() && tile.getIcon() !=null) {
                                Tower t1 = new Tower();
                                t1.setActive(false);
                                t1.setTowerIcon();
                                addedTowers[0]++;
                                inActiveTowers.add(t1);
                                tile.setIcon(null);

                                hasClicked.set(true);
                            }
                        }
                    });
                }
            }
        }









}






    static Wave w = new Wave();

    public Tower() {
    }

    Map map = new Map();
    private ImageIcon towerIcon;
private int lvl;

    public static void placeTower(JLabel[][] labels, int inActiveTowers, int rows, int cols, ArrayList<Integer> towerIndexes)throws Exception{



        AtomicBoolean hasClicked = new AtomicBoolean(false); // shared click flag

        if (inActiveTowers != 0) {
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
                                        System.out.println("VEZ JE NA INDEXECH "+ finalI+ " "+finalJ);
                                        System.out.println(towerIndexes);

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
    switch (lvl){
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
