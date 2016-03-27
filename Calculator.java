package tabliczkamnozenia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator extends JFrame{
	
	private JButton wyswietlacz;
	private boolean startup;
	private boolean dot;
	private boolean pierwszyMinus;
	private String znak;
	private JButton button;
	private JButton c;
	private JPanel panel;
	private Listener listener;
//	private double wynik;
//	private double wynikKoncowy;
	private BigDecimal wynik;
	private BigDecimal wynikKoncowy;
	
	public Calculator(){
//		wynik = 0;
//		wynikKoncowy = 0;
		wynik = BigDecimal.valueOf(0);
		wynikKoncowy = BigDecimal.valueOf(0);
		startup = true;
		pierwszyMinus = false;
		dot = false;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		wyswietlacz = new JButton("0");
		wyswietlacz.setFont(new Font("Dialog", Font.BOLD, 24));
		wyswietlacz.setBackground(Color.BLUE);
		wyswietlacz.setForeground(Color.BLACK);
		wyswietlacz.setEnabled(false);
		
		BorderLayout layout = new BorderLayout();
		
		//Dodanie przycisków
		mB("7");
		mB("8");
		mB("9");
		mB("+");
		
		mB("4");
		mB("5");
		mB("6");
		mB("-");
		
		mB("1");
		mB("2");
		mB("3");
		mB("*");
		
		mB("0");
		mB(".");
		mB("=");
		mB("/");
		
		c = new JButton("C");
		c.setFont(new Font("Dialog", Font.BOLD, 18));
		c.setBackground(Color.BLUE);
		c.setForeground(Color.WHITE);
		c.addActionListener(new Listener());
		
		add(wyswietlacz, layout.NORTH);
		add(panel, layout.CENTER);
		add(c, layout.SOUTH);
	}
	
	public void mB(String s){
		button = new JButton(s);
		button.setFont(new Font("Dialog", Font.BOLD, 18));
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		listener = new Listener();
		button.addActionListener(listener);
		panel.add(button);
	}
	
	private class Listener extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			String przyciskString = e.getActionCommand();
//			System.out.println(przyciskString);
			
			switch (przyciskString) {
			case "+":{
				if (!startup) {
//					wynik = Double.parseDouble(wyswietlacz.getText());
					wynik = BigDecimal.valueOf(Double.parseDouble(wyswietlacz.getText()));
					oblicz();
					znak = "+";
				}
				else znak = "+";
				break;
			}
			case "-":{
//				if (startup && wynik == 0 && wynikKoncowy == 0) pierwszyMinus = true;
				if (startup && wynik.compareTo(BigDecimal.valueOf(0)) == 0 && wynikKoncowy.compareTo(BigDecimal.valueOf(0)) == 0){
					pierwszyMinus = true;
//					wyswietlacz.setText("-");
//					znak = "-";
				}
				else if (startup) znak = "-";
				else {
//					wynik = Double.parseDouble(wyswietlacz.getText());
					wynik = BigDecimal.valueOf(Double.parseDouble(wyswietlacz.getText()));
					oblicz();
					znak = "-";
//					System.out.println("wewn¹trz minusa wynik = "+ wynik + " wynikKoncowy = " + wynikKoncowy);
				}
				break;
			}
			case "/":{
				if (!startup) {
//					System.out.println("wewn¹trz dzielenia");
//					wynik = Double.parseDouble(wyswietlacz.getText());
					wynik = BigDecimal.valueOf(Double.parseDouble(wyswietlacz.getText()));
					oblicz();
					znak = "/";
				}
				else znak = "/";
				break;
			}
			case "*": {
				if (!startup) {
//					System.out.println("wewn¹trz mno¿enia");
//					wynik = Double.parseDouble(wyswietlacz.getText());
					wynik = BigDecimal.valueOf(Double.parseDouble(wyswietlacz.getText()));
					oblicz();
					znak = "*";
				}
				else znak = "*";
			}
				break;
			case ".": {
				if (dot);
				else {
					if (startup){
						if (pierwszyMinus) wyswietlacz.setText("-0.");
						else wyswietlacz.setText("0.");
						startup = false;
					}
					else wyswietlacz.setText(wyswietlacz.getText()+".");
				}
				dot = true;
			}
				break;
			case "=": 
				if (!startup){
//					wynik = Double.parseDouble(wyswietlacz.getText());
					wynik = BigDecimal.valueOf(Double.parseDouble(wyswietlacz.getText()));
					oblicz();
					znak = null;
				}
				break;
			case "C": {
//				wynik = 0;
//				wynikKoncowy = 0;
				wynik = BigDecimal.valueOf(0);
				wynikKoncowy = BigDecimal.valueOf(0);
				startup = true;
				pierwszyMinus = false;
				dot = false;
				wyswietlacz.setText("0");
				znak = null;
				break;
			}	
			default: {
				if (startup){
					if (pierwszyMinus) {
						wyswietlacz.setText("-"+przyciskString);
						
						startup = false;
					}
					else {
						wyswietlacz.setText(przyciskString);
						startup = false;
					}
				}
				else {
					wyswietlacz.setText(wyswietlacz.getText() + przyciskString);
					if (wyswietlacz.getText().equals("00")){
						wyswietlacz.setText("0");
					}
				}
			};
			}

		}
		
		public void oblicz() {
			if (znak == null) {
				wynikKoncowy = wynik;
				startup = true;
			}
//			else if (znak == "/" && wynik == 0){
			else if (znak == "/" && wynik.compareTo(BigDecimal.valueOf(0)) == 0){
				wyswietlacz.setText("Dzielenie przez 0");
//				wynik = 0;
//				wynikKoncowy = 0;
				wynik = BigDecimal.valueOf(0);
				wynikKoncowy = BigDecimal.valueOf(0);
				znak = null;
				startup = true;
			}
			else {
				switch (znak) {
				case "+": {
//					wynikKoncowy = wynikKoncowy + wynik;
					wynikKoncowy = wynikKoncowy.add(wynik);
					wyswietlacz.setText("" + wynikKoncowy);
					break;
				}
				case "-": {
//					wynikKoncowy = wynikKoncowy - wynik;
					wynikKoncowy = wynikKoncowy.subtract(wynik);
					wyswietlacz.setText("" + wynikKoncowy);
					break;
				}
				case "*": {
//					wynikKoncowy *= wynik;
					wynikKoncowy = wynikKoncowy.multiply(wynik);
					wyswietlacz.setText("" + wynikKoncowy);
					break;
				}
				case "/": {
//					wynikKoncowy /= wynik;
					wynikKoncowy = wynikKoncowy.divide(wynik);
					wyswietlacz.setText("" + wynikKoncowy);
					break;
				}
				}
				startup = true;
			}
			dot = false;
			pierwszyMinus = false;
		}
		
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setDefaultCloseOperation(EXIT_ON_CLOSE);
		calculator.pack();
		calculator.setResizable(false);
		calculator.setVisible(true);
		
	}

}
