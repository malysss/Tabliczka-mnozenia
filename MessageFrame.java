package tabliczkamnozenia;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author Owner
 */
public class MessageFrame extends JFrame{

    public MessageFrame() {
        super("Opis programu");
        setLayout(new FlowLayout());
        JTextArea obszarKomunikatu = new JTextArea(22, 34);
        String komunikat = "Program służący do nauki tabliczki mnożenia. Aby rozpocząć \n wystarczy wybrać zakres liczb, których chce się uczyć."
                + " \n\n Co nowego: \n ver. 1.1 \n tryb nauki typu FISZKI, czyli popełnienie błędu powoduje \n konieczność późniejszego, ponownego "
                + "rozwiązania tego samego \n działania. \n ver. 1.2 \n Pomiar czasu odpowiedzi \n ver. 1.3 \n Poprawiona szata graficzna, dodany kalkulator"
                + " \n "
                + "\n W przygotowaniu: TRYB TEST, angielska wersja językowa, \n zapis wyników"
                + "\n\n\n\n \"Tabliczka mnożenia\" ver. 1.3 \n autor: Bartosz Malysa \n e-mail: bartosz.malysa@gmail.com " ;
        obszarKomunikatu.setText(komunikat);
        obszarKomunikatu.setEditable(false);
        obszarKomunikatu.setLineWrap(true);
        setResizable(false);
        add(obszarKomunikatu);
    }
    
    public MessageFrame(int i){
    }
}
