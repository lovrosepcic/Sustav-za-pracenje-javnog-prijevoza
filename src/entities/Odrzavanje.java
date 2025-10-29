package entities;

// SEALED SUCELJE - samo Vozilo ga moze implementirati
public sealed interface Odrzavanje permits Vozilo {
    void obavljajOdrzavanje();
    int daniDoSljedecegOdrzavanja();
}