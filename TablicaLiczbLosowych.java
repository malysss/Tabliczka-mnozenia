
package tabliczkamnozenia;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa do generowania tablic typu ArrayList oraz int[] o zadanej ilości kolejnych 
 * liczb całkowitych ułożonych losowo bez powtórzeń
 * @author Bartosz Malysa
 */
public class TablicaLiczbLosowych {
    private ArrayList arrayRandTab;
    private int[] randTab;
    Random rand = new Random();
    /**
     * Konstruktor generujący dwie tablice ArrayList oraz int[]
     * @param howManyNumbers ilość elementów
     */
    public TablicaLiczbLosowych(int howManyNumbers) {
        setArrayTab(howManyNumbers);
        setRandTab(howManyNumbers);
    }
    
    public ArrayList getArrayTab(){
        return arrayRandTab;
    }
    /**
     * Metoda do utworzenia tablicy typu ArrayList
     * @param howMany ilość elementów
     */
    public void setArrayTab(int howMany){
        arrayRandTab = new ArrayList(howMany);
        
        ArrayList array = new ArrayList(howMany);
        for (int i=0; i<howMany; i++){
            array.add(i);
        }
        for (int i=0; i<howMany; i++){
            int elementLosowy = rand.nextInt(howMany - i);
            //System.out.println(element);
            arrayRandTab.add(array.get(elementLosowy));
            array.remove(elementLosowy);
        }
    }
    
    public int[] getRandTab(){
        return randTab;
    }
    /**
     * Metoda do utworzenia tablicy typu int[]
     * @param howMany ilość elementów
     */        
    public void setRandTab(int howMany){
        randTab = new int[howMany];
        
        ArrayList array = new ArrayList(howMany);
        for (int i=0; i<howMany; i++){
            array.add(i);
        }
        for (int i=0; i<howMany; i++){
            int elementLosowy = rand.nextInt(howMany - i);
            //System.out.println(element);
            randTab[i] = (int) array.get(elementLosowy);
            array.remove(elementLosowy);
        }
        
    }
}
