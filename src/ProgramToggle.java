import java.util.ArrayList;

public class ProgramToggle {
    public ArrayList<Boolean> getWaves() {
        return waves;
    }

    public ProgramToggle(int currentWave) {
        this.currentWave = currentWave;
    }

    public void setWaves(ArrayList<Boolean> waves) {
        this.waves = waves;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    ArrayList<Boolean> waves= new ArrayList<>();
    private Result gameResult;

    public enum Result {

        WON, LOST, RUNNING
    }
private boolean wave1Passed;
private boolean wave2Passed;
private boolean wave3Passed;

    public ProgramToggle() {
        this.gameResult = Result.RUNNING; // default when player is created
    }

    public Result getGameResult() {
        return gameResult;
    }

    public void setGameResult(Result gameResult) {
        this.gameResult = gameResult;
    }

private int currentWave=3;


    public void start() {


        Audio a = new Audio();
//a.playMusic();
        int enemySpeed = 1000;
        this.setGameResult(Result.RUNNING);

        Menu m2 = new Menu(this, a.getBackgroundmusic(), enemySpeed,waves,currentWave);
        m2.mainMenu();


    }

    private boolean isMonitoring = false;

    public synchronized void monitorGameResult() {
        System.out.println("monitoring executed");
        if (isMonitoring) return;
        isMonitoring = true;

        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(200);

                    if (gameResult == Result.WON || gameResult == Result.LOST) {
                        Result result = gameResult;
                        gameResult = Result.RUNNING;
                        isMonitoring = false;

                        if (result == Result.WON) {
                            new Menu(this).youWon();
                            new Menu(this).winMenu();

                        } else {
                            new Menu(this).youLost();
                            new Menu(this).lostMenu();

                        }
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



}
