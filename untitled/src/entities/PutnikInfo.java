package entities;

// RECORD - nepromjenjivi zapis
public record PutnikInfo(String ime, String prezime, String brojKarte, int starost) {

    // Kompaktni konstruktor
    public PutnikInfo {
        if (starost < 0) {
            starost = 0;
        }
    }

    public boolean jeDijete() {
        return starost < 18;
    }
}