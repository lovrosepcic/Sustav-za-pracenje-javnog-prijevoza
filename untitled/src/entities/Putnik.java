package entities;

// Putnik - jos jedna podklasa Osoba za polimorfizam
public class Putnik extends Osoba {
    private String brojKarte;

    public Putnik(String ime, String prezime, int godine, String brojKarte) {
        super(ime, prezime, godine);
        this.brojKarte = brojKarte;
    }

    public String getBrojKarte() {
        return brojKarte;
    }

    public void setBrojKarte(String brojKarte) {
        this.brojKarte = brojKarte;
    }

    @Override
    public void predstaviSe() {
        System.out.println("Putnik: " + getIme() + " " + getPrezime() +
                ", godine: " + getGodine() + ", karta: " + brojKarte);
    }
}