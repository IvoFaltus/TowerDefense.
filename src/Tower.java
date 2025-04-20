import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tower {
    public Tower() {
    }

    Map map = new Map();
    private ImageIcon towerIcon;
private int lvl;

    public void placeTower(int x, int y, JLabel[][] labels,int lvl){
        Tower t = new Tower(1);
        t.setTowerIcon(lvl);
        labels[x][y].setIcon(t.getTowerIcon());
    }
    public void removeTower(int x, int y, JLabel[][] labels){
        Color c = new Color(106, 170, 100);
        labels[x][y].setIcon((Icon)c);
    }




public void setTowerIcon( int lvl){
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
}
