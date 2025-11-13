package hr.java.vjezbe.app;
import hr.java.vjezbe.entiteti.*;
import hr.java.vjezbe.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Glavna klasa aplikacije za upravljanje autobusnim prijevozom
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);

    // Kolekcije umjesto polja
    private static List<Vozilo> vozila = new ArrayList<>();
    private static List<Vozac> vozaci = new ArrayList<>();
    private static List<Putnik> putnici = new ArrayList<>();
    private static List<Ruta> rute = new ArrayList<>();

    public static void main(String[] args) {
        logger.info("Pokretanje aplikacije...");

        // Unos testnih podataka
        ucitajTestnePodatke();

        // Glavni izbornik
        int izbor;
        do {
            prikaziIzbornik();
            izbor = scanner.nextInt();
            scanner.nextLine(); // Čisti buffer

            switch (izbor) {
                case 1:
                    prikaziSvaVozila();
                    break;
                case 2:
                    prikaziSveVozace();
                    break;
                case 3:
                    prikaziSvePutnike();
                    break;
                case 4:
                    prikaziSveRute();
                    break;
                case 5:
                    sortirajVozila();
                    break;
                case 6:
                    sortirajVozace();
                    break;
                case 7:
                    filtrirajPutnike();
                    break;
                case 8:
                    grupirajRutePoPolazistima();
                    break;
                case 9:
                    particionirajVozilaPoGodini();
                    break;
                case 0:
                    logger.info("Izlaz iz aplikacije.");
                    System.out.println("Doviđenja!");
                    break;
                default:
                    System.out.println("Nepoznata opcija!");
            }
        } while (izbor != 0);

        scanner.close();
    }

    /**
     * Prikazuje glavni izbornik
     */
    private static void prikaziIzbornik() {
        System.out.println("\n=== AUTOBUSNI PRIJEVOZ - GLAVNI IZBORNIK ===");
        System.out.println("1. Prikaži sva vozila");
        System.out.println("2. Prikaži sve vozače");
        System.out.println("3. Prikaži sve putnike");
        System.out.println("4. Prikaži sve rute");
        System.out.println("5. Sortiraj vozila");
        System.out.println("6. Sortiraj vozače");
        System.out.println("7. Filtriraj putnike po godinama");
        System.out.println("8. Grupiraj rute po polazištima");
        System.out.println("9. Particioniraj vozila po godini proizvodnje");
        System.out.println("0. Izlaz");
        System.out.print("Odabir: ");
    }

    /**
     * Učitava testne podatke u kolekcije
     */
    private static void ucitajTestnePodatke() {
        // Kreiranje vozila
        Vozilo v1 = new Vozilo.VoziloBuilder("ZG-1234-AB", "Mercedes Tourismo")
                .brojSjedala(50)
                .godinaProizvodnje(2020)
                .trenutnaLokacija("Zagreb")
                .build();

        Vozilo v2 = new Vozilo.VoziloBuilder("ZG-5678-CD", "Setra S 515 HD")
                .brojSjedala(45)
                .godinaProizvodnje(2019)
                .trenutnaLokacija("Split")
                .build();

        Vozilo v3 = new Vozilo.VoziloBuilder("ZG-9012-EF", "MAN Lion's Coach")
                .brojSjedala(55)
                .godinaProizvodnje(2021)
                .trenutnaLokacija("Rijeka")
                .build();

        vozila.add(v1);
        vozila.add(v2);
        vozila.add(v3);

        // Kreiranje vozača
        vozaci.add(new Vozac("Ivan", "Horvat", 45, "VD-123456"));
        vozaci.add(new Vozac("Marko", "Kovačević", 38, "VD-234567"));
        vozaci.add(new Vozac("Ana", "Perić", 42, "VD-345678"));

        // Kreiranje putnika
        putnici.add(new Putnik("Petra", "Jurić", 25, "K-00123"));
        putnici.add(new Putnik("Luka", "Babić", 30, "K-00456"));
        putnici.add(new Putnik("Marta", "Novak", 22, "K-00789"));
        putnici.add(new Putnik("Ivana", "Marić", 28, "K-00321"));

        // Kreiranje ruta
        Ruta r1 = new Ruta("Zagreb", "Split", 380.0);
        r1.dodajVozaca(vozaci.get(0));
        r1.dodajPutnika(putnici.get(0));
        r1.dodajPutnika(putnici.get(1));

        Ruta r2 = new Ruta("Zagreb", "Rijeka", 165.0);
        r2.dodajVozaca(vozaci.get(1));
        r2.dodajPutnika(putnici.get(2));

        Ruta r3 = new Ruta("Split", "Dubrovnik", 230.0);
        r3.dodajVozaca(vozaci.get(2));
        r3.dodajPutnika(putnici.get(3));

        rute.add(r1);
        rute.add(r2);
        rute.add(r3);

        logger.info("Učitano {} vozila, {} vozača, {} putnika, {} rute",
                vozila.size(), vozaci.size(), putnici.size(), rute.size());
    }

    /**
     * Prikazuje sva vozila
     */
    private static void prikaziSvaVozila() {
        System.out.println("\n--- SVA VOZILA ---");
        for (Vozilo v : vozila) {
            System.out.println(v.getModel() + " (" + v.getRegistracija() + ") - "
                    + v.getBrojSjedala() + " sjedala, godina: " + v.getGodinaProizvodnje());
        }
    }

    /**
     * Prikazuje sve vozače
     */
    private static void prikaziSveVozace() {
        System.out.println("\n--- SVI VOZAČI ---");
        for (Vozac v : vozaci) {
            v.predstaviSe();
        }
    }

    /**
     * Prikazuje sve putnike
     */
    private static void prikaziSvePutnike() {
        System.out.println("\n--- SVI PUTNICI ---");
        for (Putnik p : putnici) {
            p.predstaviSe();
        }
    }

    /**
     * Prikazuje sve rute
     */
    private static void prikaziSveRute() {
        System.out.println("\n--- SVE RUTE ---");
        for (Ruta r : rute) {
            System.out.println(r.getPolaziste() + " -> " + r.getOdrediste()
                    + " (" + r.getUdaljenost() + " km) - Putnika: " + r.getBrojPutnika());
        }
    }

    /**
     * Sortira vozila - primjer Comparatora
     */
    private static void sortirajVozila() {
        System.out.println("\n1. Po broju sjedala (uzlazno)");
        System.out.println("2. Po godini proizvodnje (silazno)");
        System.out.print("Odabir: ");
        int opcija = scanner.nextInt();

        if (opcija == 1) {
            // Sortiranje po broju sjedala - lambda izraz
            vozila.sort((v1, v2) -> Integer.compare(v1.getBrojSjedala(), v2.getBrojSjedala()));
            // Ili s Comparator.comparingInt
            // vozila.sort(Comparator.comparingInt(Vozilo::getBrojSjedala));
        } else if (opcija == 2) {
            // Sortiranje po godini - silazno
            vozila.sort(Comparator.comparingInt(Vozilo::getGodinaProizvodnje).reversed());
        }

        System.out.println("\n--- SORTIRANA VOZILA ---");
        for (Vozilo v : vozila) {
            System.out.println(v.getModel() + " - " + v.getBrojSjedala() + " sjedala, "
                    + v.getGodinaProizvodnje() + " god.");
        }
    }

    /**
     * Sortira vozače - primjer višekriterijskog sortiranja
     */
    private static void sortirajVozace() {
        // Sortiranje prvo po godinama, pa po prezimenu
        vozaci.sort(Comparator.comparingInt(Vozac::getGodine)
                .thenComparing(Vozac::getPrezime));

        System.out.println("\n--- SORTIRANI VOZAČI (godine pa prezime) ---");
        for (Vozac v : vozaci) {
            v.predstaviSe();
        }
    }

    /**
     * Filtrira putnike po godinama - Stream API
     */
    private static void filtrirajPutnike() {
        System.out.print("Unesite minimalnu dob: ");
        int minDob = scanner.nextInt();

        // Stream API - filter
        List<Putnik> filtrirani = putnici.stream()
                .filter(p -> p.getGodine() >= minDob)
                .collect(Collectors.toList());

        System.out.println("\n--- PUTNICI STARIJI OD " + minDob + " GODINA ---");
        for (Putnik p : filtrirani) {
            p.predstaviSe();
        }
    }

    /**
     * Grupira rute po polazištima - Collectors.groupingBy
     */
    private static void grupirajRutePoPolazistima() {
        // Collectors.groupingBy
        Map<String, List<Ruta>> grupirano = rute.stream()
                .collect(Collectors.groupingBy(Ruta::getPolaziste));

        System.out.println("\n--- RUTE GRUPIRANE PO POLAZIŠTIMA ---");
        for (Map.Entry<String, List<Ruta>> entry : grupirano.entrySet()) {
            System.out.println("\nPolazište: " + entry.getKey());
            for (Ruta r : entry.getValue()) {
                System.out.println("  -> " + r.getOdrediste() + " (" + r.getUdaljenost() + " km)");
            }
        }
    }

    /**
     * Particionira vozila po godini - Collectors.partitioningBy
     */
    private static void particionirajVozilaPoGodini() {
        // Particioniranje - nova (>=2020) vs stara vozila
        Map<Boolean, List<Vozilo>> particionirano = vozila.stream()
                .collect(Collectors.partitioningBy(v -> v.getGodinaProizvodnje() >= 2020));

        System.out.println("\n--- NOVA VOZILA (2020+) ---");
        for (Vozilo v : particionirano.get(true)) {
            System.out.println(v.getModel() + " - " + v.getGodinaProizvodnje());
        }

        System.out.println("\n--- STARIJA VOZILA ---");
        for (Vozilo v : particionirano.get(false)) {
            System.out.println(v.getModel() + " - " + v.getGodinaProizvodnje());
        }
    }
}
