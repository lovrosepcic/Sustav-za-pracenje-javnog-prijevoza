package entities;


public record PutnikInfo(String ime, String prezime, String brojKarte, int starost) {


    public PutnikInfo {
        if (starost < 0) {
            starost = 0;
        }
    }

    public boolean jeDijete() {
        return starost < 18;
    }
}