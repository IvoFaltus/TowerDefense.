import javax.sound.midi.Sequence;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Knight {
    public Knight(int health) {
        this.health = health;
    }

    public Knight() {
    }

    private int lastX = -1;
    private int lastY = -1;

    public void setLastXY(int lastX, int lastY) {
        this.lastX = lastX;
        this.lastY = lastY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public ArrayList addKnights() {
        ArrayList<Knight> wave1 = new ArrayList<>();
        Knight k = new Knight(100);
        Knight k2 = new Knight(100);
        k.setKnightIcon();
        k2.setKnightIcon();
        wave1.add(k);
        wave1.add(k2);
        return wave1;
    }

    private int health;
    private ImageIcon knightIcon;


    public void setKnightIcon() {
        URL url;


        if (this.health > 90) {
            //no idea how to shorten this
            url = getClass().getResource("/resources/knight100.png");
        } else if (this.health <= 90 && this.health > 80) {
            url = getClass().getResource("/resources/knight80.png");
        } else if (this.health <= 80 && this.health > 70) {
            url = getClass().getResource("/resources/knight70.png");
        } else if (this.health <= 70 && this.health > 60) {
            url = getClass().getResource("/resources/knight60.png");
        } else if (this.health <= 60 && this.health > 50) {
            url = getClass().getResource("/resources/knight50.png");
        } else if (this.health <= 50 && this.health > 40) {
            url = getClass().getResource("/resources/knight40.png");
        } else if (this.health <= 40 && this.health > 30) {
            url = getClass().getResource("/resources/knight30.png");
        } else if (this.health <= 30 && this.health > 20) {
            url = getClass().getResource("/resources/knight20.png");
        } else if (this.health <= 20 && this.health > 10) {
            url = getClass().getResource("/resources/knight10.png");
        } else {
            url = getClass().getResource("/resources/knight5.png");
        }

        knightIcon = new ImageIcon(url);


        if (url == null) {
            System.out.println(" Image not found!");
        } else {
            this.knightIcon = new ImageIcon(url);
            System.out.println(" Image loaded!");
            Image temp = this.knightIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            this.knightIcon = new ImageIcon(temp);
        }
    }


    public void placeEnemy(int x, int y, JLabel[][] labels, Knight knight) {

        labels[x][y].setIcon(knight.getKnightIcon());

    }

    public ImageIcon getKnightIcon() {
        return knightIcon;
    }

    public void setKnightIcon(ImageIcon knightIcon) {
        this.knightIcon = knightIcon;
    }
}


