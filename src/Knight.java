import javax.sound.midi.Sequence;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Knight {
    private boolean isAlive;
private int x;
private int y;

public void setPosition(int x, int y) {

    this.x = x;
    this.y = y;
}


public String getPosition(){

    return this.x+","+this.y;
}



    public boolean isAlive() {
        return isAlive;
    }


    public boolean isAt(int x,int y) {
        return this.x == x && this.y == y;
    }


    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private int currentX;
    private int currentY;
public boolean AllKnightsDead(ArrayList<Knight> knights) throws Exception {
    boolean temp = false;
    int numberOfKnights = 0;








    for(int i=0;i<knights.size();i++){
        if(knights.get(i).getHealth()==0){
            numberOfKnights++;
        }
    }
    if(numberOfKnights==knights.size()){
        temp = true;
    }


    Menu m =new Menu(null,null);
    m.youWon();
    return temp;
}


    public void enemyHealth() {


    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void setDEfaultHealth(int health) {
        this.health =100;
    }




    public int getHealth() {
        return health;
    }

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

    public void knightPreset(){

        this.setKnightIcon();
        this.setHealth(100);
    }









    private int health;
    private ImageIcon knightIcon;


    public ImageIcon setKnightIcon() {
        URL url;



            //no idea how to shorten this
            url = getClass().getResource("/resources/knight.png");





        knightIcon = new ImageIcon(url);


        if (url == null) {
            System.out.println(" Image not found!");
        } else {
            this.knightIcon = new ImageIcon(url);

            Image temp = this.knightIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            this.knightIcon = new ImageIcon(temp);
        }
        return knightIcon;
    }


    public void placeEnemy(int x, int y, JLabel[][] labels, Knight knight) {

        labels[x][y].setIcon(knight.getKnightIcon());

    }

    public ImageIcon getKnightIcon() {
        return knightIcon;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setKnightIcon2(ImageIcon knightIcon) {
        this.knightIcon = knightIcon;
    }
}


