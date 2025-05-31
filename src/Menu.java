import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu extends JFrame {
    private int roundplayed;
    private ProgramToggle toggle;
    private Clip music;
    private int currentWave;
private ArrayList<Integer> completedWaves;
    public Menu(ProgramToggle toggle, Clip music, int enemySpeed, ArrayList<Boolean> waves, int currentWave, ArrayList completedWaves, int roundplayed) {
        this.toggle = toggle;
        this.music = music;
        this.enemySpeed = enemySpeed;
        this.waves = waves;
        this.currentWave = currentWave;
        this.completedWaves=completedWaves;
        this.roundplayed = roundplayed;
    }

    public Menu(ProgramToggle toggle, Clip music, int enemySpeed, ArrayList<Boolean> waves, int currentWave) {
        this.toggle = toggle;
        this.music = music;
        this.enemySpeed = enemySpeed;
        this.waves = waves;
        this.currentWave = currentWave;
        this.completedWaves=new ArrayList<>();
    }

    public Menu(ProgramToggle toggle, int currentWave) {
        this.toggle = toggle;
        this.currentWave = currentWave;
    }

    public Menu(ProgramToggle toggle) {
        this.toggle = toggle;
        this.currentWave = currentWave;
        this.completedWaves=new ArrayList<>();
    }

    public Menu(ProgramToggle toggle, Clip music, int enemySpeed, ArrayList<Boolean> waves) {
        this.toggle = toggle;
        this.music = music;
        this.enemySpeed = enemySpeed;
        this.waves = waves;
        this.currentWave = currentWave;
    }


    public Menu() {
    }

    public Menu(ProgramToggle toggle, Clip music) {
        this.toggle = toggle;
        this.music = music;
        this.completedWaves=new ArrayList<>();
    }

    private int enemySpeed =1000;
    private ArrayList<Boolean> waves;


    private ArrayList<JButton> levelButtons = new ArrayList<>();


    /**
     * Launches a specific wave based on the provided wave number.
     * Initializes game state, sets current wave, and begins the corresponding wave logic.
     */
    public void startWave(int wave) {
        try {
            toggle.setCurrentWave(wave); // ✅ Save the wave for replay
            toggle.setGameResult(ProgramToggle.Result.RUNNING);
            System.out.println("starting");
            Wave w = new Wave(toggle, waves, enemySpeed);

            switch (wave) {
                case 1:


                    toggle.setCurrentWave(wave);
                    System.out.println("round played "+toggle.getRoundplayed());
                    if(toggle.getRoundplayed()==0){

                        int[] countodown ={0};
                        countDown();
                        Timer t = new Timer(1000,(e)->{
                            countodown[0]++;
                            System.out.println(countodown[0]);
                            if(countodown[0]==6){((Timer)e.getSource()).stop();
                                try {
                                    w.wave1();
                                }catch (Exception ex){}
                            }


                        });
t.start();


                    }else{
                        System.out.println("enemy speed = "+enemySpeed);
                        w.wave1();
                    }
                    break;
                case 2:
                    w.setEnemySpeed(enemySpeed);
                    toggle.setCurrentWave(wave);
                    w.wave2();

                    break;
                case 3:
                    w.setEnemySpeed(enemySpeed);
                    toggle.setCurrentWave(wave);
                    w.wave3();
                    break;
                case 4:
                    w.setEnemySpeed(enemySpeed);
                    toggle.setCurrentWave(wave);
                    w.wave4();

                    break;
                case 5:
                    w.setEnemySpeed(enemySpeed);
                    System.out.println("wave 5");
                    toggle.setCurrentWave(wave);
                    w.wave5();
                    break;
                case 6:
                    w.setEnemySpeed(enemySpeed);
                    toggle.setCurrentWave(wave);
                    w.wave6();
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Applies a consistent visual style and size to menu buttons.
     * Used for main menu and action buttons.
     */
    public void buttonPreset(JButton button) {
        Dimension buttonSize = new Dimension(200, 50);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(150, 150, 160));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setFont(new Font("Impact", Font.PLAIN, 18));
        button.setFocusPainted(false);


    }

    /**
     * Applies visual style specifically for back buttons.
     * Smaller and styled differently than main buttons.
     */
    public void backButtonPreset(JButton button) {
        Dimension buttonSize = new Dimension(130, 40);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(150, 150, 160));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setFont(new Font("Impact", Font.PLAIN, 18));
        button.setFocusPainted(false);


    }

//countodwn and passing currentwave

    /**
     * Displays a full-screen countdown panel before the wave begins.
     * The background uses a warm brown gradient to match the game's UI style.
     * The countdown goes from 5 to 0, then displays "START!" and closes.
     *
     * @return true if the countdown runs successfully
     */
    public boolean countDown() {
        Menu frame = new Menu(toggle,enemySpeed);
        frame.setUndecorated(true);

        JLabel countdownLabel = new JLabel("5");
        countdownLabel.setFont(new Font("Impact", Font.BOLD, 100));
        countdownLabel.setForeground(new Color(255, 255, 255));
        countdownLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel countdownPanel = new JPanel();
        countdownPanel.setLayout(new BoxLayout(countdownPanel, BoxLayout.Y_AXIS));
        countdownPanel.setOpaque(false);
        countdownPanel.add(Box.createVerticalGlue());
        countdownPanel.add(countdownLabel);
        countdownPanel.add(Box.createVerticalGlue());


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(194, 155, 99), // top: light brown
                        0, getHeight(), new Color(130, 90, 60) // bottom: darker brown
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 60, 30), 4));
        backgroundPanel.add(countdownPanel, BorderLayout.CENTER);

        int[] numbers = {5};
        Timer t = new Timer(1000, e -> {
            if (numbers[0] > 0) {
                countdownLabel.setText(String.valueOf(numbers[0]));
            } else if (numbers[0] == 0) {
                countdownLabel.setText("START!");
                countdownLabel.setForeground(new Color(0, 210, 0));
            } else {
                ((Timer) e.getSource()).stop();
                frame.dispose();
            }
            numbers[0]--;
        });
        t.start();

        frame.setContentPane(backgroundPanel);
        frame.setDesign(400, 300);
        frame.setVisible(true);

        return true;
    }


    /**
     * Displays a fullscreen dialog when the player beats the final wave.
     * Includes a congratulations message and a Main Menu button.
     */
    public void wonGame() {
        Menu frame = new Menu(toggle, enemySpeed);
        frame.setTitle("You Won the Game!");
        frame.setUndecorated(true);

        JLabel winLabel = new JLabel("CONGRATULATIONS!", SwingConstants.CENTER);
        winLabel.setFont(new Font("Impact", Font.BOLD, 30));
        winLabel.setForeground(new Color(34, 139, 34));
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel messageLabel = new JLabel("You completed all waves!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton mainMenu = new JButton("Main Menu");
        buttonPreset(mainMenu);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        panel.add(getSpacer(20));
        panel.add(winLabel);
        panel.add(getSpacer(10));
        panel.add(messageLabel);
        panel.add(getSpacer(25));
        panel.add(mainMenu);

        JPanel borderWrapper = new JPanel(new BorderLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 4));
        borderWrapper.setBackground(new Color(20, 20, 20));
        borderWrapper.add(background(panel), BorderLayout.CENTER);

        frame.setContentPane(borderWrapper);
        frame.setDesign(360, 240);
        frame.setVisible(true);

        mainMenu.addActionListener(e -> {
            frame.dispose();
            mainMenu();
        });
    }
    /**
     * Wraps any given panel in a styled brown background with padding.
     * Used across menus for visual consistency.
     */
    public JPanel background(JPanel panel) {
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBackground(new Color(194, 155, 99));
        background.setOpaque(true);
        background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        background.add(panel);
        return background;
    }
    /**
     * Shows a small modal dialog when the player loses.
     * Styled in red tones with an “Exit” button.
     */
    public void youLost() {
        JDialog dialog = new JDialog((Frame) null, "Defeat", true);
        dialog.setUndecorated(true);
        dialog.setSize(350, 140);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(50, 0, 0));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("You lost the battle!", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton okButton = new JButton("Exit");
        okButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        okButton.setBackground(new Color(160, 30, 30));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.addActionListener(e -> dialog.dispose());

        panel.add(Box.createVerticalStrut(15));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(okButton);
        panel.add(Box.createVerticalStrut(10));

        dialog.add(panel);
        dialog.setVisible(true);
    }
    /**
     * Shows a small modal dialog when the player successfully finishes a wave.
     * Styled in green tones with a “Continue” button.
     */
    public void youWon() {
        JDialog dialog = new JDialog((Frame) null, "Victory", true);
        dialog.setUndecorated(true);
        dialog.setSize(350, 140);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 60, 0));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Victory! You defended successfully!", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton okButton = new JButton("Continue");
        okButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        okButton.setBackground(new Color(40, 130, 40));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.addActionListener(e -> dialog.dispose());

        panel.add(Box.createVerticalStrut(15));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(okButton);
        panel.add(Box.createVerticalStrut(10));

        dialog.add(panel);
        dialog.setVisible(true);
    }



    /**
     * Generates a vertical spacer with specified height for layout spacing.
     */
    public JPanel getSpacer(int height) {
        JPanel spacer = new JPanel();
        spacer.setMaximumSize(new Dimension(Integer.MAX_VALUE, height)); // Full width
        spacer.setBackground(new Color(194, 155, 99));
        spacer.setOpaque(true);
        return spacer;
    }

    /**
     * Applies default window settings such as size, location, and visibility.
     */
    public void setDesign(int width, int height) {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void lostMenu() {
        Wave w = new Wave(toggle);
        Menu frame = new Menu(toggle,enemySpeed);
        frame.setTitle("Game Over");
        frame.setUndecorated(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JLabel lostLabel = new JLabel("GAME OVER");
        lostLabel.setFont(new Font("Impact", Font.BOLD, 30));
        lostLabel.setForeground(Color.RED);
        lostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgain = new JButton("Replay Level");
        JButton mainMenu = new JButton("Main Menu");
        JButton options = new JButton("Settings");

        for (JButton button : new JButton[]{playAgain, mainMenu, options}) {
            buttonPreset(button);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        panel.add(getSpacer(15));
        panel.add(lostLabel);
        panel.add(getSpacer(25));
        panel.add(playAgain);
        //panel.add(getSpacer(10));
        // panel.add(mainMenu);
        panel.add(getSpacer(10));
        panel.add(options);

        JPanel borderWrapper = new JPanel(new BorderLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        borderWrapper.setBackground(new Color(20, 20, 20));
        borderWrapper.add(background(panel), BorderLayout.CENTER);

        frame.setContentPane(borderWrapper);
        frame.setDesign(320, 320);
        frame.setVisible(true);

        playAgain.addActionListener(e -> {
            frame.dispose();
            try {
                int waveToReplay = toggle.getCurrentWave();
                toggle.setGameResult(ProgramToggle.Result.RUNNING);
                startWave(waveToReplay);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        mainMenu.addActionListener(e -> {
            frame.dispose();
            mainMenu();
        });

        options.addActionListener(e -> {
            frame.dispose();
            options(2);
        });
    }
    /**
     * Displays a win screen menu after completing a wave.
     * Offers options to continue, go to main menu, or access other features.
     */
    public void winMenu() {


        Wave w = new Wave(toggle, enemySpeed, waves);
        Menu frame = new Menu(toggle,enemySpeed);
        frame.setTitle("You Win!");
        frame.setUndecorated(true);

        JLabel winLabel = new JLabel("YOU WIN!");
        winLabel.setFont(new Font("Impact", Font.BOLD, 30));
        winLabel.setForeground(new Color(34, 139, 34));
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextLevel = new JButton("Next Level");
        JButton mainMenu = new JButton("Main Menu");
        JButton options = new JButton("Settings");
        JButton levelSelect = new JButton("Level Select");

        for (JButton button : new JButton[]{nextLevel, mainMenu, options, levelSelect}) {
            buttonPreset(button);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        panel.add(getSpacer(15));
        panel.add(winLabel);
        panel.add(getSpacer(25));
        panel.add(nextLevel);
        panel.add(getSpacer(10));
        // panel.add(mainMenu);
        // panel.add(getSpacer(10));
        panel.add(options);
        panel.add(getSpacer(10));
        panel.add(levelSelect);

        JPanel borderWrapper = new JPanel(new BorderLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 4));
        borderWrapper.setBackground(new Color(20, 20, 20));
        borderWrapper.add(background(panel), BorderLayout.CENTER);

        frame.setContentPane(borderWrapper);
        frame.setDesign(320, 320);
        frame.setVisible(true);

        nextLevel.addActionListener(e -> {
            int nextwave = toggle.getCurrentWave() + 1;
            frame.dispose();
            try {
                switch (nextwave) {
                    case 2 -> startWave(2);
                    case 3 -> startWave(3);
                    case 4 -> startWave(4);
                    case 5 -> startWave(5);
                    case 6 -> {startWave(6);
                    }
                    default -> System.out.println("undefined");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        mainMenu.addActionListener(e -> {
            frame.dispose();
            mainMenu();
        });

        options.addActionListener(e -> {
            frame.dispose();
            options(1);
        });

        levelSelect.addActionListener(e -> {
            frame.dispose();
            openLevelMap();
        });
    }

    /**
     * Opens the info screen that explains gameplay mechanics and rules.
     */
    public void mainInfo() {
        Menu frame = new Menu(toggle,enemySpeed);
        frame.setUndecorated(true);

        // Read the info text from a file
        // Read the info text from a resource file
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/resources/info.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append("<p>").append(line).append("</p>");
            }
        } catch (Exception e) {
            content.append("<p>Error loading info file.</p>");
            e.printStackTrace();
        }

        JLabel text = new JLabel("<html><div style='text-align: center; font-size: 14px; line-height: 1.5;'>"
                + content.toString() + "</div></html>");
        text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(text, gbc);

        JButton back = new JButton("Back");
        backButtonPreset(back);
        gbc.gridy++;
        contentPanel.add(back, gbc);

        frame.add(background(contentPanel));
        frame.setDesign(800, 350);
        frame.setVisible(true);

        back.addActionListener(e -> {
            mainMenu();
            frame.dispose();
        });
    }
int currentEnemySpeed =1000;
    /**
     * Displays the options menu allowing the player to change volume and difficulty.
     * Returns to appropriate menu based on input.
     */
    public void options(int whichMenu) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        Menu frame = new Menu(toggle,enemySpeed);
        frame.setUndecorated(true);

        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        volumeLabel.setFont(new Font("Impact", Font.PLAIN, 18));


        JSlider volume = new JSlider(0, 100, 50);
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

        //dropdown
        JLabel diffLabel = new JLabel("Difficulty:");
        diffLabel.setFont(new Font("Impact", Font.PLAIN, 18));

        String[] options = {"Easy", "Medium", "Hard", "Extreme"};
        JComboBox<String> dropOptions = new JComboBox<>(options);
        dropOptions.setSelectedIndex(0);

        dropOptions.addActionListener(e -> {
            switch (dropOptions.getSelectedIndex()) {
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
                default:
                    enemySpeed=1000;
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
                case 2 -> this.lostMenu();
                default -> mainMenu();
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

        JPanel borderWrapper = new JPanel(new BorderLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 4)); // gray 4px border
        borderWrapper.setBackground(new Color(30, 30, 30)); // dark background
        borderWrapper.add(background(panel), BorderLayout.CENTER);

        frame.setContentPane(borderWrapper);
        frame.setDesign(300, 300);
        frame.setVisible(true);
    }
    /**
     * Opens a graphical level selection screen with visually connected buttons.
     * Only waves completed or unlocked are accessible.
     */

    public void openLevelMap() {
        Menu m = new Menu(toggle,enemySpeed);
        JFrame frame = new JFrame("Level Selection");
        frame.setUndecorated(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

        // Determine the highest wave completed
        int maxCompletedWave = toggle.getCompletedWaves().stream().mapToInt(Integer::intValue).max().orElse(0);

        for (int i = 0; i < positions.length; i++) {
            JButton levelBtn = new JButton("Wave " + (i + 1));
            levelBtn.setFocusPainted(false);
            levelBtn.setFont(new Font("Impact", Font.PLAIN, 16));
            levelBtn.setBackground(new Color(150, 150, 160));
            levelBtn.setForeground(Color.BLACK);
            levelBtn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            levelBtn.setBounds(positions[i][0], positions[i][1], 100, 40);

            final int waveNumber = i + 1;

            boolean isUnlocked = waveNumber <= maxCompletedWave + 1;

            levelBtn.setEnabled(isUnlocked);
            if (isUnlocked) {
                levelBtn.addActionListener(e -> {
                    frame.dispose();
                    startWave(waveNumber);
                });
            } else {
                levelBtn.setBackground(Color.DARK_GRAY);
                levelBtn.setForeground(Color.LIGHT_GRAY);
            }

            panel.add(levelBtn);
            levelButtons.add(levelBtn);
        }

        frame.setVisible(true);
    }


    /**
     * Shows the main menu screen with navigation buttons: Play, Options, Info, and Level Select.
     */
    public void mainMenu() {
        Wave w = new Wave(toggle, enemySpeed);
        System.out.println(currentWave + " this is current wave");

        Menu frame = new Menu(toggle, enemySpeed);
        frame.setTitle("Main Menu");
        frame.setUndecorated(true);

        // Updated title: black text, clean font
        JLabel title = new JLabel("MAIN MENU");
        title.setFont(new Font("Verdana", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.GRAY); // black text

        JButton mode1 = new JButton("Play");       // ▶
        JButton options = new JButton("Options");  // ⚙
        JButton info = new JButton("Info");        // ℹ
        JButton levelSelect = new JButton("Level Select"); // fallback text

        for (JButton button : new JButton[]{mode1, options, info, levelSelect}) {
            buttonPreset(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 45));
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        panel.add(getSpacer(25));
        panel.add(title);
        panel.add(getSpacer(30));
        panel.add(mode1);
        panel.add(getSpacer(12));
        panel.add(options);
        panel.add(getSpacer(12));
        panel.add(info);
      //  panel.add(getSpacer(12));
     //   panel.add(levelSelect);
        panel.add(getSpacer(25));

        JPanel borderWrapper = new JPanel(new BorderLayout());
        borderWrapper.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4)); // gray border
        borderWrapper.setBackground(new Color(20, 30, 20)); // background unchanged
        borderWrapper.add(background(panel), BorderLayout.CENTER);

        frame.setContentPane(borderWrapper);
        frame.setDesign(320, 400);
        frame.setVisible(true);

        mode1.addActionListener(e -> {frame.dispose();
            startWave(1);

        });
        options.addActionListener(e -> {
            frame.dispose();
            options(0);
        });
        info.addActionListener(e -> {
            frame.dispose();
            mainInfo();
        });
        levelSelect.addActionListener(e -> {
            frame.dispose();
            openLevelMap();
        });
    }


}