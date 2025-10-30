package entities;


public abstract class Osoba {
    private String ime;
    private String prezime;
    private int godine;

    public Osoba(String ime, String prezime, int godine) {
        this.ime = ime;
        this.prezime = prezime;
        this.godine = godine;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getGodine() {
        return godine;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }


    public abstract void predstaviSe();
}