
package StockExchange.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class Waluta extends Aktywa {
    private double cenaKupna;
    private double cenaSprzedazy;
    private List<String> listaKrajow;
    //losowanie krajów np.:
    //wylosuj liczbę mniejszą niż liczba krajów w tablicy
    //pętla for do wygenerowanej liczby powyżej
    //dla każdej iteracji znowu losujemy liczbę mniejszą niż powyższa
    //i inną niż ta, którą już wcześniej pobraliśmy
    //dodajemy kraj

    public Waluta(){

        ArrayList<String> krajeDoWyboru = new ArrayList<>(Arrays.asList(Kraje.kraje));
        listaKrajow = new ArrayList<>();
        Random generator = new Random();
        int lkrajow = generator.nextInt(4);
        for (int i = 0; i <= lkrajow; i++) {
            int element = generator.nextInt(krajeDoWyboru.size());
            this.listaKrajow.add(krajeDoWyboru.get(element));
            krajeDoWyboru.remove(element);
        }
        double cKupna = generator.nextDouble()*3;
        this.setCenaKupna(cKupna);
        this.setCenaSprzedazy(cKupna + generator.nextDouble()/5);

    }

    @Override
    public String toString(){
        String nazwa = this.getNazwa();
        return nazwa;
    }
    
    /**
     *
     * @param cenaKupna
     */
    public void setCenaKupna(double cenaKupna) {
        this.cenaKupna = cenaKupna;
    }

    /**
     *
     * @param cenaSprzedazy
     */
    public void setCenaSprzedazy(double cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    /**
     *
     * @param kraj
     */
    public void setListaKrajow(String kraj) {
        this.listaKrajow.add(kraj);
    }

    /**
     *
     * @return cenaKupna
     */
    public double getCenaKupna() {
        return cenaKupna;
    }

    /**
     *
     * @return cenaSprzedazy
     */
    public double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    /**
     *
     * @return listaKrajow
     */
    public List<String> getListaKrajow() {
        return listaKrajow;
    }
  
}
