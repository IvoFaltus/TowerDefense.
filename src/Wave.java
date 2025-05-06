import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Wave {

    public Wave() {
    }

    Player p = new Player();
    Boolean pause = false;


    public void heyWait(boolean paused) throws Exception {

        pause = paused;


    }












    Map m = new Map();
    Tower TOWER = new Tower();
    Tower t = new Tower();

    public Wave(Map map) {
        this.m = map;
    }

    Knight KNIGHT = new Knight();

    Knight k = (Knight) KNIGHT.addKnights().get(0);
    Knight k2 = (Knight) KNIGHT.addKnights().get(1);

    public void moveEnemy(int x, int y, Knight knight) throws Exception {

        knight.setCurrentX(x);
        knight.setCurrentY(y);
        URL url = getClass().getResource("/resources/pathPhoto.png");
        Icon i = new ImageIcon(url);


        if (knight.getLastX() != -1 && knight.getLastY() != -1) {
            m.labels5x5[knight.getLastX()][knight.getLastY()].setIcon(i);
        }
        KNIGHT.placeEnemy(x, y, m.labels5x5, knight);
        knight.setLastXY(x, y);

        m.towerStrikeWatcher(knights,TowerIndexes);

    }
    ArrayList<Integer> TowerIndexes = new ArrayList<>();

    public void playesInput(ArrayList<JButton> buttons) throws Exception {


        buttons.get(0).addActionListener(e -> {


            try {
                heyWait(true);
                int[] timeLeft = {10};

                Timer[] t = new Timer[1];

                t[0] = new Timer(1000, e1 -> {
                    timeLeft[0]--;
                    buttons.getFirst().setText("stopped " + timeLeft[0]);

                    if (timeLeft[0] <= 0) {
                        t[0].stop();
                        buttons.getFirst().setText("Stop");
                        try {
                            heyWait(false);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                t[0].start(); // start the countdown

            } catch (Exception c) {

            }
        });//stop button

        buttons.get(1).addActionListener(e -> {
            try {

                p.OpenInventory(m.labels5x5,false, TowerIndexes);



            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });//place tower button

        buttons.get(2).addActionListener(e -> {}); //ask help button incomplete
        buttons.get(3).addActionListener(e -> {
            try {p.OpenInventory(m.labels5x5,true, TowerIndexes);} catch (Exception ex) {throw new RuntimeException(ex);}}); //remove tower button

    }

    ArrayList<Knight> knights = new ArrayList<>();
    public void enemyPath() throws Exception {

        knights.add(k);
        knights.add(k2);
        t.setTowerIcon();
        // k.enemyHealth(t,m.labels5x5,5,k);
        t.setTowerIcon();

        m.MapWindow5x5();
        playesInput(m.StopResumePlaceHelpRemove());


        moveEnemy(0, 0, k);
        m.mapRender(pause);
        moveEnemy(1, 0, k);
        m.mapRender(pause);
        moveEnemy(2, 0, k);

        //TOWER.placeTower(m.labels5x5,1);
        m.mapRender(pause);
//heyWait();

        m.mapRender(pause);
        moveEnemy(0, 0, k2);
        moveEnemy(2, 1, k);
        m.mapRender(pause);
        moveEnemy(2, 2, k);
        moveEnemy(1, 0, k2);
        m.mapRender(pause);
        moveEnemy(2, 0, k2);
        moveEnemy(2, 3, k);
        m.mapRender(pause);
        moveEnemy(2, 1, k2);
        moveEnemy(2, 4, k);
        m.mapRender(pause);
        moveEnemy(2, 2, k2);
        moveEnemy(3, 4, k);
        m.mapRender(pause);
        moveEnemy(2, 3, k2);
        moveEnemy(4, 4, k);
        m.mapRender(pause);
        moveEnemy(2, 4, k2);
        m.mapRender(pause);
        moveEnemy(3, 4, k2);
        m.mapRender(pause);
    }

    public void wave1() throws Exception {
        p.addTowers(2);
        enemyPath();
    }



}
