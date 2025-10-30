package entities;


public class Vozac extends Osoba {
    private String brojDozvole;

    public Vozac(String ime, String prezime, int godine, String brojDozvole) {
        super(ime, prezime, godine);
        this.brojDozvole = brojDozvole;
    }

    public String getBrojDozvole() {
        return brojDozvole;
    }

    public void setBrojDozvole(String brojDozvole) {
        this.brojDozvole = brojDozvole;
    }

    @Override
    public void predstaviSe() {
        System.out.println("Vozac: " + getIme() + " " + getPrezime() +
                ", godine: " + getGodine() + ", dozvola: " + brojDozvole);
    }
}