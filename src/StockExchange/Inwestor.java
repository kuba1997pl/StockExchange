
package StockExchange;

import java.util.*;

/**
 * @author jakub
 */
public class Inwestor extends Klient {
    private String imie;
    private String nazwisko;
    private String PESEL;
    private double budzet;

    public static String[] IMIONA = {
            "Christian",
            "Bill",
            "Tony",
            "Lucjusz",
            "Zdzisław",
            "Jarosław",
            "Marian",
            "Gordon",
            "Bruce",
            "Jordan",
            "Donald",
            "Dariusz",
    };
    public static String[] NAZWISKA = {
            "Malfoy",
            "Majchrzak",
            "Kowalski",
            "Szczygieł",
            "Gekko",
            "Wayne",
            "Belfort",
            "Trump",
            "Brzezinski"
    };

    public Inwestor() {
//        ArrayList<String> nazwiska = new ArrayList();
//        ArrayList<String> imiona = new ArrayList();
//         powyższe listy trzeba dodać do metody,
//        która będzie dodawać inwestorów
//         i po dodaniu usuwać danego inwestora z listy
//         aby nie był dodany ponownie
//        for(int i=0; i<IMIONA.length(); i++){
//            imiona.add(IMIONA[i]);
//        }
//        for(int i=0; i<NAZWISKA.length(); i++){
//            imiona.add(NAZWISKA[i]);
//        }
        Random generator = new Random();
        StringBuilder builder = new StringBuilder();
        this.PESEL = builder
                .append(getTwoDigitNumberString(generator.nextInt(100))) //rok
                .append(getTwoDigitNumberString(generator.nextInt(12) + 1)) //miesiac
                .append(getTwoDigitNumberString(generator.nextInt(28) + 1)) // dzien
                .append(getTwoDigitNumberString(generator.nextInt(100000))).toString();
        this.budzet = generator.nextDouble() * 1000000.0;
        System.out.println(this.budzet + "\n" + this.PESEL);
    }

    private String getTwoDigitNumberString(int i) {
        if(i < 10) {
            return String.valueOf(0) + i;
        } else {
            return String.valueOf(i);
        }
    }


    public void kupujAktywaSamemu() {

    }

    ;

    public void sprzedajAktywaSamemu() {

    }

    public void kupujPrzezFundusz() {

    }

    public void sprzedajPrzezFundusz() {

    }


    /**
     * @return imie
     */
    public String getImie() {
        return imie;
    }

    /**
     * @return nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL;
    }

    /**
     * @return budzet
     */
    public double getBudzet() {
        return budzet;
    }

    /**
     * @param imie
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * @param nazwisko
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    /**
     * @param budzet
     */
    public void setBudzet(double budzet) {
        this.budzet = budzet;
    }
}
