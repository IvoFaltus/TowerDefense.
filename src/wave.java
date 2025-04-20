import javax.swing.*;
import java.net.URL;

public class wave {
    Map m;

    public wave(Map map) {
        this.m = map;
    }

    Knight KNIGHT = new Knight();

    Knight k = (Knight) KNIGHT.addKnights().get(0);
    Knight k2 = (Knight) KNIGHT.addKnights().get(1);


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

        m.MapWindow5x5();
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
