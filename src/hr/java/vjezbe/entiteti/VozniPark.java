package hr.java.vjezbe.entiteti;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; 
public class VozniPark<T extends Vozilo> {

    private List<T> vozila;

    /**
     * Konstruktor za inicijalizaciju genericnog voznog parka
     */
    public VozniPark() {
        this.vozila = new ArrayList<>();
    }

    /**
     * Dodaj vozilo u park
     */
    public void dodajVozilo(T vozilo) {
        vozila.add(vozilo);
    }

    /**
     * Dohvati vozilo po indeksu s Optional zaštitom
     *
     * @param indeks indeks vozila
     * @return Optional koji sadrži vozilo ili je prazan
     */
    public Optional<T> getVozilo(int indeks) {
        if (indeks >= 0 && indeks < vozila.size()) {
            return Optional.of(vozila.get(indeks));
        }
        return Optional.empty();
    }

    /**
     * PECS - Producer: ? extends T
     * Čita iz kolekcije (ne piše)
     *
     * @param prodajnaVozila kolekcija vozila za prodaju
     * @return lista svih vozila
     */
    public List<String> dodajIzProdajeSveVozile(List<? extends Vozilo> prodajnaVozila) {
        // Producer - čitamo iz liste
        return prodajnaVozila.stream()
                .map(v -> v.getModel() + " (" + v.getRegistracija() + ")")
                .collect(Collectors.toList());
    }

    /**
     * PECS - Consumer: ? super T
     * Piše u kolekciju (ne čita)
     *
     * @param odlagaliste kolekcija za odbačena vozila
     */
    public void prebaciBraceneVozileUOdlagaliste(List<? super Vozilo> odlagaliste) {
        // Consumer - pišemo u listu
        vozila.stream()
                .filter(v -> v.daniDoSljedecegOdrzavanja() <= 0)
                .forEach(odlagaliste::add);
    }

    /**
     * Pronađi vozilo sa minimalnim danima do održavanja
     * Demonstrira generičke komparatora
     *
     * @return Optional s vozilom koje najhitnije trebapodržavanje
     */
    public Optional<T> pronađiVoziloZaNajhitnije() {
        return vozila.stream()
                .min(Comparator.comparingInt(Vozilo::daniDoSljedecegOdrzavanja));
    }

    /**
     * Sortiraj vozila po kustom komparatoru - generički pristup
     *
     * @param komparator prilagođeni komparator
     */
    public void sortiraj(Comparator<? super T> komparator) {
        vozila.sort(komparator);
    }

    /**
     * Filtriraj vozila prema predikatu
     *
     * @param predikat lambda izraz za filtriranje
     * @return lista filtriranih vozila
     */
    public List<T> filtriraj(java.util.function.Predicate<? super T> predikat) {
        return vozila.stream()
                .filter(predikat)
                .collect(Collectors.toList());
    }

    /**
     * Mapiraj vozila u drugačiji tip
     *
     * @param mapper funkcija za mapiranje
     * @return lista mapiranih elemenata
     */
    public <R> List<R> mapiraj(java.util.function.Function<? super T, ? extends R> mapper) {
        return vozila.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * Dohvati broj vozila
     */
    public int getVelicina() {
        return vozila.size();
    }

    /**
     * Provjeri da li je park prazan
     */
    public boolean jePrazan() {
        return vozila.isEmpty();
    }

    /**
     * Prikaži sve vozile s detaljima
     */
    public void prikaziSvaVozila() {
        vozila.forEach(v -> System.out.println(
                v.getModel() + " - " + v.getBrojSjedala() + " sjedala, " +
                        "Registracija: " + v.getRegistracija() + ", " +
                        "Dana do održavanja: " + v.daniDoSljedecegOdrzavanja()
        ));
    }

    // ===== STATIC GENERIČKA METODA - sa bounded typovima =====

    /**
     * Pronađi elementi s maksimalnom vrijednosti
     * T mora biti Comparable
     *
     * @param lista lista elemenata za pretraživanje
     * @return Optional s elementom koji ima maksimalnu vrijednost
     */
    public static <T extends Comparable<? super T>> Object maksimum(List<? extends T> lista) {
        if (lista.isEmpty()) {
            return Optional.empty();
        }
        return lista.stream().max(Comparator.naturalOrder());
    }

    /**
     * Pronađi element s minimalnom vrijednosti
     * T mora biti Comparable
     */
    public static <T extends Comparable<? super T>> Object minimum(List<? extends T> lista) {
        if (lista.isEmpty()) {
            return Optional.empty();
        }
        return lista.stream().min(Comparator.naturalOrder());
    }

    /**
     * Kopiraj sve elemente iz source u destination
     * Demonstrira PECS - Producer (source) i Consumer (destination)
     *
     * @param source izvor elemenata (Producer - extends)
     * @param destination odrediste (Consumer - super)
     */
    public static <T> void kopiraj(List<? extends T> source, List<? super T> destination) {
        source.stream().forEach(destination::add);
    }

    /**
     * Primjer s multiple bounds - T mora biti i Number i Comparable
     *
     * @param lista lista brojeva
     * @return suma svih elemenata
     */
    public static <T extends Number & Comparable<? super T>> double suma(List<T> lista) {
        return lista.stream()
                .mapToDouble(Number::doubleValue)
                .sum();
    }
}
