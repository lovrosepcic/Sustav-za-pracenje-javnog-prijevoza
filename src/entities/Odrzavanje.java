package entities;


public sealed interface Odrzavanje permits Vozilo {
    void obavljajOdrzavanje();
    int daniDoSljedecegOdrzavanja();
}