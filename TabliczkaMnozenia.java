package tabliczkamnozenia;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Owner
 */
public class TabliczkaMnozenia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ramka = new Ramka();
                ramka.pack();
                ramka.setSize(550, 350);
                ramka.setResizable(false);
                ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ramka.setVisible(true);

            }
        });
    }
}
