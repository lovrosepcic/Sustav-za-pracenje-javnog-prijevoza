package hr.java.vjezbe.exceptions;

/**
 * Prilagođena checked iznimka za neispravne podatke o vozilu
 * Baca se kada korisnik unese neispravne podatke o vozilu
 *
 * @author Student
 * @version 1.0
 */
public class IspravnaVozilaException extends Exception {

    /**
     * Konstruktor s porukom greške
     *
     * @param poruka opis greške koja se dogodila
     */
    public IspravnaVozilaException(String poruka) {
        super(poruka);
    }
}