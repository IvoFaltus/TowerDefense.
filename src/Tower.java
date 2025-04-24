import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

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




public void removeTower(ArrayList<Tower> inActiveTowers, JLabel[][] labels, int rows, int cols, int[] addedTowers){











    AtomicBoolean hasClicked = new AtomicBoolean(false); // shared click flag


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalI = i;
                int finalJ = j;

                JLabel tile = labels[finalI][finalJ];

                if (tile != null) {
                    tile.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!hasClicked.get() && tile.getIcon() !=null) {
                                Tower t1 = new Tower();
                                t1.setActive(false);
                                t1.setTowerIcon();
                                addedTowers[0]++;
                                inActiveTowers.add(t1);
                                tile.setIcon(null);

                                hasClicked.set(true);
                            }
                        }
                    });
                }
            }
        }









}








    public Tower() {
    }

    Map map = new Map();
    private ImageIcon towerIcon;
private int lvl;

    public static void placeTower(JLabel[][] labels, int inActiveTowers, int rows, int cols   )throws Exception{



        AtomicBoolean hasClicked = new AtomicBoolean(false); // shared click flag

        if (inActiveTowers != 0) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int finalI = i;
                    int finalJ = j;

                    JLabel tile = labels[finalI][finalJ];

                    if (tile != null) {
                        tile.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (!hasClicked.get() && tile.getIcon() == null) {
                                    Tower t = new Tower();
                                    t.setTowerIcon();
                                    tile.setIcon(t.getTowerIcon());
                                    t.setActive(true);
                                    hasClicked.set(true);
                                }
                            }
                        });
                    }
                }
            }
        }







/*
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





*/
    }





public ImageIcon setTowerIcon( ){
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
return towerIcon;
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
