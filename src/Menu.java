import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JFrame {
    private ProgramToggle toggle;
    private Clip music;
private int currentWave =1;


    public Menu(ProgramToggle toggle, Clip music ) {
        this.toggle = toggle;
        this.music = music;

    }
    private int enemySpeed;
private ArrayList<Boolean> waves;


    private ArrayList<JButton> levelButtons = new ArrayList<>();

    // Dummy tracker (replace with actual save/progress logic)
    public int getHighestUnlockedLevel() {
        return currentWave - 1; // or return 0 if nothing is completed
    }

    // Launch wave from level select
    public void startWave(int wave) {
        try {

            Wave w = new Wave(toggle,waves);
            switch (wave) {
                case 1:

                    w.wave1();

                    break;
                case 2 : ;w.wave2();

                    break;
                case 3:w.wave3();
                break;
                case 4 : w.wave4();

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("not implemented yet");
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Level select screen
    public void openLevelMap() {
        JFrame frame = new JFrame("Level Selection");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Color brown = new Color(194, 155, 99);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                for (int i = 0; i < levelButtons.size() - 1; i++) {
                    JButton b1 = levelButtons.get(i);
                    JButton b2 = levelButtons.get(i + 1);
                    int x1 = b1.getX() + b1.getWidth() / 2;
                    int y1 = b1.getY() + b1.getHeight() / 2;
                    int x2 = b2.getX() + b2.getWidth() / 2;
                    int y2 = b2.getY() + b2.getHeight() / 2;
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        };
        panel.setLayout(null);
        panel.setBackground(brown);
        frame.getContentPane().setBackground(brown);
        frame.add(panel);

        levelButtons.clear();

        int[][] positions = {
                {50, 50}, {200, 100}, {350, 50}, {450, 150}, {300, 200}, {150, 250}
        };

        for (int i = 0; i < positions.length; i++) {
            JButton levelBtn = new JButton("Wave " + (i + 1));
            levelBtn.setFocusPainted(false);
            levelBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            levelBtn.setBackground(new Color(150, 150, 160));
            levelBtn.setBounds(positions[i][0], positions[i][1], 80, 40);

            final int waveNumber = i + 1;
            levelBtn.addActionListener(e -> {
                frame.dispose();
                startWave(waveNumber);
            });

            panel.add(levelBtn);
            levelButtons.add(levelBtn);
        }

        frame.setVisible(true);
    }
















    public Menu(ProgramToggle toggle, Clip music, int enemySpeed, ArrayList<Boolean> waves) {
        this.toggle = toggle;
        this.music = music;
        this.enemySpeed = enemySpeed;
        this.waves = waves;
    }
    public Menu(ProgramToggle toggle) {
        this.toggle = toggle;

    }

    public Menu(){}
    // Get screen resolution
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public void buttonPreset(JButton button) {
        Dimension buttonSize = new Dimension(200, 50);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(150, 150, 160));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setFont(new Font("Impact", Font.PLAIN, 18));
        button.setFocusPainted(false);


    }


    public void backButtonPreset(JButton button) {
        Dimension buttonSize = new Dimension(130, 40);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(150, 150, 160));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setFont(new Font("Impact", Font.PLAIN, 18));
        button.setFocusPainted(false);


    }

    public void lostMenu() {
        Wave w = new Wave(toggle); // or pass this in if needed
        Menu frame = new Menu(toggle);
        frame.setTitle("Game Over");

        JButton playAgain = new JButton("Play Again");
        JButton mainMenu = new JButton("Main Menu");
        JButton options = new JButton("Options");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Show background if needed

        for (JButton button : new JButton[]{playAgain, mainMenu, options}) {
            buttonPreset(button);
        }

        panel.add(getSpacer(10));
        panel.add(playAgain);
        panel.add(getSpacer(10));
        panel.add(mainMenu);
        panel.add(getSpacer(10));
        panel.add(options);


        frame.setVisible(true);
        frame.add(background(panel));
        frame.setDesign(300, 300); // Adjust size as needed

        playAgain.addActionListener(e -> {
            frame.dispose();
            try {
                w.wave1();
                toggle.monitorGameResult();// Restart wave or game logic
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        mainMenu.addActionListener(e -> {
            frame.dispose();
            mainMenu(); // Return to main menu
        });

        options.addActionListener(e -> {
            frame.dispose();
           options(2); // Navigate to options/settings screen
        });
    }

    public void winMenu() {
        Wave w = new Wave(toggle, enemySpeed,waves); // or pass existing instance if needed
        Menu frame = new Menu(toggle);
        frame.setTitle("You Win!");

        JButton nextLevel = new JButton("Next Level");
        JButton mainMenu = new JButton("Main Menu");
        JButton options = new JButton("Options");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // keep background visible

        for (JButton button : new JButton[]{nextLevel, mainMenu, options}) {
            buttonPreset(button);
        }

        panel.add(getSpacer(10));
        panel.add(nextLevel);
        panel.add(getSpacer(10));
        panel.add(mainMenu);
        panel.add(getSpacer(10));
        panel.add(options);
        JButton levelSelect = new JButton("Level Select");
        buttonPreset(levelSelect);
        panel.add(getSpacer(10));
        panel.add(levelSelect);
        levelSelect.addActionListener(e -> {
            frame.dispose();
            openLevelMap();
        });

        frame.setVisible(true);
        frame.add(background(panel));
        frame.setDesign(300, 300); // size of the win menu

        nextLevel.addActionListener(e -> {
            currentWave++;
            frame.dispose();
            try {


               switch (currentWave) {
                   case 1:
                       System.out.println("undefined");
                       break;
                       case 2:
                           w.wave2();
                           toggle.monitorGameResult();
                           break;
                           case 3:
               }




            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        mainMenu.addActionListener(e -> {
            frame.dispose();
            mainMenu(); // Navigate back to main menu
            toggle.monitorGameResult();
        });

        options.addActionListener(e -> {
            frame.dispose();
            options(1); // Implement your own options screen method
        });
    }




    public void staticInfo() {

    }

    public void dynamicInfo() {
    }

    public void mainInfo() {
        Menu frame = new Menu(toggle);
        JLabel text = new JLabel("<html>" +
                "<div style='text-align: center;'>" +
                "<p>You can choose from 2 modes: <b>Dynamic</b> and <b>Static</b>.</p>" +

                "<p><b>Dynamic mode</b>: Enemies <b>move independently</b> and give you <i>no time to overthink</i>.</p>" +

                "<p><b>To complete the game, you must play Dynamic mode.</b></p>" +

                "<p><b>Static mode</b>: Ideal for <i>practice and learning mechanics</i>.</p>" +

                "<br>" +

                "<p>Your goal: <b>Place towers</b> to stop enemies from reaching the finish.</p>" +
                "<p>Towers deal damage when <b>placed next to enemies</b> (non-diagonally).</p>" +
                "</div></html>");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        text.setFont(new Font("TimesRoman", Font.PLAIN, 18));

        JPanel jp = new JPanel();

//jp.setOpaque(false);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.setOpaque(false);
        jp.add(getSpacer(20));
        jp.add(text);
        jp.add(getSpacer(20));

        JButton jb = new JButton("Back");
        backButtonPreset(jb);

        jp.add(jb);
        frame.add(background(jp));
        frame.setDesign(800, 300);
        frame.setVisible(true);
        jb.addActionListener(e -> {
            mainMenu();
            frame.dispose();
        });
    }

    public void options(int whichMenu) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        Menu frame = new Menu(toggle);

        // === Volume label ===
        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeLabel.setFont(new Font("Impact", Font.PLAIN, 18));

        // === Slider and value label side-by-side ===
        JSlider volume = new JSlider(0, 100, 50); // Default at 50%
        volume.setBackground(new Color(194, 155, 99));

        JLabel volumeValues = new JLabel(volume.getValue() + "%");

        volume.addChangeListener(e -> {
            int sliderValue = volume.getValue();
            volumeValues.setText(sliderValue + "%");

            Clip clip = music;
            if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float min = gainControl.getMinimum(); // e.g. -80.0f
                float max = gainControl.getMaximum(); // e.g. 0.0f
                float dB = min + (sliderValue / 100.0f) * (max - min);
                gainControl.setValue(dB);
            }
        });

        JPanel volumePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        volumePanel.setOpaque(false);
        volumePanel.add(volume);
        volumePanel.add(volumeValues);

        // === Difficulty dropdown ===
        JLabel diffLabel = new JLabel("Difficulty:");
        diffLabel.setFont(new Font("Impact", Font.PLAIN, 18));

        String[] options = {"Easy", "Medium", "Hard", "Extreme"};
        JComboBox<String> dropOptions = new JComboBox<>(options);
        dropOptions.setSelectedIndex(0);

        dropOptions.addActionListener(e -> {
            switch (dropOptions.getSelectedIndex()){
                case 0:
                    enemySpeed = 2000;
                    break;
                case 1:
                    enemySpeed = 1000;
                    break;
                case 2:
                    enemySpeed = 500;
                    break;
                case 3:
                    enemySpeed = 300;
                    break;
            }



        });




        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        difficultyPanel.setOpaque(false);
        difficultyPanel.add(diffLabel);
        difficultyPanel.add(dropOptions);

        // === Back button ===
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButtonPreset(backButton);
        backButton.addActionListener(ev -> {
            frame.dispose();
            switch (whichMenu) {
                case 0 -> mainMenu();
                case 1 -> winMenu();
                case 2 -> lostMenu();
                default -> mainMenu(); // fallback
            }
        });

        panel.add(getSpacer(10));
        panel.add(volumeLabel);
        panel.add(getSpacer(5));
        panel.add(volumePanel);
        panel.add(getSpacer(10));
        panel.add(difficultyPanel);
        panel.add(getSpacer(10));
        panel.add(backButton);

        frame.add(background(panel));
        frame.setDesign(300, 300);
        frame.setVisible(true);
    }






public void countDown(){

        Menu frame = new Menu(toggle);
        JLabel countdownLabel = new JLabel();
        int[] numbers = {5};
        JPanel countdownPanel = new JPanel();
        countdownPanel.setLayout(new BoxLayout(countdownPanel, BoxLayout.Y_AXIS));
        countdownPanel.setOpaque(false);
        countdownPanel.add(getSpacer(20));
        countdownPanel.add(getSpacer(20));
        countdownPanel.add(getSpacer(20));

    countdownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countdownPanel.add(countdownLabel);

        Timer t = new Timer(1000,e->{
            countdownLabel.setText(String.valueOf(numbers[0]));
            countdownLabel.setFont(new Font("Impact", Font.PLAIN, 60));
            numbers[0]--;
            countdownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            if(numbers[0]==-1){
                countdownLabel.setText("START!");
            }

            if(numbers[0]<-1){
                ((Timer)e.getSource()).stop();
                frame.dispose();
            }
        });
        t.start();





        frame.add(background(countdownPanel));


        frame.setDesign(300,300);


}

    public JPanel background(JPanel panel) {
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBackground(new Color(194, 155, 99));
        background.setOpaque(true);
        background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        background.add(panel);
        return background;
    }

    public void youLost() throws InterruptedException {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through

        JLabel jb = new JLabel("You Lost");
        jb.setFont(new Font("Impact", Font.PLAIN, 60));

        panel.add(getSpacer(60));
        panel.add(jb);

        Menu frame = new Menu(toggle);
        jb.setAlignmentX(Component.CENTER_ALIGNMENT);
        jb.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setPreferredSize(new Dimension(100, 30));
        frame.setDesign(300, 300);

        frame.add(background(panel), SwingConstants.CENTER);

        Thread.sleep(2000);
        frame.dispose();

    }

    public void youWon() throws Exception {
        toggle.setGameResult(ProgramToggle.Result.RUNNING);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through

        JLabel jb = new JLabel("You won");
        jb.setFont(new Font("Impact", Font.PLAIN, 60));

        panel.add(getSpacer(60));
        panel.add(jb);

        Menu frame = new Menu(toggle);
        jb.setAlignmentX(Component.CENTER_ALIGNMENT);
        jb.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setOpaque(true);
        panel2.setPreferredSize(new Dimension(100, 30));
        frame.setDesign(300, 300);

        frame.add(background(panel), SwingConstants.CENTER);

        Thread.sleep(1000);
        frame.dispose();

    }


    // Spacer generator
    public JPanel getSpacer(int height) {
        JPanel spacer = new JPanel();
        spacer.setMaximumSize(new Dimension(Integer.MAX_VALUE, height)); // Full width
        spacer.setBackground(new Color(194, 155, 99));
        spacer.setOpaque(true);
        return spacer;
    }

    // Basic frame setup
    public void setDesign(int width, int height) {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Menu after completing level
    public void completingLevelMenu() {
        Menu frame = new Menu(toggle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through


        JButton mainMenu = new JButton("Main Menu");
        JButton nextLevel = new JButton("Next Level");
        JButton replay = new JButton("Replay Level");
        JButton options = new JButton("Options");


        for (JButton button : new JButton[]{mainMenu, nextLevel, replay, options}) {
            buttonPreset(button);
        }

        // Add components with spacing
        panel.add(getSpacer(10));
        panel.add(mainMenu);
        panel.add(getSpacer(10));
        panel.add(nextLevel);
        panel.add(getSpacer(10));
        panel.add(replay);
        panel.add(getSpacer(10));
        panel.add(options);


        // Nest panel and show

        frame.add(background(panel));
        frame.setDesign(300, 300);
    }

    public void failingLevelMenu() {
        Menu frame = new Menu(toggle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through


        JButton mainMenu = new JButton("Main Menu");
        JButton nextLevel = new JButton("Next Level");
        JButton replay = new JButton("Replay Level");
        JButton options = new JButton("Options");


        for (JButton button : new JButton[]{mainMenu, replay, options}) {
            buttonPreset(button);
        }

        // Add components with spacing
        panel.add(getSpacer(10));
        panel.add(mainMenu);
        panel.add(getSpacer(10));
        panel.add(nextLevel);
        panel.add(getSpacer(10));
        panel.add(replay);
        panel.add(getSpacer(10));
        panel.add(options);


        // Nest panel and show

        frame.add(background(panel));
        frame.setDesign(300, 300);

    }

    public void mainMenu() {
        Wave w = new Wave(toggle, enemySpeed, waves);

        Menu frame = new Menu(toggle);
        frame.setTitle("Main Menu");
        JButton mode1 = new JButton("Static Mode");
        JButton mode2 = new JButton("Dynamic Mode");
        JButton options = new JButton("Options");
        JButton info = new JButton("Info");

        // === [LEVEL SELECT BUTTON - TEMPORARY] ===
        JButton levelSelect = new JButton("Level Select");
        // =========================================

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through

        for (JButton button : new JButton[]{mode1, mode2, options, info /* levelSelect not styled here yet */}) {
            buttonPreset(button);
        }

        // === [LEVEL SELECT BUTTON - TEMPORARY] ===
        buttonPreset(levelSelect);
        // =========================================

        panel.add(getSpacer(10));
        panel.add(mode1);
        panel.add(getSpacer(10));
        panel.add(mode2);
        panel.add(getSpacer(10));
        panel.add(options);
        panel.add(getSpacer(10));
        panel.add(info);

        // === [LEVEL SELECT BUTTON - TEMPORARY] ===
        panel.add(getSpacer(10));
        panel.add(levelSelect);
        // =========================================

        frame.setVisible(true);
        frame.add(background(panel));
        frame.setDesign(300, 300);

        mode1.addActionListener(e -> {
            frame.dispose();
        });

        mode2.addActionListener(e -> {
            int[] number = {0};
            try {
                frame.dispose();
                countDown();

                Timer t = new Timer(1000, (ev) -> {
                    number[0]++;
                    if (number[0] > 5) {
                        ((Timer) ev.getSource()).stop();
                        try {
                            w.wave1();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                t.start();

            } catch (Exception ex) {
                System.out.println("Problem");
            }
        });

        options.addActionListener(e -> {
            frame.dispose();
            options(0);
        });

        info.addActionListener(e -> {
            frame.dispose();
            mainInfo();
        });

        // === [LEVEL SELECT BUTTON - TEMPORARY] ===
        levelSelect.addActionListener(e -> {
            frame.dispose();
            openLevelMap();
        });
        // =========================================
    }

}