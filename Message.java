package tabliczkamnozenia;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Message {
    public ArrayList<String> m;
    
    public Message() {
        m = new ArrayList<>();
        addMessage();
        
    }
    public void addMessage(){
        m.add("Nauka tabliczki mnożenia");
        m.add("Zaznacz, które liczby chcesz ćwiczyć");
        m.add("       Podaj wynik mnożenia:       ");
        m.add("Wskaż liczby");
        m.add("Okno komunikatów \n Wybierz liczby, które chciałbyś przećwiczyć");
        m.add("Tutaj wpisz wynik");
        m.add("Wieeeeeeeeeelkie brawa \n Wszystkie liczby zostały przećwiczone. \n Wskaż ponownie liczby - spróbuj jeszcze raz ");
        System.out.println("Ostatni komunikat ma nr: " + m.size());
    }
}
