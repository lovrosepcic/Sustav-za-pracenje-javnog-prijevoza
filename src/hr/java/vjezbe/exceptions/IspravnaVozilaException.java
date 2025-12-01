package hr.java.vjezbe.exceptions;

/**
 * Prilagođena checked iznimka za neispravne podatke o vozilu
 * Baca se kada korisnik unese neispravne podatke o vozilu
 */
public class IspravnaVozilaException extends Exception {

    /**
     * Konstruktor s porukom greška
     */
    public IspravnaVozilaException(String poruka) {
        super(poruka);
    }
}