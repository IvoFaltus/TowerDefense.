import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    private int towersCount;
    private ArrayList<Tower> towers = new ArrayList<>();

    public void addTowerWave1() {
        Tower t1 = new Tower();
        t1.setTowerIcon();
        for(int i=0;i<2;i++) {

            towers.add(t1);
        }
    }
private int chosenTower;

    public void OpenInventory(JLabel[][] labels) throws Exception {
        ArrayList<JButton> buttons = new ArrayList<>();
AtomicBoolean paused = new AtomicBoolean(false);






        Tower[] towersArray = new Tower[towers.size()];
        JFrame frame = new JFrame();
        JPanel jp = new JPanel(new GridLayout(towers.size()+2, 1));
        Label label = new Label("Choose a tower to play:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jp.add(label);


        for(int i =0;i<towers.size();i++){
            System.out.println(towers.get(i).toString());
        towersArray[i] = towers.get(i);


           JButton jb = new JButton("Tower LVL: "+towers.get(i).getLvl());
            jp.add(jb);
            buttons.add(jb);
        }




        for(int i=0;i<buttons.size();i++){

            buttons.get(i).addActionListener(e -> {

                try {
                    if(returnNonActiveTower()!=0) {
                        towers.get(returnNonActiveTower()).setTowerIcon();
                        Tower.placeTower(labels, towers.get(returnNonActiveTower()));
                    }else{
                        JFrame f = new JFrame("Warning");
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        f.setSize(300, 120);
                        f.setLayout(new BorderLayout());

                        JLabel message = new JLabel("You're out of towers!", SwingConstants.CENTER);
                        JButton okButton = new JButton("OK");

                        okButton.addActionListener(e1 -> f.dispose());

                        f.add(message, BorderLayout.CENTER);
                        f.add(okButton, BorderLayout.SOUTH);

                        f.setLocationRelativeTo(null); // Center the frame
                        f.setVisible(true);


                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            });
        }









        jp.add(new JLabel(towers.get(0).getTowerIcon()));
jp.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        frame.setSize(new Dimension(300,100));
        frame.setTitle("Inventory");
        frame.add(jp);
        frame.add(jp);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setLocation(width/3,height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }



    public int returnNonActiveTower() {
        int tower = 0;
        for(int i =0;i<towers.size();i++){
            if(towers.get(i).isActive()){}
            tower = i;
        }
        return tower;
    }




}
