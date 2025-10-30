package app;

import entities.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);


        System.out.println(" UNOS PODATAKA ZA VOZACE ");
        Vozac[] vozaci = new Vozac[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za vozaca " + (i + 1) + ":");

            System.out.print("Unesite ime: ");
            String ime = unos.nextLine();

            System.out.print("Unesite prezime: ");
            String prezime = unos.nextLine();

            System.out.print("Unesite godine: ");
            int godine = unos.nextInt();
            unos.nextLine();

            System.out.print("Unesite broj dozvole: ");
            String brojDozvole = unos.nextLine();

            vozaci[i] = new Vozac(ime, prezime, godine, brojDozvole);
        }


        System.out.println("\n UNOS PODATAKA ZA VOZILA (Builder Pattern) ");
        Vozilo[] vozila = new Vozilo[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za vozilo " + (i + 1) + ":");

            System.out.print("Unesite registraciju: ");
            String registracija = unos.nextLine();

            System.out.print("Unesite model: ");
            String model = unos.nextLine();

            System.out.print("Unesite broj sjedala: ");
            int brojSjedala = unos.nextInt();

            System.out.print("Unesite godinu proizvodnje: ");
            int godinaProizvodnje = unos.nextInt();

            System.out.print("Unesite dane od zadnjeg odrzavanja: ");
            int daniOdOdrzavanja = unos.nextInt();
            unos.nextLine();

            System.out.print("Unesite trenutnu lokaciju: ");
            String lokacija = unos.nextLine();


            vozila[i] = new Vozilo.VoziloBuilder(registracija, model)
                    .brojSjedala(brojSjedala)
                    .godinaProizvodnje(godinaProizvodnje)
                    .daniOdZadnjegOdrzavanja(daniOdOdrzavanja)
                    .trenutnaLokacija(lokacija)
                    .build();
        }


        System.out.println("\n UNOS PODATAKA ZA PUTNIKE (Record) ");
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
            int starost = unos.nextInt();
            unos.nextLine();

            putnici[i] = new PutnikInfo(ime, prezime, brojKarte, starost);
        }


        System.out.println("\n UNOS PODATAKA ZA RUTE ");
        Ruta[] rute = new Ruta[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nUnos podataka za rutu " + (i + 1) + ":");

            System.out.print("Unesite broj linije: ");
            int brojLinije = unos.nextInt();
            unos.nextLine();

            System.out.print("Unesite pocetnu stanicu: ");
            String pocetnaStanica = unos.nextLine();

            System.out.print("Unesite zavrsnu stanicu: ");
            String zavrsnaStanica = unos.nextLine();

            rute[i] = new Ruta(brojLinije, pocetnaStanica, zavrsnaStanica, vozila[i], vozaci[i]);
        }


        System.out.println("\n KREIRANJE POLJA ZA POLIMORFIZAM ");
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
            int godine = unos.nextInt();
            unos.nextLine();
            System.out.print("Broj karte: ");
            String brojKarte = unos.nextLine();

            osobe[5 + i] = new Putnik(ime, prezime, godine, brojKarte);
        }


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
            int opcija = unos.nextInt();
            unos.nextLine();

            if (opcija == 1) {
                System.out.print("Unesite prezime za pretragu: ");
                String trazeno = unos.nextLine();
                boolean pronadeno = false;

                for (int i = 0; i < 5; i++) {
                    if (vozaci[i].getPrezime().equals(trazeno)) {
                        vozaci[i].predstaviSe();
                        pronadeno = true;
                    }
                }

                if (pronadeno == false) {
                    System.out.println("Nema vozaca s tim prezimenom.");
                }

            } else if (opcija == 2) {
                System.out.print("Unesite model za pretragu: ");
                String trazeniModel = unos.nextLine();
                boolean pronadeno = false;
                int i = 0;

                while (i < 5) {
                    if (vozila[i].getModel().equals(trazeniModel)) {
                        System.out.println("Pronadeno: " + vozila[i].getRegistracija() +
                                " - " + vozila[i].getModel() + ", sjedala: " +
                                vozila[i].getBrojSjedala());
                        pronadeno = true;
                    }
                    i = i + 1;
                }

                if (pronadeno == false) {
                    System.out.println("Nema vozila tog modela.");
                }

            } else if (opcija == 3) {
                System.out.print("Unesite broj linije za pretragu: ");
                int trazeniBroj = unos.nextInt();
                unos.nextLine();
                boolean pronadeno = false;
                int i = 0;

                do {
                    if (rute[i].getBrojLinije() == trazeniBroj) {
                        System.out.println("Pronadeno: Linija " + rute[i].getBrojLinije() +
                                " - " + rute[i].getPocetnaStanica() +
                                " -> " + rute[i].getZavrsnaStanica());
                        System.out.println("Vozac: " + rute[i].getVozac().getIme() + " " +
                                rute[i].getVozac().getPrezime());
                        System.out.println("Vozilo: " + rute[i].getVozilo().getModel());
                        pronadeno = true;
                    }
                    i = i + 1;
                } while (i < 5);

                if (pronadeno == false) {
                    System.out.println("Nema rute s tim brojem linije.");
                }

            } else if (opcija == 4) {
                int indeksNajstarijeg = 0;
                int maksimalneGodine = vozaci[0].getGodine();

                for (int i = 1; i < 5; i++) {
                    if (vozaci[i].getGodine() > maksimalneGodine) {
                        maksimalneGodine = vozaci[i].getGodine();
                        indeksNajstarijeg = i;
                    }
                }

                System.out.println("Najstariji vozac:");
                vozaci[indeksNajstarijeg].predstaviSe();

            } else if (opcija == 5) {
                int indeksNajnovijeg = 0;
                int najnovijaGodina = vozila[0].getGodinaProizvodnje();

                for (int i = 1; i < 5; i++) {
                    if (vozila[i].getGodinaProizvodnje() > najnovijaGodina) {
                        najnovijaGodina = vozila[i].getGodinaProizvodnje();
                        indeksNajnovijeg = i;
                    }
                }

                System.out.println("Najnovije vozilo: " + vozila[indeksNajnovijeg].getModel() +
                        ", registracija: " + vozila[indeksNajnovijeg].getRegistracija() +
                        ", godina: " + vozila[indeksNajnovijeg].getGodinaProizvodnje());

            } else if (opcija == 6) {
                System.out.println("\n=== SVE RUTE ===");
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

            } else if (opcija == 8) {

                System.out.println("\n=== PROVJERA ODRZAVANJA ===");
                for (int i = 0; i < 5; i++) {
                    System.out.println("\nVozilo: " + vozila[i].getModel());
                    int danaPreostalo = vozila[i].daniDoSljedecegOdrzavanja();
                    System.out.println("Dana do sljedeceg odrzavanja: " + danaPreostalo);

                    if (danaPreostalo <= 0) {
                        vozila[i].obavljajOdrzavanje();
                    }
                }

            } else if (opcija == 9) {

                System.out.println("\n=== DJECA PUTNICI (mladji od 18) ===");
                for (int i = 0; i < 5; i++) {
                    if (putnici[i].jeDijete()) {
                        System.out.println(putnici[i].ime() + " " + putnici[i].prezime() +
                                ", starost: " + putnici[i].starost());
                    }
                }

            } else if (opcija == 10) {

                System.out.println("\n=== LOKACIJE VOZILA ===");
                for (int i = 0; i < 5; i++) {
                    System.out.println("Vozilo " + vozila[i].getModel() +
                            " - lokacija: " + vozila[i].dohvatiLokaciju());
                }

            } else if (opcija == 11) {
                nastaviPretragu = false;
                System.out.println("Izlaz iz programa.");
            } else {
                System.out.println("Nepoznata opcija. Pokusajte ponovno.");
            }
        }

        unos.close();
    }
}