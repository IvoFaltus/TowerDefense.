import javax.sound.midi.Sequence;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Knight {
    private int health;
    private ImageIcon knightIcon;


    public void setKnightIcon() {
        URL url = getClass().getResource("/resources/knight100.png");
        if (url == null) {
            System.out.println(" Image not found!");
        } else {
            this.knightIcon = new ImageIcon(url);
            System.out.println(" Image loaded!");
           Image temp = this.knightIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            this.knightIcon = new ImageIcon(temp);
        }
    }







    public void placeEnemy(JLabel[][] labels,int x, int y){




JLabel jl = new JLabel();
       jl.setBounds(50, 50, 256, 256);


jl.setIcon(knightIcon);


        jl.setVisible(true);
        labels[x][y].setIcon(knightIcon);



    }

}
