import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class  Wave {

    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Point> knightPath = new ArrayList<>();

    AtomicBoolean towerIsBeingPlaced = new AtomicBoolean(false);
    AtomicBoolean atleastOneTowerIsPlaced = new AtomicBoolean(false);

    public Wave(ProgramToggle toggle, int enemySpeed) {
        this.toggle = toggle;
        this.enemySpeed = enemySpeed;
    }

    public Wave() {
        m = new Map(toggle, enemySpeed);
    }

    int finalx;
int finaly;


    Player p = new Player();


private ArrayList<Boolean> waves;



    Boolean pause = false;


    public void heyWait(boolean paused) throws Exception {

        pause = paused;


    }




    public ArrayList<Tower> addTowers(int howManyTowers, int durabilty){

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
private int enemySpeed;
    public Wave(ProgramToggle toggle) {
        this.toggle = toggle;
m = new Map(toggle, enemySpeed);
    }


    public Wave(ProgramToggle toggle, int enemySpeed, ArrayList<Boolean> waves) {
        this.toggle = toggle;
        this.enemySpeed = enemySpeed;
        this.waves = waves;
        m = new Map(toggle, enemySpeed);

    }

    public Wave(ProgramToggle toggle, ArrayList<Boolean> waves) {
        this.toggle = toggle;
        this.waves = waves;
        m = new Map(toggle, enemySpeed);
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
    Knight k4 = new Knight();
    Knight k5 = new Knight();
    Knight k6 = new Knight();
    Knight k7 = new Knight();



    public ArrayList<Boolean> getWaves() {
        return waves;
    }

    public void setWaves(ArrayList<Boolean> waves) {
        this.waves = waves;
    }

    public void moveEnemy(int x, int y, Knight knight, JLabel[][] labels) throws Exception {

        knight.setCurrentX(x);
        knight.setCurrentY(y);
        URL url = getClass().getResource("/resources/pathPhoto.png");
        Icon i = new ImageIcon(url);


        if (knight.getLastX() != -1 && knight.getLastY() != -1) {
           labels[knight.getLastX()][knight.getLastY()].setIcon(null);
        }
        KNIGHT.placeEnemy(x, y,labels, knight);
        knight.setLastXY(x, y);

    m.towerStrikeWatcher(knights, TowerIndexes, towers, finalx, finaly, labels);


    }
    ArrayList<Integer> TowerIndexes = new ArrayList<>();

    public void playesInput(ArrayList<JButton> buttons, JLabel[][] labels, ArrayList<Point> knightPathh, int lineLength) throws Exception {

        // timer to monitor tower placement state
        Timer monitorTowerPlacing = new Timer(100, e -> {
            boolean placing = towerIsBeingPlaced.get();
            for (JButton button : buttons) {
                button.setEnabled(!placing);
            }
            if(!atleastOneTowerIsPlaced.get()){

                buttons.get(3).setEnabled(false);
            }
        });
        monitorTowerPlacing.start();

        buttons.get(0).addActionListener(e -> {
            towerIsBeingPlaced.set(true);
            try {
                buttons.get(0).setEnabled(false);
                heyWait(true);
                int[] timeLeft = {15};

                Timer[] t = new Timer[1];
                t[0] = new Timer(1000, e1 -> {
                    timeLeft[0]--;
                    buttons.getFirst().setText("stopped " + timeLeft[0]);

                    if (timeLeft[0] <= 0) {
                        t[0].stop();
                        towerIsBeingPlaced.set(false);
                        buttons.getFirst().setText("Stop");
                        buttons.get(0).setEnabled(true);
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
        }); // stop button

        buttons.get(1).addActionListener(e -> {
            try {
                p.OpenInventory(labels, false, TowerIndexes, towers, knightPathh, towerIsBeingPlaced, lineLength,atleastOneTowerIsPlaced);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }); // place tower button

        buttons.get(2).addActionListener(e -> {}); // ask help button incomplete

        buttons.get(3).addActionListener(e -> {
            try {
                p.OpenInventory(labels, true, TowerIndexes, towers, knightPathh, towerIsBeingPlaced, lineLength,atleastOneTowerIsPlaced);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }); // remove tower button
    }

    private void moveSafe(int x, int y, Knight knight,int wave) throws Exception {
        try {

            switch (wave) {
                case 1:
                    moveEnemy(x, y, knight, m.labels5x5);
                    break;
                case 2:
                    moveEnemy(x, y, knight, m.labels5x5);
                    break;
                case 3:
                    moveEnemy(x, y, knight, m.labels7x7);

                    break;
                case 4:
                    moveEnemy(x, y, knight, m.labels10x10);
                    break;
                case 5:
                    moveEnemy(x, y, knight, m.labels10x10);
                    break;
                case 6:
                    moveEnemy(x, y, knight, m.labels10x10);
                    break;
                default:
                    moveEnemy(x, y, knight, m.labels5x5);
                    break;

            }







            knight.setPosition(x,y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ArrayList<Knight> knights = new ArrayList<>();

    private void runStepsWithRender(ArrayList<Runnable> steps, int index) {
        if (index < steps.size()) {

            steps.get(index).run();
            m.mapRender(pause, () -> runStepsWithRender(steps, index + 1));
        }else{
            System.out.println("probleem");
        }
    }




    //region enemy paths
    public void enemyPath() throws Exception {
        finalx = 4;
        finaly = 4;

        k.knightPreset(); knights.add(k);
        k2.knightPreset(); knights.add(k2);
        k3.knightPreset(); knights.add(k3);

        t.setTowerIcon();

        knightPath = m.MapWindow5x5(1);
        playesInput(m.StopResumePlaceHelpRemove(), m.labels5x5, knightPath, 5);

        ArrayList<Runnable> steps = new ArrayList<>();

        // k path
        String[] pathK = {"00", "10", "20", "21", "22", "23", "24", "34", "44"};
        addKnightPathSteps(steps, pathK, k, 1, 0);

        // k2 path, starting after 3 steps of k (step index 3)
        String[] pathK2 = {"00", "10", "20", "21", "22", "23", "34", "44"};
        addKnightPathSteps(steps, pathK2, k2, 1, 3);

        // k3 path, starting after 4 steps of k2 (step index 3 + 4 = 7)
        String[] pathK3 = {"00", "10", "20", "21", "22", "23", "24", "34", "44"};
        addKnightPathSteps(steps, pathK3, k3, 1, 7);

        runStepsWithRender(steps, 0);
    }


    public void enemyPath2() throws Exception {
        finalx = 4;
        finaly = 4;

        knightPath = m.MapWindow5x5(2);
        playesInput(m.StopResumePlaceHelpRemove(), m.labels5x5, knightPath, 5);

        k.knightPreset(); knights.add(k);
        k2.knightPreset(); //knights.add(k2);
        k3.knightPreset(); //knights.add(k3);
        k4.knightPreset(); //knights.add(k4);

        for (Knight knight : knights) {
            knight.setKnightIcon();
            knight.setHealth(100);
        }

        ArrayList<Runnable> steps = new ArrayList<>();

        // Define the path for k (knight 1)
        String[] pathK = {
                "00", "10", "20", "30", "40",
                "41", "42", "43", "44",
                "34", "24", "23", "22", "21",
                "20", "10", "00"
        };

        // Add path steps for k (wave 2)
        addKnightPathSteps(steps, pathK, k, 2, 0);

        runStepsWithRender(steps, 0);
    }


    public void enemyPath3() throws Exception {
        knightPath = m.MapWindow5x5(3);
        finalx = 6;
        finaly = 6;
        playesInput(m.StopResumePlaceHelpRemove(), m.labels7x7, knightPath, 7);

        k.knightPreset();  knights.add(k);
        k2.knightPreset(); knights.add(k2);
        k3.knightPreset(); knights.add(k3);
        k4.knightPreset(); // not added to list (optional)

        for (Knight knight : knights) {
            knight.setKnightIcon();
            knight.setHealth(100);
        }

        ArrayList<Runnable> steps = new ArrayList<>();

        // Define paths as arrays of string coordinates "xy"
        String[] pathK  = {"00", "10", "20", "21", "22", "23", "24", "34", "35", "45", "46", "56", "66"};
        String[] pathK2 = {"00", "10", "20", "30", "40", "50", "51", "52", "53", "63", "64", "65", "66"};
        String[] pathK3 = {"00", "10", "20", "30", "40", "50", "51", "52", "53", "64", "65", "66"};

        // Stagger knights: k2 starts after 4 steps of k, k3 after 8 steps of k2
        addKnightPathSteps(steps, pathK,  k,  3, 0);
        addKnightPathSteps(steps, pathK2, k2, 3, 4);
        addKnightPathSteps(steps, pathK3, k3, 3, 8);

        runStepsWithRender(steps, 0);
    }



    public void enemyPath4() throws Exception {
        knightPath = m.MapWindow5x5(4);
        finalx = 9;
        finaly = 9;
        playesInput(m.StopResumePlaceHelpRemove(), m.labels10x10, knightPath, 10);

        k.knightPreset();  knights.add(k);
        k2.knightPreset(); knights.add(k2);
        k3.knightPreset(); knights.add(k3);
        k4.knightPreset(); knights.add(k4);

        k.setKnightIcon();  k.setHealth(100);
        k2.setKnightIcon(); k2.setHealth(100);
        k3.setKnightIcon(); k3.setHealth(100);
        k4.setKnightIcon(); k4.setHealth(100);

        ArrayList<Runnable> steps = new ArrayList<>();

        // Define each knight's path as strings in format "xy"
        String[] pathK  =  {"10", "11", "12", "13", "23", "33", "43", "53", "63", "73", "83", "93", "94", "95", "96", "97", "98", "99"};
       String[] pathK2 =  {"10", "11", "12", "13", "14", "15", "16", "26", "36", "46", "47", "48", "49", "59", "69", "79", "89", "99"};
        String[] pathK3 =  {"10", "11", "12", "13", "23", "33", "43", "44", "45", "46", "56", "66", "76", "77", "78", "79", "89", "99"};
        String[] pathK4 =  {"10", "11", "12", "13", "14", "15", "16", "26", "36", "46", "56", "66", "76", "86", "96", "97", "98", "99"};

        // Add knight paths with staggered delays
        addKnightPathSteps(steps, pathK,  k,  4, 0);
        addKnightPathSteps(steps, pathK2, k2, 4, 3);
        addKnightPathSteps(steps, pathK3, k3, 4, 6);
        addKnightPathSteps(steps, pathK4, k4, 4, 9);

        runStepsWithRender(steps, 0);
    }



    public void enemypath5() throws Exception {
        knightPath = m.MapWindow5x5(5);
        finalx = 9;
        finaly = 9;
        playesInput(m.StopResumePlaceHelpRemove(), m.labels10x10, knightPath, 10);

        // Prepare knights
        k.knightPreset(); knights.add(k);
        k2.knightPreset(); knights.add(k2);
        k3.knightPreset(); knights.add(k3);
        k4.knightPreset(); knights.add(k4);
        k5.knightPreset(); knights.add(k5);
        k6.knightPreset(); knights.add(k6);

        for (Knight knight : knights) {
            knight.setKnightIcon();
            knight.setHealth(100);
        }

        ArrayList<Runnable> steps = new ArrayList<>();

        // Define paths (as strings: "xy" → x=row, y=column)
        String[] pathK  =  {"40","41","51","61","71","81","91","92","93","94","95","96","97","98","99"};
        String[] pathK2 =  {"40","41","31","21","11","01","02","03","13","23","33","43","53","63","73","74","75","76","77","78","79","89","99"};
        String[] pathK3 =  {"40","41","31","21","11","01","02","03","04","05","15","25","35","45","55","56","57","58","59","69","79","89","99"};
        String[] pathK4 =  {"40","41","31","21","11","01","02","03","04","05","06","07","17","27","37","38","39","49","59","69","79","89","99"};
        String[] pathK5 =  {"40","41","31","21","11","01","02","03","13","23","33","43","53","63","73","74","75","76","77","78","79","89","99"};
        String[] pathK6 =  {"40","41","31","21","11","01","02","03","13","23","33","43","53","63","73","74","75","76","77","78","79","89","99"};

        // Add knights with staggered start steps
       addKnightPathSteps(steps, pathK,  k,  5,  0);  // starts at step 0
        addKnightPathSteps(steps, pathK2, k2, 5,  3);  // starts at step 3
        addKnightPathSteps(steps, pathK3, k3, 5,  5);  // starts at step 5
        addKnightPathSteps(steps, pathK4, k4, 5, 10);  // starts at step 10
        addKnightPathSteps(steps, pathK5, k5, 5, 11);  // starts at step 11
        addKnightPathSteps(steps, pathK6, k6, 5, 13);  // starts at step 13

        runStepsWithRender(steps, 0);
    }
    public void enemyPath6() throws Exception {
        finalx = 9;
        finaly = 9;

        k.knightPreset();     knights.add(k);
        k2.knightPreset();    knights.add(k2);
        k3.knightPreset();    knights.add(k3);
        k4.knightPreset();    knights.add(k4);
        k5.knightPreset();    knights.add(k5);
        k6.knightPreset();    knights.add(k6);
        k7.knightPreset();    knights.add(k7);

        knightPath = m.MapWindow5x5(6);
        playesInput(m.StopResumePlaceHelpRemove(), m.labels10x10, knightPath, 10);

        ArrayList<Runnable> steps = new ArrayList<>();

        String[] pathK  = {"10","11","21","31","41","51","61","71","81","91","92","93","94","95","96","97","98","99"};
        String[] pathK2 = {"10","11","12","02","03","04","14","15","16","06","07","08","18","19","29","39","49","59","69","79","89","99"};
        String[] pathK3 = {"10","11","21","31","32","33","34","44","45","46","47","57","67","77","87","97","98","99"};
        String[] pathK4 = {"10","11","21","31","41","51","61","62","63","64","65","66","67","77","87","97","98","99"};
        String[] pathK5 = {"10","11","21","31","41","51","61","71","81","91","92","93","94","95","96","97","98","99"};
        String[] pathK6 = {"10","11","12","02","03","04","14","15","16","06","07","08","18","19","29","39","49","59","69","79","89","99"};
        String[] pathK7 = {"10","11","21","31","32","33","34","44","45","46","47","57","67","77","87","97","98","99"};

        addKnightPathSteps(steps, pathK,  k,  6,  0);
        addKnightPathSteps(steps, pathK2, k2, 6,  2);
        addKnightPathSteps(steps, pathK3, k3, 6,  4);
        addKnightPathSteps(steps, pathK4, k4, 6,  6);
        addKnightPathSteps(steps, pathK5, k5, 6,  8);
        addKnightPathSteps(steps, pathK6, k6, 6, 12);
        addKnightPathSteps(steps, pathK7, k7, 6, 14);

        runStepsWithRender(steps, 0);
    }

    //endregion
    private void addKnightPathSteps(ArrayList<Runnable> steps, String[] path, Knight knight, int wave, int startStep) {
        for (int i = 0; i < path.length; i++) {
            final int stepIndex = startStep + i;
            final int x = Character.getNumericValue(path[i].charAt(0));
            final int y = Character.getNumericValue(path[i].charAt(1));

            // Ensure steps list is long enough
            while (steps.size() <= stepIndex) {
                steps.add(() -> {}); // placeholder to avoid IndexOutOfBounds
            }

            // Safely combine with previous runnable
            final Runnable previous = steps.get(stepIndex);
            steps.set(stepIndex, () -> {
                try {
                    previous.run(); // run existing actions
                    moveSafe(x, y, knight, wave); // add this knight's move

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }



    //region waves
    public void wave1() throws Exception {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);


        addTowers(2,2);
        enemyPath();
        toggle.monitorGameResult(1);
    }
    public void wave2() throws Exception {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);

        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath2();
        System.out.println("wave2 executed");

        toggle.monitorGameResult(2);

    }
    public void wave3()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);

        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath3();
        System.out.println("wave3 executed");

        toggle.monitorGameResult(3);
    }
    public void wave4()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);

        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath4();
        System.out.println("wave4 executed");

        toggle.monitorGameResult(4);
    }
    public void wave5()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);

        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemypath5();
        System.out.println("wave5 executed");

        toggle.monitorGameResult(5);
    }
public void wave6()throws Exception{
    toggle.setGameResult(ProgramToggle.Result.RUNNING);

    knights.clear();
    towers.clear();
    addTowers(4,2);
    enemyPath6();
    System.out.println("wave 6 executed");

    toggle.monitorGameResult(6);
}
    //endregion










}
