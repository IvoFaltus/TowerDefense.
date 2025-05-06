import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

Wave w = new Wave();


        try {

UnifiedWindow window = new UnifiedWindow();
window.start();

           w.wave1();
            Player p = new Player();
           // p.OpenInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
