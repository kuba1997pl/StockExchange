
package StockExchange.model;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author jakub
 */

public class Spolka {
    private String nazwa;
    private LocalDate dataIWyceny;
    private double kursMin;
    private double kursMax;
    private double kursAkt;
    private int liczbaAkcji;
    private double zysk; // dochód = przychód - koszta 
    private double przychod; 
    private double kapitalWlasny;
    private double kapitalZakladowy;
    private int wolumen;
    private double obroty; //przychód - podatek

    
    public Spolka(){
        Random generator = new Random();
        double min = generator.nextDouble() * 10;
        double maks = min + generator.nextDouble() * 20;
        int akcyje = generator.nextInt(1000);
        this.kursMin = min;
        this.kursMax = maks;
        this.kursAkt = (min+maks)/2;
        this.liczbaAkcji = akcyje;

        //date generator
        int minDz = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDz = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long losDz = minDz + generator.nextInt(maxDz - minDz);
        this.dataIWyceny = LocalDate.ofEpochDay(losDz);
        this.wolumen = generator.nextInt(1000);
        //System.out.println(this.dataIWyceny+"\n"+this.wolumen+"\n"+this.kursMax+"\n"+this.kursMin);
    }



    /**
     *
     * @param kursAkt
     */

    public void setKursAkt(double kursAkt) {
        this.kursAkt = kursAkt;
    }

    /**
     *
     * @return kursAkt
     */

    public double getKursAkt() {

        return kursAkt;
    }

    /**
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     *
     * @param dataIwyceny
     */
    public void setDataIWyceny(LocalDate dataIwyceny) {
        this.dataIWyceny = dataIWyceny;
    }

    /**
     *
     * @param kursMin
     */
    public void setKursMin(double kursMin) {
        this.kursMin = kursMin;
    }

    /**
     *
     * @param kursMax
     */
    public void setKursMax(double kursMax) {
        this.kursMax = kursMax;
    }

    /**
     *
     * @param liczbaAkcji
     */
    public void setLiczbaAkcji(int liczbaAkcji) {
        this.liczbaAkcji = liczbaAkcji;
    }

    /**
     *
     * @param zysk
     */
    public void setZysk(int zysk) {
        this.zysk = zysk;
    }

    /**
     *
     * @param przychod
     */
    public void setPrzychod(int przychod) {
        this.przychod = przychod;
    }

    /**
     *
     * @param kapitalWlasny
     */
    public void setKapitalWlasny(int kapitalWlasny) {
        this.kapitalWlasny = kapitalWlasny;
    }

    /**
     *
     * @param kapitalZakladowy
     */
    public void setKapitalZakladowy(int kapitalZakladowy) {
        this.kapitalZakladowy = kapitalZakladowy;
    }

    /**
     *
     * @param wolumen
     */
    public void setWolumen(int wolumen) {
        this.wolumen = wolumen;
    }

    /**
     *
     * @param obroty
     */
    public void setObroty(int obroty) {
        this.obroty = obroty;
    }

    /**
     *
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     *
     * @return dataIwyceny
     */
    public LocalDate getDataIWyceny() {
        return dataIWyceny;
    }

    /**
     *
     * @return kursMin
     */
    public double getKursMin() {
        return kursMin;
    }

    /**
     * 
     * @return kursMax
     */
    public double getKursMax() {
        return kursMax;
    }

    /**
     *
     * @return liczbaAkcji
     */
    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    /**
     *
     * @return zysk
     */
    public double getZysk() {
        return zysk;
    }

    /**
     *
     * @return przychod
     */
    public double getPrzychod() {
        return przychod;
    }

    /**
     *
     * @return kapitalWlasny
     */
    public double getKapitalWlasny() {
        return kapitalWlasny;
    }

    /**
     *
     * @return kapitalZakladowy
     */
    public double getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    /**
     *
     * @return wolumen
     */
    public int getWolumen() {
        return wolumen;
    }

    /**
     *
     * @return obroty
     */
    public double getObroty() {
        return obroty;
    }
}
