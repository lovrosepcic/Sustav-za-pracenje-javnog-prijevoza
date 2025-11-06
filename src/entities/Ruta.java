package entities;

/**
 * Klasa Ruta predstavlja autobusnu liniju/rutu
 * Povezuje vozilo, vozača i stanice
 *
 * @author Student
 * @version 2.0
 */
public class Ruta {
    private int brojLinije;
    private String pocetnaStanica;
    private String zavrsnaStanica;
    private Vozilo vozilo;
    private Vozac vozac;

    /**
     * Konstruktor za inicijalizaciju rute
     *
     * @param brojLinije broj linije
     * @param pocetnaStanica početna stanica
     * @param zavrsnaStanica završna stanica
     * @param vozilo vozilo koje vozi rutu
     * @param vozac vozač koji vozi
     */
    public Ruta(int brojLinije, String pocetnaStanica, String zavrsnaStanica, Vozilo vozilo, Vozac vozac) {
        this.brojLinije = brojLinije;
        this.pocetnaStanica = pocetnaStanica;
        this.zavrsnaStanica = zavrsnaStanica;
        this.vozilo = vozilo;
        this.vozac = vozac;
    }

    /**
     * Dohvaća broj linije
     *
     * @return broj linije
     */
    public int getBrojLinije() {
        return brojLinije;
    }

    /**
     * Dohvaća početnu stanicu
     *
     * @return početna stanica
     */
    public String getPocetnaStanica() {
        return pocetnaStanica;
    }

    /**
     * Dohvaća završnu stanicu
     *
     * @return završna stanica
     */
    public String getZavrsnaStanica() {
        return zavrsnaStanica;
    }

    /**
     * Dohvaća vozilo
     *
     * @return vozilo
     */
    public Vozilo getVozilo() {
        return vozilo;
    }

    /**
     * Dohvaća vozača
     *
     * @return vozač
     */
    public Vozac getVozac() {
        return vozac;
    }

    /**
     * Postavlja broj linije
     *
     * @param brojLinije novi broj linije
     */
    public void setBrojLinije(int brojLinije) {
        this.brojLinije = brojLinije;
    }

    /**
     * Postavlja početnu stanicu
     *
     * @param pocetnaStanica nova početna stanica
     */
    public void setPocetnaStanica(String pocetnaStanica) {
        this.pocetnaStanica = pocetnaStanica;
    }

    /**
     * Postavlja završnu stanicu
     *
     * @param zavrsnaStanica nova završna stanica
     */
    public void setZavrsnaStanica(String zavrsnaStanica) {
        this.zavrsnaStanica = zavrsnaStanica;
    }

    /**
     * Postavlja vozilo
     *
     * @param vozilo novo vozilo
     */
    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    /**
     * Postavlja vozača
     *
     * @param vozac novi vozač
     */
    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }
}