package hr.java.vjezbe.app;

import hr.java.vjezbe.entiteti.*;
import hr.java.vjezbe.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Glavna klasa aplikacije za upravljanje autobusnim prijevozom
 * Peta laboratorijska vježba - Lambda izrazi, Optional, Generici
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static List<Vozilo> vozila = new ArrayList<>();
    private static List<Vozac> vozaci = new ArrayList<>();
    private static List<Putnik> putnici = new ArrayList<>();
    private static List<Ruta> rute = new ArrayList<>();

    public static void main(String[] args) {
        logger.info("Pokretanje aplikacije...");

        ucitajTestnePodatke();

        int izbor;
        do {
            prikaziIzbornik();
            izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1 -> prikaziSvaVozila();
                case 2 -> prikaziSveVozace();
                case 3 -> prikaziSvePutnike();
                case 4 -> prikaziSveRute();
                case 5 -> sortirajVozila();
                case 6 -> sortirajVozace();
                case 7 -> filtrirajPutnike();
                case 8 -> grupirajRutePoPolazistima();
                case 9 -> particionirajVozilaPoGodini();
                case 10 -> pronađiNajstarijevoziluOptional();
                case 11 -> mapiranjePutnikaNaInfo();
                case 12 -> reducirajVozilaPoGodini();
                case 0 -> logger.info("Izlaz iz aplikacije.");
                default -> System.out.println("Nepoznata opcija!");
            }
        } while (izbor != 0);

        scanner.close();
    }

    private static void prikaziIzbornik() {
        System.out.println("\n=== AUTOBUSNI PRIJEVOZ - V5 (Lambda, Optional, Generici) ===");
        System.out.println("1. Prikaži sva vozila");
        System.out.println("2. Prikaži sve vozače");
        System.out.println("3. Prikaži sve putnike");
        System.out.println("4. Prikaži sve rute");
        System.out.println("5. Sortiraj vozila");
        System.out.println("6. Sortiraj vozače");
        System.out.println("7. Filtriraj putnike po godinama");
        System.out.println("8. Grupiraj rute po polazištima");
        System.out.println("9. Particioniraj vozila po godini");
        System.out.println("10. Pronađi najstarije vozilo (Optional)");
        System.out.println("11. Mapiraj putnike u PutnikInfo (Stream API)");
        System.out.println("12. Reduciraj vozila po godini");
        System.out.println("0. Izlaz");
        System.out.print("Odabir: ");
    }

    private static void ucitajTestnePodatke() {
        // Vozila
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

        // Vozači
        vozaci.add(new Vozac("Ivan", "Horvat", 45, "VD-123456"));
        vozaci.add(new Vozac("Marko", "Kovačević", 38, "VD-234567"));
        vozaci.add(new Vozac("Ana", "Perić", 42, "VD-345678"));

        // Putnici
        putnici.add(new Putnik("Petra", "Jurić", 25, "K-00123"));
        putnici.add(new Putnik("Luka", "Babić", 30, "K-00456"));
        putnici.add(new Putnik("Marta", "Novak", 22, "K-00789"));
        putnici.add(new Putnik("Ivana", "Marić", 28, "K-00321"));
        putnici.add(new Putnik("Jovan", "Jovanović", 17, "K-00654"));

        // Rute
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

        Ruta r4 = new Ruta("Zagreb", "Osijek", 340.0);
        r4.dodajVozaca(vozaci.get(0));
        r4.dodajPutnika(putnici.get(4));

        rute.add(r1);
        rute.add(r2);
        rute.add(r3);
        rute.add(r4);

        logger.info("Učitano {} vozila, {} vozača, {} putnika, {} rute",
                vozila.size(), vozaci.size(), putnici.size(), rute.size());
    }

    // ===== PRIKAZ METODE =====
    private static void prikaziSvaVozila() {
        System.out.println("\n--- SVA VOZILA ---");
        vozila.forEach(v -> System.out.println(v.getModel() + " (" + v.getRegistracija() +
                ") - " + v.getBrojSjedala() + " sjedala, godina: " + v.getGodinaProizvodnje()));
    }

    private static void prikaziSveVozace() {
        System.out.println("\n--- SVI VOZAČI ---");
        vozaci.forEach(Vozac::predstaviSe);
    }

    private static void prikaziSvePutnike() {
        System.out.println("\n--- SVI PUTNICI ---");
        putnici.forEach(Putnik::predstaviSe);
    }

    private static void prikaziSveRute() {
        System.out.println("\n--- SVE RUTE ---");
        rute.forEach(r -> System.out.println(r.getPolaziste() + " -> " + r.getOdrediste() +
                " (" + r.getUdaljenost() + " km) - Putnika: " + r.getBrojPutnika()));
    }


    private static void sortirajVozila() {
        System.out.println("\n1. Po broju sjedala (uzlazno)");
        System.out.println("2. Po godini proizvodnje (silazno)");
        System.out.print("Odabir: ");
        int opcija = scanner.nextInt();

        if (opcija == 1) {
            // Lambda izraz - sortiranje po broju sjedala
            vozila.sort((v1, v2) -> Integer.compare(v1.getBrojSjedala(), v2.getBrojSjedala()));
        } else if (opcija == 2) {
            // Comparator s method reference - silazno
            vozila.sort(Comparator.comparingInt(Vozilo::getGodinaProizvodnje).reversed());
        }

        System.out.println("\n--- SORTIRANA VOZILA ---");
        vozila.forEach(v -> System.out.println(v.getModel() + " - " + v.getBrojSjedala() +
                " sjedala, " + v.getGodinaProizvodnje() + " god."));
    }

    private static void sortirajVozace() {
        // Višekriterijsko sortiranje - prvo po godinama, pa po prezimenu
        vozaci.sort(Comparator.comparingInt(Vozac::getGodine)
                .thenComparing(Vozac::getPrezime));

        System.out.println("\n--- SORTIRANI VOZAČI (godine pa prezime) ---");
        vozaci.forEach(Vozac::predstaviSe);
    }


    private static void filtrirajPutnike() {
        System.out.print("Unesite minimalnu dob: ");
        int minDob = scanner.nextInt();

        // Stream API - filter s lambda izrazom
        List<Putnik> filtrirani = putnici.stream()
                .filter(p -> p.getGodine() >= minDob)
                .collect(Collectors.toList());

        System.out.println("\n--- PUTNICI STARIJI OD " + minDob + " GODINA ---");
        filtrirani.forEach(Putnik::predstaviSe);
    }


    private static void grupirajRutePoPolazistima() {
        // Collectors.groupingBy - immutable pristup
        Map<String, List<Ruta>> grupirano = rute.stream()
                .collect(Collectors.groupingBy(Ruta::getPolaziste));

        System.out.println("\n--- RUTE GRUPIRANE PO POLAZIŠTIMA ---");
        grupirano.forEach((polaziste, ruteListe) -> {
            System.out.println("\nPolazište: " + polaziste);
            ruteListe.forEach(r -> System.out.println(" -> " + r.getOdrediste() +
                    " (" + r.getUdaljenost() + " km)"));
        });
    }


    private static void particionirajVozilaPoGodini() {
        // Particioniranje - nova (>=2020) vs stara vozila
        Map<Boolean, List<Vozilo>> particionirano = vozila.stream()
                .collect(Collectors.partitioningBy(v -> v.getGodinaProizvodnje() >= 2020));

        System.out.println("\n--- NOVA VOZILA (2020+) ---");
        particionirano.get(true).forEach(v -> System.out.println(v.getModel() +
                " - " + v.getGodinaProizvodnje()));

        System.out.println("\n--- STARIJA VOZILA ---");
        particionirano.get(false).forEach(v -> System.out.println(v.getModel() +
                " - " + v.getGodinaProizvodnje()));
    }


    private static void pronađiNajstarijevoziluOptional() {
        // Optional - sigurna obrada null vrijednosti
        Optional<Vozilo> najstarijeVozilo = vozila.stream()
                .min(Comparator.comparingInt(Vozilo::getGodinaProizvodnje));

        System.out.println("\n--- NAJSTARIJE VOZILO (Optional) ---");

        // ifPresentOrElse - lambda izrazi za obje grane
        najstarijeVozilo.ifPresentOrElse(
                v -> System.out.println("Pronađeno: " + v.getModel() + " - " + v.getGodinaProizvodnje()),
                () -> System.out.println("Nema vozila u bazi!")
        );

        // Alternativa - ifPresent
        if (najstarijeVozilo.isPresent()) {
            Vozilo v = najstarijeVozilo.get();
            System.out.println("Alternativa - vozilo: " + v.getRegistracija());
        }
    }


    private static void mapiranjePutnikaNaInfo() {
        System.out.println("\n--- MAPIRANJE PUTNIKA U PutnikInfo (Stream API) ---");

        // Map - transformacija Putnik -> PutnikInfo
        List<PutnikInfo> putnikInfos = putnici.stream()
                .map(p -> new PutnikInfo(p.getIme(), p.getPrezime(),
                        p.getBrojKarte(), p.getGodine()))
                .collect(Collectors.toList());

        putnikInfos.forEach(info -> System.out.println(info.ime() + " " + info.prezime() +
                " (" + info.starost() + ") - Karta: " + info.brojKarte()));

        // Dodatni primjer - samo imena
        System.out.println("\n--- SAMO IMENA PUTNIKA (Map) ---");
        putnici.stream()
                .map(Putnik::getIme)
                .forEach(System.out::println);

        // Dodatni primjer - prosječna starost
        double prosjecnaStarost = putnici.stream()
                .mapToInt(Putnik::getGodine)
                .average()
                .orElse(0.0);

        System.out.println("\nProsječna starost putnika: " + prosjecnaStarost);
    }


    private static void reducirajVozilaPoGodini() {
        System.out.println("\n--- REDUCIRANJE VOZILA PO GODINI PROIZVODNJE ---");

        // Reduce - suma godina proizvodnje
        Optional<Integer> sumGodina = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .reduce(Integer::sum);

        sumGodina.ifPresent(suma -> System.out.println("Suma godina: " + suma));

        // Reduce s initialnom vrijednosti
        Integer sumGodina2 = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .reduce(0, Integer::sum);

        System.out.println("Suma godina (s 0): " + sumGodina2);

        // Prosječna godina proizvodnje
        double prosjecnaGodina = vozila.stream()
                .mapToInt(Vozilo::getGodinaProizvodnje)
                .average()
                .orElse(0.0);

        System.out.println("Prosječna godina proizvodnje: " + prosjecnaGodina);

        // Najnovija godina
        Optional<Integer> najnovijaGodina = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .max(Integer::compare);

        najnovijaGodina.ifPresent(godina -> System.out.println("Najnovija godina: " + godina));
    }
}