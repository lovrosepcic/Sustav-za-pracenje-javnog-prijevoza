package entities;

public class Ruta {
    private int brojLinije;
    private String pocetnaStanica;
    private String zavrsnaStanica;
    private Vozilo vozilo;
    private Vozac vozac;

    public Ruta(int brojLinije, String pocetnaStanica, String zavrsnaStanica, Vozilo vozilo, Vozac vozac) {
        this.brojLinije = brojLinije;
        this.pocetnaStanica = pocetnaStanica;
        this.zavrsnaStanica = zavrsnaStanica;
        this.vozilo = vozilo;
        this.vozac = vozac;
    }

    public int getBrojLinije() {
        return brojLinije;
    }

    public String getPocetnaStanica() {
        return pocetnaStanica;
    }

    public String getZavrsnaStanica() {
        return zavrsnaStanica;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setBrojLinije(int brojLinije) {
        this.brojLinije = brojLinije;
    }

    public void setPocetnaStanica(String pocetnaStanica) {
        this.pocetnaStanica = pocetnaStanica;
    }

    public void setZavrsnaStanica(String zavrsnaStanica) {
        this.zavrsnaStanica = zavrsnaStanica;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }
}