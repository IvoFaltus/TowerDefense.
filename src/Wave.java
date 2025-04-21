import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Wave {
    public Wave() {
    }

    Map m = new Map();
Tower TOWER = new Tower();
Tower t = new Tower();
    public Wave(Map map) {
        this.m = map;
    }

    Knight KNIGHT = new Knight();

    Knight k = (Knight) KNIGHT.addKnights().get(0);
    Knight k2 = (Knight) KNIGHT.addKnights().get(1);

public void playerChoice(){

    JTextField x = new JTextField(); JTextField y = new JTextField();
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
    jp.setLayout(new FlowLayout());

        jp.add(jpX);
        jp.add(jpY);

    JFrame frame = new JFrame();
frame.add(new JLabel("Enter coordinates you want to place a tower to"));
        frame.setSize(new Dimension(300,100));
    frame.add(jp);
    frame.setLayout(new FlowLayout());
    //frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

}
    public void moveEnemy(int x, int y, Knight knight) throws Exception {
        URL url = getClass().getResource("/resources/pathPhoto.png");
        Icon i = new ImageIcon(url);



if(knight.getLastX()!=-1&&knight.getLastY()!=-1) {
    m.labels5x5[knight.getLastX()][knight.getLastY()].setIcon(i);
}
        KNIGHT.placeEnemy(x, y, m.labels5x5, knight);
        knight.setLastXY(x,y);
    }


    public void enemyPath() throws Exception {
t.setTowerIcon(1);
playerChoice();
       // m.MapWindow5x5();



        moveEnemy(0, 0, k);
        m.mapRender();
        moveEnemy(1, 0, k);
        m.mapRender();
        moveEnemy(2, 0, k);
        moveEnemy(0, 0, k2);

        m.mapRender();
        moveEnemy(1, 0, k2);
        moveEnemy(2, 1, k);
        m.mapRender();
        moveEnemy(2, 2, k);
        moveEnemy(2, 0, k2);
        m.mapRender();
        moveEnemy(2, 1, k2);
        moveEnemy(2, 3, k);
        m.mapRender();
        moveEnemy(2, 2, k2);
        moveEnemy(2, 4, k);
        m.mapRender();
        moveEnemy(2, 3, k2);
        moveEnemy(3, 4, k);
        m.mapRender();
        moveEnemy(2, 4, k2);
        moveEnemy(4, 4, k);
        m.mapRender();
        moveEnemy(3, 4, k2);
        m.mapRender();

    }

    public void wave1() throws Exception {
        enemyPath();
    }

/*
    public void wave1() throws Exception {
        boolean isOver = false;


        enemyPath();
        Thread.sleep(1000);


    }
*/

}
