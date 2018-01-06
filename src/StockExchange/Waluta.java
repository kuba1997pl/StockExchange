
package StockExchange;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class Waluta extends Aktywa {
    private double cenaKupna;
    private double cenaSprzedazy;
    private List<String> listaKrajow = new ArrayList<>();
    //losowanie krajów np.:
    //wylosuj liczbę mniejszą niż liczba krajów w tablicy
    //pętla for do wygenerowanej liczby powyżej
    //dla każdej iteracji znowu losujemy liczbę mniejszą niż powyższa
    //i inną niż ta, którą już wcześniej pobraliśmy
    //dodajemy kraj

    
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
     * @param listaKrajow
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
