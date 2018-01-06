
package StockExchange;

/**
 * @author jakub
 */
public class FunduszInwestycyjny extends Klient {
    private String nazwa;
    private String imieZarzadcy;
    private String nazwiskoZarzadcy;

    public static String[] NAZWY = {
            "Rób Pieniążki Zawodowo",
            "Śpij na pieniądzach!",
            "Dreams fulfiller",
            "Du hast was du willst",
            "Może nie najtaniej, ale jako tako",
            "Agresywny",
            "Stabilność przede wszystkim"
    };
    public static String[] IMIONA = {
            "Zdzisław",
            "Piotr",
            "Andrzej",
            "Janusz",
            "Helmut",
            "Bożydar",
            "James"
    };
    public static String NAZWISKA[] = {
            "Masztalerz",
            "Wąsik",
            "Boligłowa",
            "Dusigrosz",
            "Rozrzutny",
            "Chytry",
            "Szkodnik"

    };

    /**
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * @return imieZarzadcy
     */
    public String getImieZarzadcy() {
        return imieZarzadcy;
    }

    /**
     * @return nazwiskoZarzadcy
     */
    public String getNazwiskoZarzadcy() {
        return nazwiskoZarzadcy;
    }

    /**
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @param imieZarzadcy
     */
    public void setImieZarzadcy(String imieZarzadcy) {
        this.imieZarzadcy = imieZarzadcy;
    }

    /**
     * @param nazwiskoZarzadcy
     */
    public void setNazwiskoZarzadcy(String nazwiskoZarzadcy) {
        this.nazwiskoZarzadcy = nazwiskoZarzadcy;
    }


    public void kupujAktywa() {

    }

    public void sprzedajAktywa() {

    }
}
