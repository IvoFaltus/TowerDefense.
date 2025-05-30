import java.util.ArrayList;

/**
 * Manages the current state of the game, including the active wave, game result,
 * and completed levels. Also handles transitions between wave states and menu displays.
 */
public class ProgramToggle {

    private ArrayList<Boolean> waves = new ArrayList<>();
    private Result gameResult;
    private int currentWave = 3;
    private int roundplayed = 0;
    private ArrayList<Integer> completedWaves = new ArrayList<>();
    private boolean wave1Passed;
    private boolean wave2Passed;
    private boolean wave3Passed;
    private boolean isMonitoring = false;


    public ProgramToggle(int currentWave) {
        this.currentWave = currentWave;
    }

    /**
     * Default constructor. Initializes the game result to RUNNING.
     */
    public ProgramToggle() {
        this.gameResult = Result.RUNNING;
    }


    /**
     * Sets the wave completion flags.
     *
     * @param waves list of Boolean values indicating completed waves
     */
    public void setWaves(ArrayList<Boolean> waves) {
        this.waves = waves;
    }

    /**
     * Returns the index of the currently selected wave.
     *
     * @return the current wave number
     */
    public int getCurrentWave() {
        return currentWave;
    }

    /**
     * Sets the current wave to be played.
     *
     * @param currentWave the wave number to set as current
     */
    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    /**
     * Returns how many times the game has been played.
     *
     * @return number of rounds played
     */
    public int getRoundplayed() {
        return roundplayed;
    }

    /**
     * Sets how many rounds the game has been played.
     *
     * @param roundplayed number of completed play rounds
     */
    public void setRoundplayed(int roundplayed) {
        this.roundplayed = roundplayed;
    }

    /**
     * Returns a list of completed wave numbers.
     *
     * @return list of integers representing finished waves
     */
    public ArrayList<Integer> getCompletedWaves() {
        return completedWaves;
    }



    /**
     * Sets the game result to a new state.
     *
     * @param gameResult the result to set (WON, LOST, or RUNNING)
     */
    public void setGameResult(Result gameResult) {
        this.gameResult = gameResult;
    }

    /**
     * Starts the game by launching the menu and initializing music and wave settings.
     */
    public void start() {
        Wave w = new Wave(this);
        Audio a = new Audio();
        int enemySpeed = 1000;
        this.setGameResult(Result.RUNNING);

        Menu m2 = new Menu(this, a.getBackgroundmusic(), enemySpeed, waves, currentWave, completedWaves, roundplayed);
        m2.mainMenu();
    }

    /**
     * Monitors the current game result in a background thread.
     * When a result is reached (WON/LOST), it updates game state and shows the corresponding menu.
     *
     * @param wave the wave being monitored
     */
    public synchronized void monitorGameResult(int wave) {
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

                        if (result == Result.WON && wave == 6) {
                            new Menu(this).wonGame();
                        } else if (result == Result.WON) {
                            completedWaves.add(wave);
                            for (int a : completedWaves) {
                                System.out.println(a);
                            }
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

    /**
     * Enum representing the possible states of the game.
     */
    public enum Result {
        WON, LOST, RUNNING
    }
}
