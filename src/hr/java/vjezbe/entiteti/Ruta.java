package hr.java.vjezbe.entiteti;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Ruta predstavlja autobusnu liniju s listama vozača i putnika
 *
 * @author Student
 * @version 4.0
 */
public class Ruta {
    private String polaziste;
    private String odrediste;
    private double udaljenost;
    private List<Vozac> vozaci;
    private List<Putnik> putnici;

    /**
     * Konstruktor za inicijalizaciju rute
     */
    public Ruta(String polaziste, String odrediste, double udaljenost) {
        this.polaziste = polaziste;
        this.odrediste = odrediste;
        this.udaljenost = udaljenost;
        this.vozaci = new ArrayList<>();
        this.putnici = new ArrayList<>();
    }

    public String getPolaziste() {
        return polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public double getUdaljenost() {
        return udaljenost;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public void setUdaljenost(double udaljenost) {
        this.udaljenost = udaljenost;
    }

    /**
     * Dodaje vozača na rutu
     */
    public void dodajVozaca(Vozac vozac) {
        vozaci.add(vozac);
    }

    /**
     * Dodaje putnika na rutu
     */
    public void dodajPutnika(Putnik putnik) {
        putnici.add(putnik);
    }

    /**
     * Dohvaća broj putnika
     */
    public int getBrojPutnika() {
        return putnici.size();
    }

    /**
     * Dohvaća broj vozača
     */
    public int getBrojVozaca() {
        return vozaci.size();
    }

    /**
     * Dohvaća listu putnika
     */
    public List<Putnik> getPutnici() {
        return putnici;
    }

    /**
     * Dohvaća listu vozača
     */
    public List<Vozac> getVozaci() {
        return vozaci;
    }
}
