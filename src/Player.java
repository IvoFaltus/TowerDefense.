import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    private ArrayList<Tower> towers = new ArrayList<>();
private int chosenTower;

    public void OpenInventory(){

        Tower t1 = new Tower();
        Tower t2 = new Tower();
        Tower t3 = new Tower();
        t1.setTowerIcon(1);
        t2.setTowerIcon(1);
        t3.setTowerIcon(2);
towers.add(t1);
towers.add(t2);
towers.add(t3);
        Tower[] towersArray = new Tower[towers.size()];
        JFrame frame = new JFrame();
        JPanel jp = new JPanel(new GridLayout(towers.size()+2, 1));
        Label label = new Label("Choose a tower to play:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jp.add(label);


        for(int i =0;i<towers.size();i++){
            System.out.println(towers.get(i).toString());
        towersArray[i] = towers.get(i);

            jp.add(new JCheckBox("Tower LVL: "+towers.get(i).getLvl()));

        }
        jp.add(new JLabel(towers.get(0).getTowerIcon()));
jp.setVisible(true);

        frame.setSize(new Dimension(300,100));
        frame.setTitle("Inventory");
        frame.add(jp);
        frame.add(jp);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

       /* chosenTower = JOptionPane.showOptionDialog(jp,"Choose Tower","Inventory",JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,null,towersArray,towersArray[0]);*/

    }



}
