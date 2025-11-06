package exceptions;

/**
 * Prilagođena checked iznimka za neispravne podatke o vozaču
 * Baca se kada korisnik unese neispravne podatke (npr. string umjesto broja)
 *
 * @author Student
 * @version 1.0
 */
public class IspravnaVozacaException extends Exception {

    /**
     * Konstruktor s porukom greške
     *
     * @param poruka opis greške koja se dogodila
     */
    public IspravnaVozacaException(String poruka) {
        super(poruka);
    }
}