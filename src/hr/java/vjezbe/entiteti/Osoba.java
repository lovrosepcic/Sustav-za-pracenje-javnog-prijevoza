package hr.java.vjezbe.entiteti;

/**
 * Apstraktna klasa koja predstavlja osobu u sustavu javnog prijevoza
 * Nadklasa je za Vozac i Putnik
 *
 * @author Student
 * @version 2.0
 */
public abstract class Osoba {
    private String ime;
    private String prezime;
    private int godine;

    /**
     * Konstruktor za inicijalizaciju osobe
     *
     * @param ime ime osobe
     * @param prezime prezime osobe
     * @param godine godine osobe
     */
    public Osoba(String ime, String prezime, int godine) {
        this.ime = ime;
        this.prezime = prezime;
        this.godine = godine;
    }

    /**
     * Dohvaća ime osobe
     *
     * @return ime osobe
     */
    public String getIme() {
        return ime;
    }

    /**
     * Dohvaća prezime osobe
     *
     * @return prezime osobe
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Dohvaća godine osobe
     *
     * @return godine osobe
     */
    public int getGodine() {
        return godine;
    }

    /**
     * Postavlja novo ime osobe
     *
     * @param ime novo ime
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Postavlja novo prezime osobe
     *
     * @param prezime novo prezime
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Postavlja nove godine osobe
     *
     * @param godine nove godine
     */
    public void setGodine(int godine) {
        this.godine = godine;
    }

    /**
     * Apstraktna metoda koju sve podklase moraju implementirati
     * Prikazuje informacije o osobi
     */
    public abstract void predstaviSe();
}