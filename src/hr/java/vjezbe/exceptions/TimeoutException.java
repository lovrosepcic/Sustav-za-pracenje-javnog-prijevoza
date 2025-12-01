package hr.java.vjezbe.exceptions;

/**
 * Iznimka koja označava da je neka operacija trajala predugo
 * i da je prekoračeno dopušteno vrijeme izvršavanja.
 */
public class TimeoutException extends RuntimeException {

    /**
     * Stvara novu TimeoutException s navedenom porukom.
     */
    public TimeoutException(String message) {
        super(message);
    }
}
