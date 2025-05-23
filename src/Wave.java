import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Wave {

    ArrayList<Tower> towers = new ArrayList<>();

private int currentWave;

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

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

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
            labels[knight.getLastX()][knight.getLastY()].setIcon(i);
        }
        KNIGHT.placeEnemy(x, y,labels, knight);
        knight.setLastXY(x, y);

        m.towerStrikeWatcher(knights,TowerIndexes,towers,finalx,finaly);

    }
    ArrayList<Integer> TowerIndexes = new ArrayList<>();

    public void playesInput(ArrayList<JButton> buttons, JLabel[][] labels) throws Exception {


        buttons.get(0).addActionListener(e -> {


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
        });//stop button

        buttons.get(1).addActionListener(e -> {
            try {

                p.OpenInventory(labels,false, TowerIndexes, towers);



            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });//place tower button

        buttons.get(2).addActionListener(e -> {}); //ask help button incomplete
        buttons.get(3).addActionListener(e -> {
            try {p.OpenInventory(labels,true, TowerIndexes, towers);} catch (Exception ex) {throw new RuntimeException(ex);}}); //remove tower button

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
        }
    }




    //region enemy paths
    public void enemyPath() throws Exception {

        finalx = 4;
        finaly = 4;
        k.knightPreset();
        knights.add(k);
        k2.knightPreset();
        knights.add(k2);
        k3.knightPreset();
        knights.add(k3);

        t.setTowerIcon();
        m.MapWindow5x5(1);
        playesInput(m.StopResumePlaceHelpRemove(), m.labels5x5);

        ArrayList<Runnable> steps = new ArrayList<>();

        // k moves first
        steps.add(() -> { try { moveSafe(0, 0, k, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(1, 0, k, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 0, k, 1); } catch (Exception e) { e.printStackTrace(); } });

        // k2 starts after k moves 3 steps
        steps.add(() -> {
            try {
                moveSafe(2, 1, k, 1);
                moveSafe(0, 0, k2, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(2, 2, k, 1);
                moveSafe(1, 0, k2, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(2, 3, k, 1);
                moveSafe(2, 0, k2, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(2, 4, k, 1);
                moveSafe(2, 1, k2, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });

        // k3 starts after k2 moves 4 steps
        steps.add(() -> {
            try {
                moveSafe(3, 4, k, 1);
                moveSafe(2, 2, k2, 1);
                moveSafe(0, 0, k3, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(4, 4, k, 1);
                moveSafe(2, 3, k2, 1);
                moveSafe(1, 0, k3, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(3, 4, k2, 1);
                moveSafe(2, 0, k3, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(4, 4, k2, 1);
                moveSafe(2, 1, k3, 1);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> { try { moveSafe(2, 2, k3, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 3, k3, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 4, k3, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(3, 4, k3, 1); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 4, k3, 1); } catch (Exception e) { e.printStackTrace(); } });

        runStepsWithRender(steps, 0);
    }

    public void enemyPath2() throws Exception {
        finalx = 4;
        finaly = 4;
        m.MapWindow5x5(2);
        playesInput(m.StopResumePlaceHelpRemove(),m.labels5x5);

        k.knightPreset();
        knights.add(k);
        k2.knightPreset();
        k3.knightPreset();
        k4.knightPreset();

        for(Knight knight : knights){
            knight.setKnightIcon();
            knight.setHealth(100);
        }

        ArrayList<Runnable> steps = new ArrayList<>();

        steps.add(() -> { try { moveSafe(0, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(1, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(3, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 1, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 2, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 3, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(4, 4, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(3, 4, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 4, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 3, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 2, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 1, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(1, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(0, 0, k, 2); } catch (Exception e) { e.printStackTrace(); } });

        runStepsWithRender(steps, 0);
    }

    public void enemyPath3() throws Exception {
        m.MapWindow5x5(3);
        finalx = 6;
        finaly = 6;
        playesInput(m.StopResumePlaceHelpRemove(), m.labels7x7);

        k.knightPreset();
        knights.add(k);
        k2.knightPreset();
        knights.add(k2);
        k3.knightPreset();
        knights.add(k3);
        k4.knightPreset();
        knights.add(k4);

        for (Knight knight : knights) {
            knight.setKnightIcon();
            knight.setHealth(100);
        }

        ArrayList<Runnable> steps = new ArrayList<>();

        // Knight 1
        steps.add(() -> { try { moveSafe(0, 0, k, 3); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(1, 0, k, 3); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 0, k, 3); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 1, k, 3); } catch (Exception e) { e.printStackTrace(); } });
        steps.add(() -> { try { moveSafe(2, 2, k, 3); } catch (Exception e) { e.printStackTrace(); } });

        // Knight 2 starts after knight 1 moves 4 steps
        steps.add(() -> {
            try {
                moveSafe(2, 3, k, 3);
                moveSafe(0, 0, k2, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(2, 4, k, 3);
                moveSafe(1, 0, k2, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(3, 4, k, 3);
                moveSafe(2, 0 , k2, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });

        // Knight 3 starts
        steps.add(() -> {
            try {
                moveSafe(3, 5, k, 3);
                moveSafe(3, 0, k2, 3);
                moveSafe(0, 0, k3, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });

        steps.add(() -> {
            try {
                moveSafe(4, 5, k, 3);
                moveSafe(4, 0, k2, 3);
                moveSafe(1, 0, k3, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(4, 6, k, 3);
                moveSafe(5, 0, k2, 3);
                moveSafe(2, 0, k3, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });
        steps.add(() -> {
            try {
                moveSafe(5, 6, k, 3);
                moveSafe(5, 1, k2, 3);
                moveSafe(3, 0, k3, 3);
            } catch (Exception e) { e.printStackTrace(); }
        });


                steps.add(() -> {
                    try {
                        moveSafe(6, 6, k, 3);
                        moveSafe(5, 2, k2, 3);
                        moveSafe(4, 0, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );


        steps.add(() -> {
                    try {

                        moveSafe(5, 3, k2, 3);
                        moveSafe(5, 0, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );




        steps.add(() -> {
                    try {

                        moveSafe(6, 3, k2, 3);
                        moveSafe(5, 1, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );

        steps.add(() -> {
                    try {

                        moveSafe(6, 4, k2, 3);
                        moveSafe(5, 2, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );

        steps.add(() -> {
                    try {

                        moveSafe(6, 5, k2, 3);
                        moveSafe(5, 3, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );


        steps.add(() -> {
                    try {

                        moveSafe(6, 6, k2, 3);
                        moveSafe(6, 4, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );

        steps.add(() -> {
                    try {

                        moveSafe(6, 6, k2, 3);
                        moveSafe(6, 5, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );
        steps.add(() -> {
                    try {

                        moveSafe(6, 6, k2, 3);
                        moveSafe(6, 6, k3, 3);
                    } catch (Exception e) { e.printStackTrace(); }
                }


        );



        // Optionally continue for k4 or loop end
        runStepsWithRender(steps, 0);
    }


    public void enemyPath4() throws Exception {
        m.MapWindow5x5(4);
        finalx = 9;
        finaly = 9;
        playesInput(m.StopResumePlaceHelpRemove(), m.labels10x10);

        k.knightPreset(); knights.add(k);
        k2.knightPreset(); knights.add(k2);
        k3.knightPreset(); knights.add(k3);
        k4.knightPreset(); knights.add(k4);

        k.setKnightIcon(); k.setHealth(100);
        k2.setKnightIcon(); k2.setHealth(100);
        k3.setKnightIcon(); k3.setHealth(100);
        k4.setKnightIcon(); k4.setHealth(100);

        ArrayList<Runnable> steps = new ArrayList<>();

        // 1
        steps.add(() -> { try {
            moveSafe(1, 0, k, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 2
        steps.add(() -> { try {
            moveSafe(1, 1, k, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 3
        steps.add(() -> { try {
            moveSafe(1, 2, k, 4);
            moveSafe(1, 0, k2, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 4
        steps.add(() -> { try {
            moveSafe(1, 3, k, 4);
            moveSafe(1, 1, k2, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 5
        steps.add(() -> { try {
            moveSafe(2, 3, k, 4);
            moveSafe(1, 2, k2, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 6
        steps.add(() -> { try {
            moveSafe(3, 3, k, 4);
            moveSafe(1, 3, k2, 4);
            moveSafe(1, 0, k3, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 7
        steps.add(() -> { try {
            moveSafe(4, 3, k, 4);
            moveSafe(1, 4, k2, 4);
            moveSafe(1, 1, k3, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 8
        steps.add(() -> { try {
            moveSafe(5, 3, k, 4);
            moveSafe(1, 5, k2, 4);
            moveSafe(1, 2, k3, 4);
            moveSafe(1, 0, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 9
        steps.add(() -> { try {
            moveSafe(6, 3, k, 4);
            moveSafe(1, 6, k2, 4);
            moveSafe(1, 3, k3, 4);
            moveSafe(1, 1, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 10
        steps.add(() -> { try {
            moveSafe(7, 3, k, 4);
            moveSafe(2, 6, k2, 4);
            moveSafe(2, 3, k3, 4);
            moveSafe(1, 2, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 11
        steps.add(() -> { try {
            moveSafe(8, 3, k, 4);
            moveSafe(3, 6, k2, 4);
            moveSafe(3, 3, k3, 4);
            moveSafe(1, 3, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 12
        steps.add(() -> { try {
            moveSafe(9, 3, k, 4);
            moveSafe(4, 6, k2, 4);
            moveSafe(4, 3, k3, 4);
            moveSafe(1, 4, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 13
        steps.add(() -> { try {
            moveSafe(9, 4, k, 4);
            moveSafe(4, 7, k2, 4);
            moveSafe(4, 4, k3, 4);
            moveSafe(1, 5, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 14
        steps.add(() -> { try {
            moveSafe(9, 5, k, 4);
            moveSafe(4, 8, k2, 4);
            moveSafe(4, 5, k3, 4);
            moveSafe(1, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 15
        steps.add(() -> { try {
            moveSafe(9, 6, k, 4);
            moveSafe(4, 9, k2, 4);
            moveSafe(4, 6, k3, 4);
            moveSafe(2, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 16
        steps.add(() -> { try {
            moveSafe(9, 7, k, 4);
            moveSafe(5, 9, k2, 4);
            moveSafe(5, 6, k3, 4);
            moveSafe(3, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 17
        steps.add(() -> { try {
            moveSafe(9, 8, k, 4);
            moveSafe(6, 9, k2, 4);
            moveSafe(6, 6, k3, 4);
            moveSafe(4, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 18
        steps.add(() -> { try {
            moveSafe(9, 9, k, 4);
            moveSafe(7, 9, k2, 4);
            moveSafe(7, 6, k3, 4);
            moveSafe(5, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 19
        steps.add(() -> { try {
            moveSafe(8, 9, k2, 4);
            moveSafe(7, 7, k3, 4);
            moveSafe(6, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 20
        steps.add(() -> { try {
            moveSafe(9, 9, k2, 4);
            moveSafe(7, 8, k3, 4);
            moveSafe(7, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 21
        steps.add(() -> { try {
            moveSafe(7, 9, k3, 4);
            moveSafe(8, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 22
        steps.add(() -> { try {
            moveSafe(8, 9, k3, 4);
            moveSafe(9, 6, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 23
        steps.add(() -> { try {
            moveSafe(9, 9, k3, 4);
            moveSafe(9, 7, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 24
        steps.add(() -> { try {
            moveSafe(9, 8, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        // 25
        steps.add(() -> { try {
            moveSafe(9, 9, k4, 4);
        } catch (Exception e) { e.printStackTrace(); } });

        runStepsWithRender(steps, 0);
    }

    public void enemypath5() throws Exception {
        m.MapWindow5x5(5);}
    //endregion



    //region waves
    public void wave1() throws Exception {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        currentWave=1;


        addTowers(2,2);
        enemyPath();
        toggle.monitorGameResult();
    }
    public void wave2() throws Exception {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        currentWave=2;
        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath2();
        System.out.println("wave2 executed");

        toggle.monitorGameResult();

    }
    public void wave3()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        currentWave=3;
        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath3();
        System.out.println("wave3 executed");

        toggle.monitorGameResult();
    }
    public void wave4()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        currentWave=4;
        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemyPath4();
        System.out.println("wave4 executed");

        toggle.monitorGameResult();
    }
    public void wave5()throws Exception{
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        currentWave=5;
        knights.clear();
        towers.clear();
        addTowers(3,2);
        enemypath5();
        System.out.println("wave4 executed");

        toggle.monitorGameResult();
    }

    //endregion










}
