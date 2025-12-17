package hr.java.vjezbe.app;

import hr.java.vjezbe.entiteti.*;
import hr.java.vjezbe.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Glavna klasa aplikacije za upravljanje autobusnim prijevozom
 */
public class Main {
    private static final String VOZILA_JSON = "vozila.json";
    private static final String VOZACI_JSON = "vozaci.json";
    private static final String PUTNICI_JSON = "putnici.json";
    private static final String RUTE_JSON = "rute.json";

    private static final String LOG_XML = "log.xml";
    private static PrintWriter logWriter;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static List<Vozilo> vozila = new ArrayList<>();
    private static List<Vozac> vozaci = new ArrayList<>();
    private static List<Putnik> putnici = new ArrayList<>();
    private static List<Ruta> rute = new ArrayList<>();

    private static void spremiVozilaUJson() {
        try {
            jakarta.json.bind.Jsonb jsonb = jakarta.json.bind.JsonbBuilder.create();
            String json = jsonb.toJson(vozila);
            java.nio.file.Files.writeString(java.nio.file.Paths.get(VOZILA_JSON), json);
            System.out.println("Vozila spremljena u " + VOZILA_JSON);
        } catch (Exception e) {
            System.err.println("Greska pri spremanju JSON datoteke: " + e.getMessage());
        }
    }

    private static void ucitajPodatkeIzJson() {
        jakarta.json.bind.Jsonb jsonb = jakarta.json.bind.JsonbBuilder.create();

        try {
            java.nio.file.Path p = java.nio.file.Paths.get(VOZILA_JSON);
            if (java.nio.file.Files.exists(p)) {
                String json = java.nio.file.Files.readString(p);
                Vozilo[] arr = jsonb.fromJson(json, Vozilo[].class);
                vozila = new java.util.ArrayList<>(java.util.Arrays.asList(arr));
            }
        } catch (Exception e) {
            System.err.println("Greska pri citanju " + VOZILA_JSON);
        }

        try {
            java.nio.file.Path p = java.nio.file.Paths.get(VOZACI_JSON);
            if (java.nio.file.Files.exists(p)) {
                String json = java.nio.file.Files.readString(p);
                Vozac[] arr = jsonb.fromJson(json, Vozac[].class);
                vozaci = new java.util.ArrayList<>(java.util.Arrays.asList(arr));
            }
        } catch (Exception e) {
            System.err.println("Greska pri citanju " + VOZACI_JSON);
        }

        try {
            java.nio.file.Path p = java.nio.file.Paths.get(PUTNICI_JSON);
            if (java.nio.file.Files.exists(p)) {
                String json = java.nio.file.Files.readString(p);
                Putnik[] arr = jsonb.fromJson(json, Putnik[].class);
                putnici = new java.util.ArrayList<>(java.util.Arrays.asList(arr));
            }
        } catch (Exception e) {
            System.err.println("Greska pri citanju " + PUTNICI_JSON);
        }

        try {
            java.nio.file.Path p = java.nio.file.Paths.get(RUTE_JSON);
            if (java.nio.file.Files.exists(p)) {
                String json = java.nio.file.Files.readString(p);
                Ruta[] arr = jsonb.fromJson(json, Ruta[].class);
                rute = new java.util.ArrayList<>(java.util.Arrays.asList(arr));
            }
        } catch (Exception e) {
            System.err.println("Greska pri citanju " + RUTE_JSON);
        }

        logger.info("Ucitano {} vozila, {} vozaca, {} putnika, {} ruta iz JSON-a",
                vozila.size(), vozaci.size(), putnici.size(), rute.size());
    }
    private static void spremiPodatkeUJson() {
        jakarta.json.bind.Jsonb jsonb = jakarta.json.bind.JsonbBuilder.create();

        try {
            String json = jsonb.toJson(vozila);
            java.nio.file.Files.writeString(java.nio.file.Paths.get(VOZILA_JSON), json);
        } catch (Exception e) {
            System.err.println("Greska pri pisanju " + VOZILA_JSON);
        }

        try {
            String json = jsonb.toJson(vozaci);
            java.nio.file.Files.writeString(java.nio.file.Paths.get(VOZACI_JSON), json);
        } catch (Exception e) {
            System.err.println("Greska pri pisanju " + VOZACI_JSON);
        }

        try {
            String json = jsonb.toJson(putnici);
            java.nio.file.Files.writeString(java.nio.file.Paths.get(PUTNICI_JSON), json);
        } catch (Exception e) {
            System.err.println("Greska pri pisanju " + PUTNICI_JSON);
        }

        try {
            String json = jsonb.toJson(rute);
            java.nio.file.Files.writeString(java.nio.file.Paths.get(RUTE_JSON), json);
        } catch (Exception e) {
            System.err.println("Greska pri pisanju " + RUTE_JSON);
        }
    }

    private static void initLog() {
        try {

            logWriter = new PrintWriter(new java.io.FileWriter(LOG_XML, true));
            java.io.File f = new java.io.File(LOG_XML);
            if (f.length() == 0) {
                logWriter.println("<log>");
            }
            logWriter.flush();
        } catch (java.io.IOException e) {
            System.err.println("Greska pri otvaranju log.xml");
        }
    }

    private static void closeLog() {
        if (logWriter != null) {
            logWriter.println("</log>");
            logWriter.flush();
            logWriter.close();
        }
    }

    private static void logAkcija(String tekst) {
        if (logWriter != null) {
            logWriter.println("  <akcija>" + tekst + "</akcija>");
            logWriter.flush();
        }
    }
    private static final String BACKUP_BIN = "backup.bin";

    private static void kreirajBackup() {
        try (java.io.ObjectOutputStream out =
                     new java.io.ObjectOutputStream(new java.io.FileOutputStream(BACKUP_BIN))) {
            out.writeObject(new java.util.ArrayList<>(vozila));
            out.writeObject(new java.util.ArrayList<>(vozaci));
            out.writeObject(new java.util.ArrayList<>(putnici));
            out.writeObject(new java.util.ArrayList<>(rute));
            System.out.println("Backup uspjesno kreiran u " + BACKUP_BIN);
        } catch (java.io.IOException e) {
            System.err.println("Greska pri kreiranju backup.bin");
        }
    }
    @SuppressWarnings("unchecked")
    private static void ucitajIzBackup() {
        try (java.io.ObjectInputStream in =
                     new java.io.ObjectInputStream(new java.io.FileInputStream(BACKUP_BIN))) {
            vozila = (java.util.List<Vozilo>) in.readObject();
            vozaci = (java.util.List<Vozac>) in.readObject();
            putnici = (java.util.List<Putnik>) in.readObject();
            rute = (java.util.List<Ruta>) in.readObject();
            System.out.println("Podaci uspjesno ucitani iz " + BACKUP_BIN);
            System.out.println("Vozila: " + vozila.size() + ", Vozaca: " + vozaci.size()
                    + ", Putnika: " + putnici.size() + ", Ruta: " + rute.size());
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.err.println("Greska pri citanju backup.bin: "
                    + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    private static void ucitajPodatkeIzJsonIliTestne() {
        ucitajPodatkeIzJson();
        if (vozila.isEmpty() && vozaci.isEmpty() && putnici.isEmpty() && rute.isEmpty()) {
            ucitajTestnePodatke();
            spremiPodatkeUJson();
        }
    }

    public static void main(String[] args) {
        logger.info("Pokretanje aplikacije...");
        initLog();
        logAkcija("Pokretanje aplikacije");

        // umjesto ucitajTestnePodatke(); koristiti JSON (dolje)
        ucitajPodatkeIzJsonIliTestne();


        int izbor;
        do {
            prikaziIzbornik();
            izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1 -> { prikaziSvaVozila(); logAkcija("Prikaz svih vozila"); }
                case 2 -> { prikaziSveVozace(); logAkcija("Prikaz svih vozaca"); }
                case 3 -> { prikaziSvePutnike(); logAkcija("Prikaz svih putnika"); }
                case 4 -> { prikaziSveRute(); logAkcija("Prikaz svih ruta"); }
                case 5 -> { sortirajVozila(); logAkcija("Sortiranje vozila"); }
                case 6 -> { sortirajVozace(); logAkcija("Sortiranje vozaca"); }
                case 7 -> { filtrirajPutnike(); logAkcija("Filtriranje putnika"); }
                case 8 -> { grupirajRutePoPolazistima(); logAkcija("Grupiranje ruta"); }
                case 9 -> { particionirajVozilaPoGodini(); logAkcija("Particioniranje vozila"); }
                case 10 -> { pronađiNajstarijevoziluOptional(); logAkcija("Najstarije vozilo"); }
                case 11 -> { mapiranjePutnikaNaInfo(); logAkcija("Mapiranje putnika"); }
                case 12 -> { reducirajVozilaPoGodini(); logAkcija("Reduciranje vozila"); }
                case 13 -> { kreirajBackup(); logAkcija("Kreiranje backup.bin"); }
                case 14 -> { ucitajIzBackup(); logAkcija("Ucitavanje iz backup.bin"); }
                case 15 -> { ispisiLogBezTagova(); logAkcija("Ispis loga bez XML tagova"); }
                case 16 -> spremiVozilaUJson();

                case 0 -> logger.info("Izlaz iz aplikacije.");
                default -> System.out.println("Nepoznata opcija!");
            }
        } while (izbor != 0);

        closeLog();
        scanner.close();
    }
    private static void ispisiLogBezTagova() {
        System.out.println("\n--- LOG BEZ XML TAGOVA ---");
        try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(LOG_XML))) {
            String line;
            while ((line = in.readLine()) != null) {
                // makni <tag> i </tag>
                String bezTagova = line.replaceAll("<[^>]+>", "").trim();
                if (!bezTagova.isEmpty()) {
                    System.out.println(bezTagova);
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Greska pri citanju log.xml");
        }
    }



    private static void prikaziIzbornik() {
        System.out.println("\n AUTOBUSNI PRIJEVOZ  ");
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
        System.out.println("13. Kreiraj backup (backup.bin)");
        System.out.println("14. Učitaj podatke iz backup.bin");
        System.out.println("15. Ispiši log bez XML tagova");
        System.out.println("16. Spremi vozila u JSON");

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
            vozila.sort((v1, v2) -> Integer.compare(v1.getBrojSjedala(), v2.getBrojSjedala()));
        } else if (opcija == 2) {
            vozila.sort(Comparator.comparingInt(Vozilo::getGodinaProizvodnje).reversed());
        }

        System.out.println("\n--- SORTIRANA VOZILA ---");
        vozila.forEach(v -> System.out.println(v.getModel() + " - " + v.getBrojSjedala() +
                " sjedala, " + v.getGodinaProizvodnje() + " god."));
    }

    private static void sortirajVozace() {
        vozaci.sort(Comparator.comparingInt(Vozac::getGodine)
                .thenComparing(Vozac::getPrezime));

        System.out.println("\n--- SORTIRANI VOZAČI (godine pa prezime) ---");
        vozaci.forEach(Vozac::predstaviSe);
    }


    private static void filtrirajPutnike() {
        System.out.print("Unesite minimalnu dob: ");
        int minDob = scanner.nextInt();

        List<Putnik> filtrirani = putnici.stream()
                .filter(p -> p.getGodine() >= minDob)
                .collect(Collectors.toList());

        System.out.println("\n--- PUTNICI STARIJI OD " + minDob + " GODINA ---");
        filtrirani.forEach(Putnik::predstaviSe);
    }


    private static void grupirajRutePoPolazistima() {

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

        Optional<Vozilo> najstarijeVozilo = vozila.stream()
                .min(Comparator.comparingInt(Vozilo::getGodinaProizvodnje));

        System.out.println("\n--- NAJSTARIJE VOZILO (Optional) ---");


        najstarijeVozilo.ifPresentOrElse(
                v -> System.out.println("Pronađeno: " + v.getModel() + " - " + v.getGodinaProizvodnje()),
                () -> System.out.println("Nema vozila u bazi!")
        );


        if (najstarijeVozilo.isPresent()) {
            Vozilo v = najstarijeVozilo.get();
            System.out.println("Alternativa - vozilo: " + v.getRegistracija());
        }
    }


    private static void mapiranjePutnikaNaInfo() {
        System.out.println("\n--- MAPIRANJE PUTNIKA U PutnikInfo (Stream API) ---");

        List<PutnikInfo> putnikInfos = putnici.stream()
                .map(p -> new PutnikInfo(p.getIme(), p.getPrezime(),
                        p.getBrojKarte(), p.getGodine()))
                .collect(Collectors.toList());

        putnikInfos.forEach(info -> System.out.println(info.ime() + " " + info.prezime() +
                " (" + info.starost() + ") - Karta: " + info.brojKarte()));

        System.out.println("\n--- SAMO IMENA PUTNIKA (Map) ---");
        putnici.stream()
                .map(Putnik::getIme)
                .forEach(System.out::println);

        double prosjecnaStarost = putnici.stream()
                .mapToInt(Putnik::getGodine)
                .average()
                .orElse(0.0);

        System.out.println("\nProsječna starost putnika: " + prosjecnaStarost);
    }


    private static void reducirajVozilaPoGodini() {
        System.out.println("\n--- REDUCIRANJE VOZILA PO GODINI PROIZVODNJE ---");

        Optional<Integer> sumGodina = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .reduce(Integer::sum);

        sumGodina.ifPresent(suma -> System.out.println("Suma godina: " + suma));

        Integer sumGodina2 = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .reduce(0, Integer::sum);

        System.out.println("Suma godina (s 0): " + sumGodina2);

        double prosjecnaGodina = vozila.stream()
                .mapToInt(Vozilo::getGodinaProizvodnje)
                .average()
                .orElse(0.0);

        System.out.println("Prosječna godina proizvodnje: " + prosjecnaGodina);

        Optional<Integer> najnovijaGodina = vozila.stream()
                .map(Vozilo::getGodinaProizvodnje)
                .max(Integer::compare);

        najnovijaGodina.ifPresent(godina -> System.out.println("Najnovija godina: " + godina));
    }
}