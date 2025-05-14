public class ProgramToggle {


    private Result gameResult;
    public enum Result{

        WON,LOST,RUNNING
    }


    public ProgramToggle() {
        this.gameResult = Result.RUNNING; // default when player is created
    }
    public Result getGameResult() {
        return gameResult;
    }
    public void setGameResult(Result gameResult) {
        this.gameResult = gameResult;
    }



    public void start(){

        this.setGameResult(Result.RUNNING);
        Map m = new Map();

        Menu m2 = new Menu(this);
       m2.mainMenu();
        new Thread(() -> {
            try {
                // Wait until game ends
                while (this.getGameResult() == Result.RUNNING) {
                    Thread.sleep(200); // check every 200ms


                    // Once game ends, show result
                    if (this.getGameResult() == Result.WON) {
                        this.setGameResult(Result.RUNNING);
                        System.out.println(" Player WON");
                        new Menu(this).youWon();
                        new Menu(this).winMenu();

                    } else if (this.getGameResult() == Result.LOST) {
                        this.setGameResult(Result.RUNNING);
                        System.out.println("Player LOST");
                        new Menu(this).youLost();
                        new Menu(this).lostMenu();

                    }
                }
                } catch(Exception e){
                    e.printStackTrace();
                }

        }).start();

    }



}
