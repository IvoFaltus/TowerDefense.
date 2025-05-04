import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    // Get screen resolution
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
public void buttonPreset(JButton button){
    Dimension buttonSize = new Dimension(200, 50);
    button.setMaximumSize(buttonSize);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setBackground(new Color(150, 150, 160));
    button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    button.setFont(new Font("Impact", Font.PLAIN, 18));
    button.setFocusPainted(false);


}



public JPanel background(JPanel panel){
    JPanel background = new JPanel();
    background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
    background.setBackground(new Color(194, 155, 99));
    background.setOpaque(true);
    background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
    background.add(panel);
    return background;
}



public void youLost(){




}
public void youWon()throws Exception{

    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(100,100));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setOpaque(false); // So background shows through

    JLabel jb = new JLabel("You won");
    jb.setFont(new Font("Impact", Font.PLAIN, 60));

    panel.add(getSpacer(60));
    panel.add(jb);

    Menu frame = new Menu();
    jb.setAlignmentX(Component.CENTER_ALIGNMENT);
jb.setAlignmentY(Component.CENTER_ALIGNMENT);
    frame.setVisible(true);

    JPanel panel2 = new JPanel();
    panel2.setOpaque(true);
    panel2.setPreferredSize(new Dimension(100,30));
    frame.setDesign(300,300);

    frame.add(background(panel),SwingConstants.CENTER);

    Thread.sleep(2000);
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
        Menu frame = new Menu();
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
        frame.setDesign(300,300);
    }

    public void failingLevelMenu() {
        Menu frame = new Menu();
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
        frame.setDesign(300,300);

    }
    public void mainMenu(){
        Menu frame = new Menu();
        frame.setTitle("Main Menu");
JButton mode1 = new JButton("Static Mode");
JButton mode2 = new JButton("Dynamic Mode");
JButton options = new JButton("Options");
JButton help = new JButton("Help");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // So background shows through
for(JButton button:new JButton[]{mode1,mode2,options,help  }){

    buttonPreset(button);
        }
        panel.add(getSpacer(10));
panel.add(mode1);
panel.add(getSpacer(10));
panel.add(mode2);
        panel.add(getSpacer(10));
panel.add(options);
        panel.add(getSpacer(10));
panel.add(help);

frame.setVisible(true);
frame.add(background(panel));
frame.setDesign(300,300);




mode1.addActionListener(e->{frame.dispose();});
mode2.addActionListener(e->{ frame.dispose();});
options.addActionListener(e->{ frame.dispose();});
help.addActionListener(e->{ frame.dispose();});







    }

}