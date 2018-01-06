
package StockExchange.model;

import java.util.*;

/**
 * @author jakub
 */
public class Investor extends Customer {
    private String firstName;
    private String lastName;
    private String PESEL;
    private double budget;

    public static String[] FIRSTNAMES = {
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
    public static String[] LASTNAMES = {
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

    public Investor() {
//        ArrayList<String> lastNames = new ArrayList();
//        ArrayList<String> firstNames = new ArrayList();
//         powyższe listy trzeba dodać do metody,
//        która będzie dodawać inwestorów
//         i po dodaniu usuwać danego inwestora z listy
//         aby nie był dodany ponownie
//        for(int i=0; i<FIRSTNAMES.length(); i++){
//            firstNames.add(FIRSTNAMES[i]);
//        }
//        for(int i=0; i<LASTNAMES.length(); i++){
//            firstNames.add(LASTNAMES[i]);
//        }
        Random generator = new Random();
        StringBuilder builder = new StringBuilder();
        this.PESEL = builder
                .append(getTwoDigitNumberString(generator.nextInt(100))) //rok
                .append(getTwoDigitNumberString(generator.nextInt(12) + 1)) //miesiac
                .append(getTwoDigitNumberString(generator.nextInt(28) + 1)) // dzien
                .append(getTwoDigitNumberString(generator.nextInt(100000))).toString();
        this.budget = generator.nextDouble() * 1000000.0;
        System.out.println(this.budget + "\n" + this.PESEL);
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

    public void sprzedajAktywaSamemu() {

    }

    public void kupujPrzezFundusz() {

    }

    public void sprzedajPrzezFundusz() {

    }


    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL;
    }

    /**
     * @return budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    /**
     * @param budget
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }
}
