package tabliczkamnozenia;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class Obliczenia {
    int jakieLiczby;
    int pomocnicza;
    int licznik;
    TablicaLiczbLosowych tablica;
    //int[] liczby;
    ArrayList liczby;
    int wynik;
    int iloczyn;
    int liczbaZTablicy;
    Ramka ramka;
    
    public Obliczenia(int whatNumber){
        jakieLiczby = whatNumber;
        licznik = 0;
        tablica = new TablicaLiczbLosowych(10);
        //liczby = tablica.getRandTab();
        liczby = tablica.getArrayTab();
        ramka = new Ramka();
    }
    
    public String showNumbers(){
        while (liczby.size() != 0) {
            liczbaZTablicy = (int) liczby.get(0);
            String string = jakieLiczby + " x " + liczbaZTablicy;
            System.out.println(liczby.toString());
            return string;
        }
        return "wskaż ponownie liczby";
        //liczbaZTablicy = liczby[pomocnicza++];
        //liczbaZTablicy = (int) liczby.get(licznik++);
        
    }
    
    public String sprawdz(int wynik) throws InterruptedException{
        iloczyn = jakieLiczby * liczbaZTablicy;
        this.wynik = wynik;
        //System.out.println(iloczyn);
        System.out.println("Wewnątrz obliczenia " + wynik);
        if (iloczyn == wynik) {
            System.out.println("Brawo");
            liczby.remove(0);
            return "Brawo";
        }
        else if (wynik == -987654){
            System.out.println("Wewnątrz Obliczenia else if" + wynik);
            return "Dozwolone jest podawanie \n WYŁĄCZNIE \n liczb całkowitych";
        }
        else {
            System.out.println("Zła odpowiedź");
            pomocnicza = (int) liczby.get(0);
            System.out.println("pomocnicza: " + pomocnicza);
            liczby.remove(0);
            liczby.add(pomocnicza);
            System.out.println("pierwszy element: " + liczby.get(0) + " ostatni element: " + liczby.get(liczby.size()-1) + " Ilość elementów w tablicy: "
                    + liczby.size() + " element - licznik wynosi: " + liczby.get(0));
            return "Zła odpowiedź \n poprawny wynik działania to \n" + jakieLiczby + " x " + liczbaZTablicy + " = " + iloczyn;

        }
        
    }
    
}
