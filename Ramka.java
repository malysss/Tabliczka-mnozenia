package tabliczkamnozenia;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.MetaMessage;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Owner
 */
public class Ramka extends JFrame{
    JButton textField;
    JTextField wyswietlacz;
    JButton buttonExit;
    JLabel label;
    JLabel label2;
    ButtonGroup buttonGroup;
    JPanel panel;
    JPanel panel2;
    JPanel panel3;
    int[] tab;
    int whatNumber;
    int wynik;
    Obliczenia obliczenia;
    TablicaLiczbLosowych tabLiczb;
    String odpowiedz;
    JTextArea komunikat;
    int lookNumber;
    UIManager.LookAndFeelInfo[] infos;
    JLabel etykietaZData;
    Date date;
    GregorianCalendar gregorianCalendar;
    JLabel czasyOdpowiedziJLabel;
    String czasyOdpowiedzi;
    Stoper stoper;
    int pierwszyPanel = 0;
    JRadioButton radioButton;
    JFrame messageFrame;
    Calculator calculator;
    //zmienne statyczne do napisów i komunikatów
    static String message;
    static Message messageClass = new Message();
    
    public Ramka(){
    	
        super(m(0));
        utworzMessageRamka();
        stoper = new Stoper();
//        setLayout(new FlowLayout());
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        label = new JLabel(m(1));
        buttonGroup = new ButtonGroup();
        panel = new JPanel();
        lookNumber = 0;
        String[] stringZeStopera2;
        //Sprawdzenie dostępnych motywów
        infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos){
        }
        	
        
        //Dodanie menu
        MenuPlWew menuPlWew = new MenuPlWew();
        add(menuPlWew, new GBC(0,0).setWeight(100, 100).setAnchor(GBC.NORTHWEST));
        
        //Dodanie radiobuttonów i panelu
        for (int i=1; i<11; i++){
        	addRadioButton(Integer.toString(i));
        }
        
        add(label, new GBC(0, 1, 2, 1).setWeight(100, 100).setAnchor(GBC.NORTH));
        add(panel, new GBC(0, 2, 2, 1).setWeight(100, 100).setAnchor(GBC.NORTH));
        
        //Część ramki odpowiedzialna za wyświetlanie i przyjmowanie obliczeń
        label2 = new JLabel(m(2));
        
        textField = new JButton(m(3));
        textField.setEnabled(false);
        
        panel3 = new JPanel();
        komunikat = new JTextArea(3, 25);
        komunikat.setText(m(4));
        komunikat.setEditable(false);
        panel3.add(komunikat);
        add(panel3, new GBC(0, 3, 1, 3).setWeight(100, 100));
        
        makeWyswietlacz();
        
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(label2);
        panel2.add(textField);
        panel2.add(wyswietlacz);
        
        add(panel2, new GBC(1, 3, 1, 3).setWeight(100, 100));
        
        czasyOdpowiedziJLabel = new JLabel(stoper.calculate());
        System.out.println("za czasyOdpowiedziJLabel = new JLabel(stoper.calculate());");
        add(czasyOdpowiedziJLabel, new GBC(0, 6).setWeight(100, 100));
        gregorianCalendar = new GregorianCalendar();
        date = gregorianCalendar.getTime();
        etykietaZData = new JLabel("Dzisiejsza data to: " + date.toString());
        add(etykietaZData, new GBC(0,7).setWeight(100, 100));
        makeButtonExit();
        
    }
    
    public void addRadioButton(String s){
        
        radioButton = new JRadioButton(s);
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	wyswietlacz.setEditable(true);
                whatNumber = Integer.parseInt(s);
                obliczenia = new Obliczenia(whatNumber);
                textField.setText(obliczenia.showNumbers());
                wyswietlacz.setText("");
                wyswietlacz.requestFocusInWindow();
                stoper.czasStart();
            }
        });
        	buttonGroup.add(radioButton);
           	panel.add(radioButton);
       	
    }
    
    /**
     * Utworzenie i obsługa pola przyjmowania wyników od użytkownika
     */
    public void makeWyswietlacz(){
        wyswietlacz = new JTextField(m(5), 10);
        wyswietlacz.setFont(new Font(wyswietlacz.getFont().getName(), Font.BOLD, 18));
        System.out.println(wyswietlacz.getFont().getName());
        wyswietlacz.setEditable(false);
        wyswietlacz.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void actionPerformed(ActionEvent e) {
                String wynikString = wyswietlacz.getText();
                //System.out.println("wewnątrz actionPerformed " + wynikString);
                boolean bool = true;
                while (bool){
                    try {
                        //wynik = Integer.parseInt(wynikString);
                        wynik = Integer.parseInt(wyswietlacz.getText());
                        //System.out.println("wewnątrz bloku try " + wynik);
                        bool = false;
                    } catch (NumberFormatException ex) {
                        
                        //System.out.println("wewnątrz bloku catch "+ "błąd");
                        wynik = -987654;
                        break;
                    }
                }
            
                //System.out.println("Za blokiem try-catch" + wynik);
                
                try {
                    odpowiedz = obliczenia.sprawdz(wynik);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ramka.class.getName()).log(Level.SEVERE, null, ex);
                }
                komunikat.setText(odpowiedz);
                textField.setText(obliczenia.showNumbers());
                String zWyswietlacza = textField.getText();
                if (zWyswietlacza.equals("wskaż ponownie liczby")){
                    komunikat.setText(m(6));
                    buttonGroup.clearSelection();
                }
                czasyOdpowiedzi = stoper.calculate();
                czasyOdpowiedziJLabel.setText(czasyOdpowiedzi);
                gregorianCalendar = new GregorianCalendar();
                date = gregorianCalendar.getTime();
//              System.out.println(date.toString());
                etykietaZData.setText("Obecny czas " + date.toString());
                wyswietlacz.setText("");
                wyswietlacz.requestFocusInWindow();
                }
        });
    }
    
    public void makeButtonExit(){
        buttonExit = new JButton("Zakończ");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonExit, new GBC(1,8).setAnchor(GBC.EAST));
    }
    
    public static String m(int mes){
        message = messageClass.m.get(mes);
        return message;        
    }
    
    public void utworzMessageRamka(){
    	messageFrame = new MessageFrame();
        messageFrame.setBounds(580, 0, 400, 400);
        messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageFrame.setVisible(true);
    }

    class MenuPlWew extends JPanel{
    	
    	public MenuPlWew(){
    				
    		//Utworzenie głównych elementów menu
    		JMenuBar menuBar = new JMenuBar();
    		JMenu menuPlik = new JMenu("Plik");
    		JMenu trybNauki = new JMenu("Tryb Nauki");
    		JMenu widok = new JMenu("Widok");
    		JMenu pomoc = new JMenu("Pomoc");
    		
    		//Utworzenie elementów podmenu i dodanie ich do menuPlik
    		JMenuItem otworzWyniki = menuPlik.add(new AbstractAction("Otwórz wyniki"){

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \"Otwórz wyniki\"");
    			}
    			
    		});
    		JMenuItem zapiszWyniki = menuPlik.add(new AbstractAction("Zapisz Wyniki"){

    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				System.out.println("element \"Zapisz wyniki\"");				
    			}
    			
    		});
    		JMenuItem zapiszJako = menuPlik.add(new AbstractAction("Zapisz jako") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \"Zapisz jako\"");
    				
    			}
    		});
    		JMenuItem drukujWyniki = menuPlik.add(new AbstractAction("Drukuj wyniki") {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				System.out.println("element \"Drukuj wyniki\"");
    				
    			}
    		});
    		JMenuItem wyjscie = menuPlik.add(new AbstractAction("Wyjście") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \"Wyjście\"");
    				System.exit(0);
    			}
    		});
    		
    		//Dodanie mnemoników i akcelelatorów w munuPlik
    		otworzWyniki.setMnemonic('O');
    		otworzWyniki.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
    		zapiszWyniki.setMnemonic('Z');
    		zapiszWyniki.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
    		drukujWyniki.setMnemonic('D');
    		drukujWyniki.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
    		wyjscie.setMnemonic('W');
    		
    		//Utworzenie elementów podmenu i dodanie ich do trybNauki
    		JMenuItem fiszki = trybNauki.add(new AbstractAction("Fiszki") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \" Fiszki \"");
    				
    			}
    		});
    		JMenuItem test = trybNauki.add(new AbstractAction("Test") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \" Test \"");
    				
    			}
    		});
    		//Dodanie mnemoników i akcelelatorów do trybNauki
    		fiszki.setMnemonic('F');
    		test.setMnemonic('T');
    		
    		//Utworzenie elementów podmenu i dodanie ich do menu widok
    		JMenuItem kalkulator = widok.add(new AbstractAction("Kalkulator") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \" Kalkulator \"");
    				calculator = new Calculator();
    				
    				calculator.setBounds(0, 400, 250, 250);
    				
    				calculator.setVisible(true);
    				calculator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    				
    			}
    		});
    		
    		JMenuItem nastepnyWyglad = widok.add(new AbstractAction("Następny wygląd") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \" Następny wygląd \"");
    				lookNumber++;
    				if (lookNumber == infos.length) lookNumber = 0;
    				try {
    					UIManager.setLookAndFeel(infos[lookNumber].getClassName());
    				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
    						| UnsupportedLookAndFeelException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				SwingUtilities.updateComponentTreeUI(Ramka.this);
    				
    			}
    		});
    		JMenuItem poprzedniWyglad = widok.add(new AbstractAction("Poprzedni wygląd") {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				System.out.println("element \" Poprzedni wygląd \"");
    				lookNumber--;
    				if (lookNumber <= 0 ) lookNumber = infos.length-1;
    				try {
    					UIManager.setLookAndFeel(infos[lookNumber].getClassName());
    				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
    						| UnsupportedLookAndFeelException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				SwingUtilities.updateComponentTreeUI(Ramka.this);
    			}
    		});
    		//Dodanie akceleratorów
    		kalkulator.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));
    		nastepnyWyglad.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
    		poprzedniWyglad.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
    		
    		//Utworzenie elementów podmenu i dodanie ich do menu pomoc
    		JMenuItem opisProgramu = pomoc.add(new AbstractAction("O programie") {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					messageFrame.dispose();
					utworzMessageRamka();
				}
			});
    		
    		//Dodanie elementów do paska menu
    		menuBar.add(menuPlik);
    		menuBar.add(trybNauki);
    		menuBar.add(widok);
    		menuBar.add(pomoc);
    		
    		//Tymczasowe wyłączenie elementów menu
    		otworzWyniki.setEnabled(false);
    		zapiszWyniki.setEnabled(false);
    		zapiszJako.setEnabled(false);
    		drukujWyniki.setEnabled(false);
    		fiszki.setEnabled(false);
    		test.setEnabled(false);
//    		kalkulator.setEnabled(false);
    		
    		add(menuBar);
    		//pack();
    	}
    }
}