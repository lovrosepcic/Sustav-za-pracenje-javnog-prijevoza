package hr.java.vjezbe.exceptions;

/**
 * Iznimka koja se baca kada je unesen negativan iznos
 * koji nije dozvoljen u sustavu.
 */
public class NegativanIznosException extends RuntimeException {
    /**
     * Stvara novu NegativanIznosException s navedenom porukom.
     */
    public NegativanIznosException(String message) {
        super(message);
    }
}
