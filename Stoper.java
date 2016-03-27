package tabliczkamnozenia;

import javax.swing.Timer;

public class Stoper {
	private long czas1; 
	private long czas2;
	private long czasPomocniczy;
	private long przedzialCzasu;
	private String wyliczString;
	private boolean pierwszyBoolean;
	
	public Stoper(){
		//uruchomienie pomiaru czasu
		czas1 = System.currentTimeMillis();
		System.out.println(czas1+ " w konstruktorze");
		pierwszyBoolean = true;  //Do określenia, czy klasa została po raz pierwszy załadowana
	}
	
	public void czasStart(){
		czas1 = System.currentTimeMillis();
		System.out.println(czas1 + " w czasStart");
	}
	
	public String calculate(){
		czas2 = System.currentTimeMillis();
		System.out.println(czas2 + " w calculate");
		przedzialCzasu = czas2 - czas1;
		czas1 = czas2; //Będzie wykorzystane do pomiaru czasu odpowiedzi
		
		if (pierwszyBoolean) {
			wyliczString = Long.toString(przedzialCzasu);
			char[] znakiWTablicy = wyliczString.toCharArray();
			char ostatniaLitera = znakiWTablicy[znakiWTablicy.length-1];
			if (ostatniaLitera=='0'||ostatniaLitera=='1'||ostatniaLitera=='5'||ostatniaLitera=='6'
					||ostatniaLitera=='7'||ostatniaLitera=='8'||ostatniaLitera=='9'){
//				System.out.println("Zajęło to "+wylicz+" milisekund");
				pierwszyBoolean = false;
				return "Załadowanie programu zajęło " + wyliczString + " milisekund";
			}
			else {
//				System.out.println("Zajęło to "+wyliczString+" milisekundy");
				pierwszyBoolean = false;
				return "Załadowanie programu zajęło " + wyliczString + " milisekundy";
			}
		}
		else{
			
			int konwersjaNaInt = Math.round(przedzialCzasu /= 1000); 
			wyliczString = Long.toString(przedzialCzasu);
			char[] znakiWTablicy = wyliczString.toCharArray();
			char ostatniaLitera = znakiWTablicy[znakiWTablicy.length-1];
			if (ostatniaLitera == '1'){
				pierwszyBoolean = false;
				return "Podanie odpowiedzi zajęło " + wyliczString + " sekundę";
			}
			else if (ostatniaLitera=='0'||ostatniaLitera=='5'||ostatniaLitera=='6'
					||ostatniaLitera=='7'||ostatniaLitera=='8'||ostatniaLitera=='9'){
				pierwszyBoolean = false;
				return "Podanie odpowiedzi zajęło " + wyliczString + " sekund";
			}
			else {
				pierwszyBoolean = false;
				return "Podanie odpowiedzi zajęło " + wyliczString + " sekundy";
			}
		}
		
				
		//System.out.println("Ilość znaków to: "+znakiWTablicy.length);
		//System.out.println("Ostatni znak to: "+ostatniaLitera);

	}
//	public static void main(String[] args){
//		Stoper stoper = new Stoper();
//		stoper.calculate();
//	}
}
