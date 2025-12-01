package hr.java.vjezbe.exceptions;

/**
 * Prilagođena checked iznimka za neispravne podatke o vozaču
 * Baca se kada korisnik unese neispravne podatke (npr. string umjesto broja)
 */
public class NeispravniPodaciVozacaException extends Exception {

    /**
     * Konstruktor s porukom greška
     */
    public NeispravniPodaciVozacaException(String poruka) {
        super(poruka);
    }
}