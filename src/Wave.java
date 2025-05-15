import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Wave {

    ArrayList<Tower> towers = new ArrayList<>();



    public Wave() {
    }

    int finalx =4;
int finaly=4;


    Player p = new Player();






    Boolean pause = false;


    public void heyWait(boolean paused) throws Exception {

        pause = paused;


    }




    public ArrayList<Tower> addTowers(int howManyTowers, int durabilty) {

        for (int i = 0; i < howManyTowers; i++) {
            Tower t = new Tower();
            t.setDurability(durabilty);// create new instance
            t.setTowerIcon();
            t.setActive(false);
            towers.add(t);
        }

        return towers;
    }


    Map m;

    private ProgramToggle toggle;

    public Wave(ProgramToggle toggle) {
        this.toggle = toggle;
m = new Map(toggle);
    }








    Tower TOWER = new Tower();
    Tower t = new Tower();

    public Wave(Map map) {
        this.m = map;
    }

    Knight KNIGHT = new Knight();

    Knight k = new Knight();
    Knight k2 = new Knight();
    Knight k3 = new Knight();

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

        m.towerStrikeWatcher(knights,TowerIndexes,towers,finalx,finaly);

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

                p.OpenInventory(m.labels5x5,false, TowerIndexes, towers);



            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });//place tower button

        buttons.get(2).addActionListener(e -> {}); //ask help button incomplete
        buttons.get(3).addActionListener(e -> {
            try {p.OpenInventory(m.labels5x5,true, TowerIndexes, towers);} catch (Exception ex) {throw new RuntimeException(ex);}}); //remove tower button

    }
    private void moveSafe(int x, int y, Knight knight) {
        try {

            moveEnemy(x, y, knight);
            knight.setPosition(x,y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ArrayList<Knight> knights = new ArrayList<>();
    public void enemyPath() throws Exception {
        k.knightPreset();
        knights.add(k);
        k2.knightPreset();
        knights.add(k2);
        k3.knightPreset();
        knights.add(k3);

        t.setTowerIcon();
        m.MapWindow5x5();
        playesInput(m.StopResumePlaceHelpRemove());

        ArrayList<Runnable> steps = new ArrayList<>();

        // k moves first
        steps.add(() -> moveSafe(0, 0, k));
        steps.add(() -> moveSafe(1, 0, k));
        steps.add(() -> moveSafe(2, 0, k));

        // k2 starts after k moves 3 steps
        steps.add(() -> {
            moveSafe(2, 1, k);
            moveSafe(0, 0, k2);
        });
        steps.add(() -> {
            moveSafe(2, 2, k);
            moveSafe(1, 0, k2);
        });
        steps.add(() -> {
            moveSafe(2, 3, k);
            moveSafe(2, 0, k2);
        });
        steps.add(() -> {
            moveSafe(2, 4, k);
            moveSafe(2, 1, k2);
        });

        // k3 starts after k2 moves 4 steps
        steps.add(() -> {
            moveSafe(3, 4, k);
            moveSafe(2, 2, k2);
            moveSafe(0, 0, k3);
        });
        steps.add(() -> {
            moveSafe(4, 4, k);
            moveSafe(2, 3, k2);
            moveSafe(1, 0, k3);
        });
        steps.add(() -> {
            moveSafe(3, 4, k2);
            moveSafe(2, 0, k3);
        });
        steps.add(() -> {
            moveSafe(4, 4, k2);
            moveSafe(2, 1, k3);
        });
        steps.add(() -> moveSafe(2, 2, k3));
        steps.add(() -> moveSafe(2, 3, k3));
        steps.add(() -> moveSafe(2, 4, k3));
        steps.add(() -> moveSafe(3, 4, k3));
        steps.add(() -> moveSafe(4, 4, k3));

        runStepsWithRender(steps, 0);
    }

    private void runStepsWithRender(ArrayList<Runnable> steps, int index) {
        if (index < steps.size()) {
            steps.get(index).run();
            m.mapRender(pause, () -> runStepsWithRender(steps, index + 1));
        }
    }
    public void wave1() throws Exception {


        addTowers(1,2);
        enemyPath();
    }



}
