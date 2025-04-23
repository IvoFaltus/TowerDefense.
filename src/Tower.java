import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;

public class Tower {
private boolean isActive = false;

    public Tower(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    private boolean wait = true;

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLvl() {
        return lvl;
    }













    public Tower() {
    }

    Map map = new Map();
    private ImageIcon towerIcon;
private int lvl;

    public static void placeTower(JLabel[][] labels, Tower tower   )throws Exception{











        JToggleButton ok = new JToggleButton("OK");


        JTextField x = new JTextField();
        JTextField y = new JTextField();
        x.setPreferredSize(new Dimension(100,20));
        y.setPreferredSize(new Dimension(100,20));



        JLabel jlX = new JLabel("X: ");
        JLabel jlY = new JLabel("Y: ");

        JPanel jpX = new JPanel(new FlowLayout());
        jpX.add(jlX);
        jpX.add(x);
        JPanel jpY = new JPanel(new FlowLayout());

        jpY.add(jlY);
        jpY.add(y);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        jp.add(jpX);
        jp.add(jpY);

        JLabel timerLabel = new JLabel("20 sec", SwingConstants.CENTER);
jp.add(timerLabel);

int[] timeleft = {20};

    Timer timer = new Timer(1000, e -> {
        timeleft[0]--;
        timerLabel.setText(String.valueOf(timeleft[0]+" sec "));
    });
    timer.start();



        JFrame frame = new JFrame();
        frame.add(new JLabel("Enter coordinates you want to place a tower to"));
        frame.setSize(new Dimension(300,100));
        frame.add(jp);
        frame.add(ok);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        ok.addActionListener(e -> {
            int xValue = Integer.parseInt(x.getText());
            int yValue = Integer.parseInt(y.getText());



            labels[xValue][yValue].setIcon(tower.getTowerIcon());
            frame.dispose();

        });






    }
    public void removeTower(int x, int y, JLabel[][] labels){
        Color c = new Color(106, 170, 100);
        labels[x][y].setIcon((Icon)c);
    }




public void setTowerIcon( ){
        int lvl=1;
        //incomplete
    URL url = null;
    switch (lvl){
        case 1:
             url = getClass().getResource("/resources/tower1.png");
            break;
        case 2:
             url = getClass().getResource("/resources/tower2.png");
            break;
        case 3:
            url = getClass().getResource("/resources/tower3.png");
            break;


    }
this.towerIcon = new ImageIcon(url);
    Image temp = this.towerIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
    this.towerIcon = new ImageIcon(temp);

}

    public ImageIcon getTowerIcon() {
        return towerIcon;
    }

    public void setTowerIcon(ImageIcon towerIcon) {
        this.towerIcon = towerIcon;
    }

    public Tower(int lvl) {
        this.lvl = lvl;
    }

    public boolean isWait() {
        return wait;
    }
}
