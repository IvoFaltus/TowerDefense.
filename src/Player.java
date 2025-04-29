import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    int addedTowersUsed =0;
    int[] addedTowers={0};
    private int towersCount;
    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<Tower> inActivetowers = new ArrayList<>();

    public void addTowers(int howManyTowers) {
        for (int i = 0; i < howManyTowers; i++) {
            Tower t = new Tower();          // create new instance
            t.setTowerIcon();
            t.setActive(false);
            towers.add(t);
        }
    }


    public void OpenInventory(JLabel[][] labels,boolean wannaRemove, ArrayList<Integer> TowerIndexes) throws Exception {

if(!wannaRemove){
        inActivetowers.clear();


        ArrayList<JButton> buttons = new ArrayList<>();
        AtomicBoolean paused = new AtomicBoolean(false);


        for (int i = 0; i < towers.size(); i++) {

            if (towers.get(i).isActive() == false) {
                inActivetowers.add(towers.get(i));
            }

        }


        for(int i=0;i<addedTowers[0];i++){

            Tower t = new Tower();
            t.setTowerIcon();
            t.setActive(false);
            inActivetowers.add(t);
            addedTowersUsed++;
            if(addedTowers[0]>0){
            addedTowers[0]--;}
        }



        ;
        JFrame frame = new JFrame();
        JPanel jp = new JPanel(new GridLayout(towers.size() + 2, 1));
        Label label = new Label("Choose a tower to play:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jp.add(label);


        for (int i = 0; i < inActivetowers.size(); i++) {
            Tower currentTower = inActivetowers.get(i); // capture the correct tower

            JButton jb = new JButton("Tower");
            jp.add(jb);
            buttons.add(jb);

            jb.addActionListener(e -> {
                try {
                    frame.dispose();

                    Tower.placeTower(labels, inActivetowers.size(), 5, 5, TowerIndexes);
                    currentTower.setActive(true);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        if (inActivetowers.size() == 0) {
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



/*
        for(int i=0;i<buttons.size();i++){

            buttons.get(i).addActionListener(e -> {

                try {
                    frame.dispose();

                    if(inActivetowers.size()!=0) {

                        Tower.placeTower(labels, inActivetowers.getFirst());
                        inActivetowers.getFirst().setActive(true);


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
*/


//region x
        jp.add(new JLabel(towers.get(0).getTowerIcon()));
        jp.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        frame.setSize(new Dimension(300, 100));
        frame.setTitle("Inventory");
        frame.add(jp);
        frame.add(jp);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setLocation(width / 3, height / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (inActivetowers.size() != 0) {
            frame.setVisible(true);
        }
//endregion
    }else{
    Tower t = new Tower();
    t.removeTower(inActivetowers,labels,5,5,addedTowers);
}
    }








}
