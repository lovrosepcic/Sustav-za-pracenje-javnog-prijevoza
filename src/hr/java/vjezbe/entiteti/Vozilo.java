package hr.java.vjezbe.entiteti;
import java.io.Serializable;
/**
 * Klasa Vozilo predstavlja autobus sa svim relevantnim informacijama
 * Koristi Builder Pattern za jednostavnije kreiranje objekata
 * Implementira sučelja Odrzavanje i Pratljivo
 *
 * @author Student
 * @version 2.0
 */
public final class Vozilo implements Odrzavanje, Pratljivo, Serializable {
    private static final long serialVersionUID = 2L;
    private String registracija;
    private String model;
    private int brojSjedala;
    private int godinaProizvodnje;
    private int daniOdZadnjegOdrzavanja;
    private String trenutnaLokacija;

    public static VoziloBuilder defaultBuilder() {
        return new VoziloBuilder("", "");
    }

    /**
     * Privatni konstruktor - koristi se samo kroz Builder
     *
     * @param builder Builder objekt koji sadrži sve podatke
     */
    private Vozilo(VoziloBuilder builder) {
        this.registracija = builder.registracija;
        this.model = builder.model;
        this.brojSjedala = builder.brojSjedala;
        this.godinaProizvodnje = builder.godinaProizvodnje;
        this.daniOdZadnjegOdrzavanja = builder.daniOdZadnjegOdrzavanja;
        this.trenutnaLokacija = builder.trenutnaLokacija;
    }

    /**
     * Dohvaća registraciju vozila
     *
     * @return registracijska pločica
     */
    public String getRegistracija() {
        return registracija;
    }

    /**
     * Dohvaća model vozila
     *
     * @return naziv modela
     */
    public String getModel() {
        return model;
    }

    /**
     * Dohvaća broj sjedala
     *
     * @return broj sjedala
     */
    public int getBrojSjedala() {
        return brojSjedala;
    }

    /**
     * Dohvaća godinu proizvodnje
     *
     * @return godina proizvodnje
     */
    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    /**
     * Postavlja registraciju vozila
     *
     * @param registracija nova registracija
     */
    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    /**
     * Postavlja model vozila
     *
     * @param model novi model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Postavlja broj sjedala
     *
     * @param brojSjedala novi broj sjedala
     */
    public void setBrojSjedala(int brojSjedala) {
        this.brojSjedala = brojSjedala;
    }

    /**
     * Postavlja godinu proizvodnje
     *
     * @param godinaProizvodnje nova godina
     */
    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    /**
     * Obavlja održavanje vozila
     * Implementacija sučelja Odrzavanje
     */
    @Override
    public void obavljajOdrzavanje() {
        this.daniOdZadnjegOdrzavanja = 0;
        System.out.println("Odrzavanje obavljeno za vozilo: " + registracija);
    }

    /**
     * Dohvaća broj dana do sljedećeg održavanja
     * Implementacija sučelja Odrzavanje
     *
     * @return broj dana
     */
    @Override
    public int daniDoSljedecegOdrzavanja() {
        return 90 - daniOdZadnjegOdrzavanja;
    }

    /**
     * Dohvaća trenutnu lokaciju vozila
     * Implementacija sučelja Pratljivo
     *
     * @return lokacija vozila
     */
    @Override
    public String dohvatiLokaciju() {
        return trenutnaLokacija;
    }

    /**
     * Postavlja novu lokaciju vozila
     * Implementacija sučelja Pratljivo
     *
     * @param lokacija nova lokacija
     */
    @Override
    public void postaviLokaciju(String lokacija) {
        this.trenutnaLokacija = lokacija;
    }

    /**
     * Builder klasa za jednostavnije kreiranje Vozilo objekata
     * Omogućuje postavljanje samo onih parametara koji su potrebni
     *
     * @author Student
     * @version 1.0
     */
    public static class VoziloBuilder {
        private String registracija;
        private String model;
        private int brojSjedala = 40;
        private int godinaProizvodnje = 2020;
        private int daniOdZadnjegOdrzavanja = 0;
        private String trenutnaLokacija = "Garaza";

        /**
         * Konstruktor Buildera s obaveznim parametrima
         *
         * @param registracija registracija vozila
         * @param model model vozila
         */
        public VoziloBuilder(String registracija, String model) {
            this.registracija = registracija;
            this.model = model;
        }

        /**
         * Postavlja broj sjedala
         *
         * @param brojSjedala broj sjedala
         * @return Builder objekt za ulančavanje
         */
        public VoziloBuilder brojSjedala(int brojSjedala) {
            this.brojSjedala = brojSjedala;
            return this;
        }

        /**
         * Postavlja godinu proizvodnje
         *
         * @param godinaProizvodnje godina
         * @return Builder objekt za ulančavanje
         */
        public VoziloBuilder godinaProizvodnje(int godinaProizvodnje) {
            this.godinaProizvodnje = godinaProizvodnje;
            return this;
        }

        /**
         * Postavlja dane od zadnjeg održavanja
         *
         * @param dani broj dana
         * @return Builder objekt za ulančavanje
         */
        public VoziloBuilder daniOdZadnjegOdrzavanja(int dani) {
            this.daniOdZadnjegOdrzavanja = dani;
            return this;
        }

        /**
         * Postavlja trenutnu lokaciju
         *
         * @param lokacija lokacija vozila
         * @return Builder objekt za ulančavanje
         */
        public VoziloBuilder trenutnaLokacija(String lokacija) {
            this.trenutnaLokacija = lokacija;
            return this;
        }

        /**
         * Kreira i vraća Vozilo objekt
         *
         * @return novi Vozilo objekt
         */
        public Vozilo build() {
            return new Vozilo(this);
        }
    }
}