package entities;


public final class Vozilo implements Odrzavanje, Pratljivo {
    private String registracija;
    private String model;
    private int brojSjedala;
    private int godinaProizvodnje;
    private int daniOdZadnjegOdrzavanja;
    private String trenutnaLokacija;


    private Vozilo(VoziloBuilder builder) {
        this.registracija = builder.registracija;
        this.model = builder.model;
        this.brojSjedala = builder.brojSjedala;
        this.godinaProizvodnje = builder.godinaProizvodnje;
        this.daniOdZadnjegOdrzavanja = builder.daniOdZadnjegOdrzavanja;
        this.trenutnaLokacija = builder.trenutnaLokacija;
    }

    public String getRegistracija() {
        return registracija;
    }

    public String getModel() {
        return model;
    }

    public int getBrojSjedala() {
        return brojSjedala;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrojSjedala(int brojSjedala) {
        this.brojSjedala = brojSjedala;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }


    @Override
    public void obavljajOdrzavanje() {
        this.daniOdZadnjegOdrzavanja = 0;
        System.out.println("Odrzavanje obavljeno za vozilo: " + registracija);
    }

    @Override
    public int daniDoSljedecegOdrzavanja() {
        return 90 - daniOdZadnjegOdrzavanja;
    }


    @Override
    public String dohvatiLokaciju() {
        return trenutnaLokacija;
    }

    @Override
    public void postaviLokaciju(String lokacija) {
        this.trenutnaLokacija = lokacija;
    }


    public static class VoziloBuilder {
        private String registracija;
        private String model;
        private int brojSjedala = 40;
        private int godinaProizvodnje = 2020;
        private int daniOdZadnjegOdrzavanja = 0;
        private String trenutnaLokacija = "Garaza";

        public VoziloBuilder(String registracija, String model) {
            this.registracija = registracija;
            this.model = model;
        }

        public VoziloBuilder brojSjedala(int brojSjedala) {
            this.brojSjedala = brojSjedala;
            return this;
        }

        public VoziloBuilder godinaProizvodnje(int godinaProizvodnje) {
            this.godinaProizvodnje = godinaProizvodnje;
            return this;
        }

        public VoziloBuilder daniOdZadnjegOdrzavanja(int dani) {
            this.daniOdZadnjegOdrzavanja = dani;
            return this;
        }

        public VoziloBuilder trenutnaLokacija(String lokacija) {
            this.trenutnaLokacija = lokacija;
            return this;
        }

        public Vozilo build() {
            return new Vozilo(this);
        }
    }
}