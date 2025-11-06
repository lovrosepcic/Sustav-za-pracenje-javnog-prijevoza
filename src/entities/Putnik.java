package entities;

/**
 * Klasa Putnik predstavlja putnika u autobusu
 * Nasljeđuje apstraktnu klasu Osoba
 *
 * @author Student
 * @version 2.0
 */
public class Putnik extends Osoba {
    private String brojKarte;

    /**
     * Konstruktor za inicijalizaciju putnika
     *
     * @param ime ime putnika
     * @param prezime prezime putnika
     * @param godine godine putnika
     * @param brojKarte broj putne karte
     */
    public Putnik(String ime, String prezime, int godine, String brojKarte) {
        super(ime, prezime, godine);
        this.brojKarte = brojKarte;
    }

    /**
     * Dohvaća broj putne karte
     *
     * @return broj karte
     */
    public String getBrojKarte() {
        return brojKarte;
    }

    /**
     * Postavlja novi broj putne karte
     *
     * @param brojKarte novi broj karte
     */
    public void setBrojKarte(String brojKarte) {
        this.brojKarte = brojKarte;
    }

    /**
     * Prikazuje informacije o putniku
     * Implementacija apstraktne metode iz Osoba klase
     */
    @Override
    public void predstaviSe() {
        System.out.println("Putnik: " + getIme() + " " + getPrezime() +
                ", godine: " + getGodine() + ", karta: " + brojKarte);
    }
}