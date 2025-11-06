package app;

import entities.*;
import exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

/**
 * Glavna aplikacija za sustav javnog prijevoza
 * Sveobuhvaćava unos podataka, pretraživanje i upravljanje vozačima, vozilima, putnicima i rutama
 * Primjenjuje iznimke, logiranje i Javadoc dokumentaciju
 *
 * @author Student
 * @version 3.0
 */
public class Main {

    // Logger za logiranje informacija i grešaka
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Glavna metoda programa
     *
     * @param args argumenti naredbenog redka (ne koriste se)
     */
    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);
        logger.info("=== Početak programa - Sustav javnog prijevoza ===");

        // 1. UNOS PODATAKA ZA VOZACE
        System.out.println("=== UNOS PODATAKA ZA VOZACE ===");
        Vozac[] vozaci = new Vozac[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za vozaca " + (i + 1) + ":");

            try {
                System.out.print("Unesite ime: ");
                String ime = unos.nextLine();

                System.out.print("Unesite prezime: ");
                String prezime = unos.nextLine();

                System.out.print("Unesite godine: ");
                String godineString = unos.nextLine();
                int godine;

                // Hvatanje NumberFormatException i bacanje checked iznimke
                try {
                    godine = Integer.parseInt(godineString);
                } catch (NumberFormatException e) {
                    logger.error("Greška pri konverziji godina za vozača " + (i + 1), e);
                    throw new IspravnaVozacaException("Godine moraju biti broj!");
                }

                // Validacija godina
                if (godine < 18 || godine > 100) {
                    logger.warn("Neispravne godine za vozača: " + godine);
                    throw new IspravnaVozacaException("Vozač mora biti stariji od 18 godina!");
                }

                System.out.print("Unesite broj dozvole: ");
                String brojDozvole = unos.nextLine();

                vozaci[i] = new Vozac(ime, prezime, godine, brojDozvole);
                logger.info("Vozač " + ime + " " + prezime + " uspješno kreiran");

            } catch (IspravnaVozacaException e) {
                logger.error("Neispravni podaci o vozaču: " + e.getMessage());
                System.out.println("GREŠKA: " + e.getMessage());
                i = i - 1; // Ponovi unos
            }
        }

        // 2. UNOS PODATAKA ZA VOZILA (Builder Pattern)
        System.out.println("\n=== UNOS PODATAKA ZA VOZILA (Builder Pattern) ===");
        Vozilo[] vozila = new Vozilo[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za vozilo " + (i + 1) + ":");

            try {
                System.out.print("Unesite registraciju: ");
                String registracija = unos.nextLine();

                System.out.print("Unesite model: ");
                String model = unos.nextLine();

                System.out.print("Unesite broj sjedala: ");
                String brojSjedalaString = unos.nextLine();
                int brojSjedala;

                try {
                    brojSjedala = Integer.parseInt(brojSjedalaString);
                } catch (NumberFormatException e) {
                    logger.error("Greška pri konverziji broja sjedala za vozilo " + (i + 1), e);
                    throw new IspravnaVozilaException("Broj sjedala mora biti broj!");
                }

                System.out.print("Unesite godinu proizvodnje: ");
                String godinaString = unos.nextLine();
                int godinaProizvodnje;

                try {
                    godinaProizvodnje = Integer.parseInt(godinaString);
                } catch (NumberFormatException e) {
                    logger.error("Greška pri konverziji godine proizvodnje", e);
                    throw new IspravnaVozilaException("Godina mora biti broj!");
                }

                System.out.print("Unesite dane od zadnjeg odrzavanja: ");
                String daniString = unos.nextLine();
                int daniOdOdrzavanja;

                try {
                    daniOdOdrzavanja = Integer.parseInt(daniString);
                } catch (NumberFormatException e) {
                    logger.error("Greška pri konverziji dana održavanja", e);
                    throw new IspravnaVozilaException("Dani moraju biti broj!");
                }

                System.out.print("Unesite trenutnu lokaciju: ");
                String lokacija = unos.nextLine();

                // Builder Pattern
                vozila[i] = new Vozilo.VoziloBuilder(registracija, model)
                        .brojSjedala(brojSjedala)
                        .godinaProizvodnje(godinaProizvodnje)
                        .daniOdZadnjegOdrzavanja(daniOdOdrzavanja)
                        .trenutnaLokacija(lokacija)
                        .build();

                logger.info("Vozilo " + model + " s registracijom " + registracija + " uspješno kreirano");

            } catch (IspravnaVozilaException e) {
                logger.error("Neispravni podaci o vozilu: " + e.getMessage());
                System.out.println("GREŠKA: " + e.getMessage());
                i = i - 1; // Ponovi unos
            }
        }

        // 3. UNOS PODATAKA ZA PUTNIKE (Record)
        System.out.println("\n=== UNOS PODATAKA ZA PUTNIKE (Record) ===");
        PutnikInfo[] putnici = new PutnikInfo[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za putnika " + (i + 1) + ":");

            System.out.print("Unesite ime: ");
            String ime = unos.nextLine();

            System.out.print("Unesite prezime: ");
            String prezime = unos.nextLine();

            System.out.print("Unesite broj karte: ");
            String brojKarte = unos.nextLine();

            System.out.print("Unesite starost: ");
            String starostString = unos.nextLine();

            try {
                int starost = Integer.parseInt(starostString);
                putnici[i] = new PutnikInfo(ime, prezime, brojKarte, starost);
                logger.info("Putnik " + ime + " " + prezime + " uspješno kreiran");

            } catch (NumberFormatException e) {
                logger.error("Greška pri konverziji starosti za putnika " + (i + 1), e);
                System.out.println("GREŠKA: Starost mora biti broj!");
                i = i - 1; // Ponovi unos
            }
        }

        // 4. UNOS PODATAKA ZA RUTE
        System.out.println("\n=== UNOS PODATAKA ZA RUTE ===");
        Ruta[] rute = new Ruta[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za rutu " + (i + 1) + ":");

            System.out.print("Unesite broj linije: ");
            String brojLineString = unos.nextLine();

            try {
                int brojLinije = Integer.parseInt(brojLineString);

                unos.nextLine();

                System.out.print("Unesite pocetnu stanicu: ");
                String pocetnaStanica = unos.nextLine();

                System.out.print("Unesite zavrsnu stanicu: ");
                String zavrsnaStanica = unos.nextLine();

                rute[i] = new Ruta(brojLinije, pocetnaStanica, zavrsnaStanica, vozila[i], vozaci[i]);
                logger.info("Ruta " + brojLinije + " uspješno kreirana");

            } catch (NumberFormatException e) {
                logger.error("Greška pri konverziji broja linije za rutu " + (i + 1), e);
                System.out.println("GREŠKA: Broj linije mora biti broj!");
                i = i - 1; // Ponovi unos
            }
        }

        // 5. POLIMORFIZAM - Polje Osoba[] s Vozac i Putnik objektima
        System.out.println("\n=== KREIRANJE POLJA ZA POLIMORFIZAM ===");
        Osoba[] osobe = new Osoba[10];

        for (int i = 0; i < 5; i++) {
            osobe[i] = vozaci[i];
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("\nKreiram dodatnog putnika " + (i + 1) + ":");
            System.out.print("Ime: ");
            String ime = unos.nextLine();
            System.out.print("Prezime: ");
            String prezime = unos.nextLine();
            System.out.print("Godine: ");
            String godineString = unos.nextLine();

            try {
                int godine = Integer.parseInt(godineString);
                unos.nextLine();
                System.out.print("Broj karte: ");
                String brojKarte = unos.nextLine();

                osobe[5 + i] = new Putnik(ime, prezime, godine, brojKarte);
                logger.info("Dodatni putnik " + ime + " " + prezime + " kreiran");

            } catch (NumberFormatException e) {
                logger.error("Greška pri kreiranju dodatnog putnika", e);
                System.out.println("GREŠKA: Godine moraju biti broj!");
                i = i - 1; // Ponovi unos
            }
        }

        // GLAVNI IZBORNIK
        boolean nastaviPretragu = true;

        while (nastaviPretragu) {
            System.out.println("\n=== IZBORNIK PRETRAZIVANJA ===");
            System.out.println("1. Pretrazi vozace po prezimenu");
            System.out.println("2. Pretrazi vozila po modelu");
            System.out.println("3. Pretrazi rute po broju linije");
            System.out.println("4. Pronadi najstarijeg vozaca");
            System.out.println("5. Pronadi najnovije vozilo");
            System.out.println("6. Ispis svih ruta");
            System.out.println("7. Polimorfizam - najmlađa i najstarija osoba");
            System.out.println("8. Provjera odrzavanja vozila");
            System.out.println("9. Ispis djecih putnika");
            System.out.println("10. Ispis lokacija vozila");
            System.out.println("11. Izlaz");
            System.out.print("Odaberite opciju: ");

            try {
                int opcija = Integer.parseInt(unos.nextLine());

                if (opcija == 1) {
                    System.out.print("Unesite prezime za pretragu: ");
                    String trazeno = unos.nextLine();
                    boolean pronadeno = false;

                    logger.info("Pretraga vozača po prezimenu: " + trazeno);

                    for (int i = 0; i < 5; i++) {
                        if (vozaci[i].getPrezime().equals(trazeno)) {
                            vozaci[i].predstaviSe();
                            pronadeno = true;
                            logger.info("Vozač pronađen: " + vozaci[i].getIme() + " " + vozaci[i].getPrezime());
                        }
                    }

                    if (pronadeno == false) {
                        System.out.println("Nema vozaca s tim prezimenom.");
                        logger.warn("Vozač s prezimenom " + trazeno + " nije pronađen");
                    }

                } else if (opcija == 2) {
                    System.out.print("Unesite model za pretragu: ");
                    String trazeniModel = unos.nextLine();
                    boolean pronadeno = false;
                    int i = 0;

                    logger.info("Pretraga vozila po modelu: " + trazeniModel);

                    while (i < 5) {
                        if (vozila[i].getModel().equals(trazeniModel)) {
                            System.out.println("Pronadeno: " + vozila[i].getRegistracija() +
                                    " - " + vozila[i].getModel() + ", sjedala: " +
                                    vozila[i].getBrojSjedala());
                            pronadeno = true;
                            logger.info("Vozilo pronađeno: " + vozila[i].getModel());
                        }
                        i = i + 1;
                    }

                    if (pronadeno == false) {
                        System.out.println("Nema vozila tog modela.");
                        logger.warn("Vozilo s modelom " + trazeniModel + " nije pronađeno");
                    }

                } else if (opcija == 3) {
                    System.out.print("Unesite broj linije za pretragu: ");
                    int trazeniBroj = Integer.parseInt(unos.nextLine());
                    boolean pronadeno = false;
                    int i = 0;

                    logger.info("Pretraga ruta po broju linije: " + trazeniBroj);

                    do {
                        if (rute[i].getBrojLinije() == trazeniBroj) {
                            System.out.println("Pronadeno: Linija " + rute[i].getBrojLinije() +
                                    " - " + rute[i].getPocetnaStanica() +
                                    " -> " + rute[i].getZavrsnaStanica());
                            System.out.println("Vozac: " + rute[i].getVozac().getIme() + " " +
                                    rute[i].getVozac().getPrezime());
                            System.out.println("Vozilo: " + rute[i].getVozilo().getModel());
                            pronadeno = true;
                            logger.info("Ruta pronađena: linija " + trazeniBroj);
                        }
                        i = i + 1;
                    } while (i < 5);

                    if (pronadeno == false) {
                        System.out.println("Nema rute s tim brojem linije.");
                        logger.warn("Ruta s brojem linije " + trazeniBroj + " nije pronađena");
                    }

                } else if (opcija == 4) {
                    int indeksNajstarijeg = 0;
                    int maksimalneGodine = vozaci[0].getGodine();

                    logger.info("Pretraga najstarijeg vozača");

                    for (int i = 1; i < 5; i++) {
                        if (vozaci[i].getGodine() > maksimalneGodine) {
                            maksimalneGodine = vozaci[i].getGodine();
                            indeksNajstarijeg = i;
                        }
                    }

                    System.out.println("Najstariji vozac:");
                    vozaci[indeksNajstarijeg].predstaviSe();
                    logger.info("Najstariji vozač: " + vozaci[indeksNajstarijeg].getIme() +
                            ", godine: " + vozaci[indeksNajstarijeg].getGodine());

                } else if (opcija == 5) {
                    int indeksNajnovijeg = 0;
                    int najnovijaGodina = vozila[0].getGodinaProizvodnje();

                    logger.info("Pretraga najnovijeg vozila");

                    for (int i = 1; i < 5; i++) {
                        if (vozila[i].getGodinaProizvodnje() > najnovijaGodina) {
                            najnovijaGodina = vozila[i].getGodinaProizvodnje();
                            indeksNajnovijeg = i;
                        }
                    }

                    System.out.println("Najnovije vozilo: " + vozila[indeksNajnovijeg].getModel() +
                            ", registracija: " + vozila[indeksNajnovijeg].getRegistracija() +
                            ", godina: " + vozila[indeksNajnovijeg].getGodinaProizvodnje());
                    logger.info("Najnovije vozilo: " + vozila[indeksNajnovijeg].getModel() +
                            ", godina: " + vozila[indeksNajnovijeg].getGodinaProizvodnje());

                } else if (opcija == 6) {
                    System.out.println("\n=== SVE RUTE ===");
                    logger.info("Prikaz svih ruta");

                    for (int i = 0; i < 5; i++) {
                        System.out.println("\nLinija " + rute[i].getBrojLinije() + ":");
                        System.out.println("  " + rute[i].getPocetnaStanica() + " -> " +
                                rute[i].getZavrsnaStanica());
                        System.out.println("  Vozac: " + rute[i].getVozac().getIme() + " " +
                                rute[i].getVozac().getPrezime());
                        System.out.println("  Vozilo: " + rute[i].getVozilo().getModel() +
                                " (" + rute[i].getVozilo().getRegistracija() + ")");
                    }

                } else if (opcija == 7) {
                    System.out.println("\n=== POLIMORFIZAM - Najmladja i najstarija osoba ===");
                    logger.info("Prikaz najmlađe i najstarije osobe");

                    int indeksNajmladje = 0;
                    int indeksNajstarije = 0;
                    int minGodine = osobe[0].getGodine();
                    int maxGodine = osobe[0].getGodine();

                    for (int i = 1; i < 10; i++) {
                        if (osobe[i].getGodine() < minGodine) {
                            minGodine = osobe[i].getGodine();
                            indeksNajmladje = i;
                        }
                        if (osobe[i].getGodine() > maxGodine) {
                            maxGodine = osobe[i].getGodine();
                            indeksNajstarije = i;
                        }
                    }

                    System.out.println("Najmladja osoba:");
                    osobe[indeksNajmladje].predstaviSe();

                    System.out.println("\nNajstarija osoba:");
                    osobe[indeksNajstarije].predstaviSe();

                    logger.info("Najmladja osoba: " + osobe[indeksNajmladje].getIme() +
                            ", godine: " + osobe[indeksNajmladje].getGodine());

                } else if (opcija == 8) {
                    System.out.println("\n=== PROVJERA ODRZAVANJA ===");
                    logger.info("Provjera održavanja vozila");

                    for (int i = 0; i < 5; i++) {
                        System.out.println("\nVozilo: " + vozila[i].getModel());
                        int danaPreostalo = vozila[i].daniDoSljedecegOdrzavanja();
                        System.out.println("Dana do sljedeceg odrzavanja: " + danaPreostalo);

                        if (danaPreostalo <= 0) {
                            vozila[i].obavljajOdrzavanje();
                            logger.warn("Vozilo " + vozila[i].getModel() + " treba održavanje!");
                        }
                    }

                } else if (opcija == 9) {
                    System.out.println("\n=== DJECA PUTNICI (mladji od 18) ===");
                    logger.info("Prikaz dječjih putnika");

                    for (int i = 0; i < 5; i++) {
                        if (putnici[i].jeDijete()) {
                            System.out.println(putnici[i].ime() + " " + putnici[i].prezime() +
                                    ", starost: " + putnici[i].starost());
                            logger.info("Dijete putnik: " + putnici[i].ime() + " " + putnici[i].prezime());
                        }
                    }

                } else if (opcija == 10) {
                    System.out.println("\n=== LOKACIJE VOZILA ===");
                    logger.info("Prikaz lokacija vozila");

                    for (int i = 0; i < 5; i++) {
                        System.out.println("Vozilo " + vozila[i].getModel() +
                                " - lokacija: " + vozila[i].dohvatiLokaciju());
                        logger.info("Vozilo " + vozila[i].getModel() +
                                " - lokacija: " + vozila[i].dohvatiLokaciju());
                    }

                } else if (opcija == 11) {
                    nastaviPretragu = false;
                    System.out.println("Izlaz iz programa.");
                    logger.info("=== Kraj programa ===");
                } else {
                    System.out.println("Nepoznata opcija. Pokusajte ponovno.");
                    logger.warn("Korisnik je unio nepoznatu opciju: " + opcija);
                }

            } catch (NumberFormatException e) {
                logger.error("Greška pri konverziji odabira iz izbornika", e);
                System.out.println("GREŠKA: Odabir mora biti broj!");
            }
        }

        unos.close();
    }
}