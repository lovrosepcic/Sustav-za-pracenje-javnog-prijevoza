package hr.java.vjezbe.entiteti;

/**
 * Record klasa za reprezentaciju informacija o putniku
 * Nepromjenjiva klasa s automatski generiranim getter metodama
 *
 * @author Student
 * @version 2.0
 * @param ime ime putnika
 * @param prezime prezime putnika
 * @param brojKarte broj putne karte
 * @param starost starost putnika
 */
public record PutnikInfo(String ime, String prezime, String brojKarte, int starost) {

    /**
     * Kompaktni konstruktor za validaciju podataka
     */
    public PutnikInfo {
        if (starost < 0) {
            starost = 0;
        }
    }

    /**
     * Provjera je li putnik dijete (manje od 18 godina)
     *
     * @return true ako je starost manja od 18, inače false
     */
    public boolean jeDijete() {
        return starost < 18;
    }
}