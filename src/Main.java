import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {




        try {
Map m = new Map();

//progress,durability fix,****************************************

ProgramToggle programToggle = new ProgramToggle();
programToggle.start();

            Player p = new Player();
           // p.OpenInventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
