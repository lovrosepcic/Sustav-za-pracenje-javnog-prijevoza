package hr.java.vjezbe.entiteti;

/**
 * Sučelje za praćenje lokacija objekta
 * Implementira funkcionalnost praćenja trenutne lokacije
 *
 * @author Student
 * @version 2.0
 */
public interface Pratljivo {

    /**
     * Dohvaća trenutnu lokaciju objekta
     *
     * @return lokacija
     */
    String dohvatiLokaciju();

    /**
     * Postavlja novu lokaciju objekta
     *
     * @param lokacija nova lokacija
     */
    void postaviLokaciju(String lokacija);
}