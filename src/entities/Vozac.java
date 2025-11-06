package entities;

/**
 * Klasa Vozac predstavlja vozača autobusa
 * Nasljeđuje apstraktnu klasu Osoba
 *
 * @author Student
 * @version 2.0
 */
public class Vozac extends Osoba {
    private String brojDozvole;

    /**
     * Konstruktor za inicijalizaciju vozača
     *
     * @param ime ime vozača
     * @param prezime prezime vozača
     * @param godine godine vozača
     * @param brojDozvole broj vozačke dozvole
     */
    public Vozac(String ime, String prezime, int godine, String brojDozvole) {
        super(ime, prezime, godine);
        this.brojDozvole = brojDozvole;
    }

    /**
     * Dohvaća broj vozačke dozvole
     *
     * @return broj dozvole
     */
    public String getBrojDozvole() {
        return brojDozvole;
    }

    /**
     * Postavlja novi broj vozačke dozvole
     *
     * @param brojDozvole novi broj dozvole
     */
    public void setBrojDozvole(String brojDozvole) {
        this.brojDozvole = brojDozvole;
    }

    /**
     * Prikazuje informacije o vozaču
     * Implementacija apstraktne metode iz Osoba klase
     */
    @Override
    public void predstaviSe() {
        System.out.println("Vozac: " + getIme() + " " + getPrezime() +
                ", godine: " + getGodine() + ", dozvola: " + brojDozvole);
    }
}