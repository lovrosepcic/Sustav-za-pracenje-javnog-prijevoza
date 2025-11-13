package hr.java.vjezbe.entiteti;

/**
 * Sealed sučelje za održavanje vozila
 * Samo klasa Vozilo može ga implementirati
 *
 * @author Student
 * @version 2.0
 */
public sealed interface Odrzavanje permits Vozilo {

    /**
     * Metoda za obavljanje održavanja vozila
     */
    void obavljajOdrzavanje();

    /**
     * Dohvaća broj preostalih dana do sljedećeg održavanja
     *
     * @return broj dana
     */
    int daniDoSljedecegOdrzavanja();
}