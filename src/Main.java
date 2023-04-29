import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean stillWork = true;
        wyswietlMenu();

        while(stillWork) {
            int userInput = scan.nextInt();
            switch (userInput) {
                case 1:
                    wygenerujStacjeOrazPolaczenia();
                    System.out.println("Wygenerowano graf polaczen!");
                    break;
                case 2:
                    if (StacjaKolejowa.getListaStacjiKolejowych().isEmpty()) {
                        System.out.println("Lista stacji kolejowych jest pusta, wygeneruj co najmniej jedna stacje i sprobuj ponownie!");
                        break;
                    }
                    try {
                        for (int i = 0; i < 25; i++)
                            wygenerujSklad();
                        System.out.println("Wygenerowano sklady!");
                    } catch (LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException |
                             LimitWagonowPrzekroczonyException | LimitMasyPrzekroczonyException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    uruchomSymulacje();
                    System.out.println("Symulacja uruchomiona!");
                    break;
                case 4:
                    if (StacjaKolejowa.getListaStacjiKolejowych().isEmpty()) {
                        System.out.println("Do wygenerowania lokomotywy potrzebna jest przynajmniej jedna stacja kolejowa, sprobuj ponownie!");
                        break;
                    }
                    stworzNowaLokomotywe();
                    System.out.println("Utworzono nowa lokomotywe!");
                    break;
                case 5:
                    System.out.println("Podaj ciag znakow aby stworzyc ponizsze wagony:");
                    System.out.println("WBP - wagon bagazowo-pocztowy");
                    System.out.println("WC - wagon chlodniczy");
                    System.out.println("WNMC - wagon na materialy ciekle");
                    System.out.println("WNMG - wagon na materialy gazowe");
                    System.out.println("WNMT - wagon na materialy toksyczne");
                    System.out.println("WNMTC - wagon na materialy toksyczne ciekle");
                    System.out.println("WNMW - wagon na materialy wybuchowe");
                    System.out.println("WPAS - wagon pasazerski");
                    System.out.println("WPOCZ - wagon pocztowy");
                    System.out.println("WRES - wagon restauracyjny");
                    System.out.println("WTCIEZ - wagon towarowy ciezki");
                    System.out.println("WTPODST - wagon towarowy podstawowy");
                    stworzNowyWagon();
                    System.out.println("Utworzono nowy wagon!");
                    break;
                case 6:
                    stworzNowaStacjeKolejowa();
                    System.out.println("Utworzono nowa stacje kolejowa!");
                    break;
                case 7:
                    if (StacjaKolejowa.getListaStacjiKolejowych().size() < 2) {
                        System.out.println("Potrzebne sa dwie stacje kolejowe, wygeneruj je i sprobuj ponownie!");
                        break;
                    }
                    stworzNowePolaczeniePomiedzyStacjami();
                    System.out.println("Utworzono polaczenie pomiedzy stacjami!");
                    break;
                case 8:
                    if (Wagon.getListaWagonow().isEmpty()) {
                        System.out.println("Lista wagonow jest pusta, utworz wagon aby dodac do niego ladunek i sprobuj ponownie!");
                        break;
                    }
                    dodajZaladunekDoWagonu();
                    System.out.println("Dodano zaladunek do wagonu!");
                    break;
                case 9:
                    try {
                        wyswietlRaport();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Niestety sklad o podanym indeksie nie istnieje - sprobuj ponownie!");
                    }
                    break;
                case 10:
                    if (Wagon.getListaWagonow().isEmpty() || Sklad.getListaPociagow().isEmpty()) {
                        System.out.println("Lista wagonow lub skladow jest pusta - utworz przynajmniej po jednym z kazdych obiektow i sprobuj ponownie!");
                        break;
                    }
                    try {
                        dodajWagonDoSkladu();
                        System.out.println("Dodano wagon do skladu!");
                    } catch (LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException |
                             LimitWagonowPrzekroczonyException | LimitMasyPrzekroczonyException e) {
                        e.printStackTrace();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Obiekt o podanym indeksie nie istnieje - sprobuj ponownie!");
                    }
                    break;
                case 11:
                    usunObiekt();
                    break;
                case 12:
                    if (StacjaKolejowa.getListaStacjiKolejowych().isEmpty()) {
                        System.out.println("Do wygenerowania skladu potrzebna jest przynajmniej jedna stacja kolejowa, sprobuj ponownie!");
                        break;
                    }
                    try {
                        wygenerujSklad();
                    } catch (LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException |
                             LimitWagonowPrzekroczonyException | LimitMasyPrzekroczonyException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Wygenerowano sklad!");
                    break;
                case 13:
                    System.out.println("Program zakonczony!");
                    System.exit(0);
                case 14:
                    wyswietlMenu();
                    break;
                default:
                    System.out.println("Nie ma takiej funkcjonalnosci - sprobuj ponownie");
                    break;
            }
        }
    }

    public static void wygenerujStacjeOrazPolaczenia() {
        String[] przewozy = {"Pasazerskie", "Towarowe", "Kombinowane"};
        String[] kategorie = {"Premium", "Wojewodzka", "Aglomeracyjna", "Regionalna", "Lokalna", "Turystyczna"};
        for (int i = 0; i < 125; i++) {
            StacjaKolejowa stacjaKolejowa = new StacjaKolejowa("Stacja"+i, przewozy[(int)(Math.random()*przewozy.length)], kategorie[(int)(Math.random()* kategorie.length)]);
            for (int j = 0; j < 10; j++) {
                int dlugoscTrasy = (int) (Math.random() * 100)+250;
                StacjaKolejowa sk = StacjaKolejowa.getListaStacjiKolejowych().get((int)(Math.random()*StacjaKolejowa.getListaStacjiKolejowych().size()));
                if (!stacjaKolejowa.getTrasa().keySet().contains(sk))
                    stacjaKolejowa.dodajPolaczenie(sk, dlugoscTrasy);
            }
        }
    }

    public static void uruchomSymulacje() {
        for (int i = 0; i < Sklad.getListaPociagow().size(); i++) {
            Thread pociag = new Thread(Sklad.getListaPociagow().get(i));
            Thread lokomotywa = new Thread(Sklad.getListaPociagow().get(i).getLokomotywa());
            lokomotywa.start();
            pociag.start();
        }
        File file = new File("AppState.txt");
        Thread zapis = new Thread(() -> {
            try {
                PrintWriter pw = new PrintWriter(file);
                while (!Thread.interrupted()) {
                    Thread.sleep(5000);
                    for (int i = 0; i < Sklad.getListaPociagow().size(); i++) {
                        Sklad.getListaPociagow().get(i).getWagony().sort((Wagon a, Wagon b) -> a.getWagaBrutto() - b.getWagaBrutto());
                        Sklad.getListaPociagow().sort((Sklad a, Sklad b) -> (int) (b.getProcentTrasyDoStacjiDocelowej() - a.getProcentTrasyDoStacjiDocelowej()));
                        pw.println(Sklad.getListaPociagow().get(i));
                    }
                    pw.flush();
                }
                pw.close();
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        zapis.start();
    }
    public static void wygenerujSklad() throws LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException, LimitWagonowPrzekroczonyException, LimitMasyPrzekroczonyException {
        String[] producenci = {"CRRC Corporation Limited", "Alstom", "Bombardier Transportation", "Siemens", "Kawasaki Heavy Industries", "Stadler Rail"};
        String[] towaryWagChlodniczy = {"Produkty spozywcze", "Leki i szczepionki", "Rosliny", "Kosmetyki i chemikalia"};
        String[] izolacja = {"Pianka poliuretanowa", "Polistyren", "Welna mineralna", "Panele izotermiczne"};
        String[] systemyChlodzenia = {"Mechaniczne", "Termoelektryczne", "Absorpcyjne", "Gazowe"};
        String[] towaryWagNaMaterialyCiekle = {"Substancje chlodzace", "Oleje przemyslowe i smary", "Farby i lakier", "Zywice i kleje", "Chemikalia", "Biopaliwa"};
        String[] towaryWagNaMaterialyGazowe = {"Paliwa gazowe", "Gazy przemyslowe", "Chlodziwa", "Sprezone powierze", "Gazy medyczne", "Gaz ziemny"};
        String[] umocnieniaKonstrukcji = {"Wzmocniona konstrukcja zbiornika", "Wzmocniona konstrukcja osłon", "Specjalne uszczelnienie zlaczy"};
        String[] rodzajeHamulcow = {"Pneumatyczne", "Elektrodynamiczne", "Reczne", "Hydrauliczne", "Magnetyczne"};
        String[] systemyWentylacji = {"Wentylacja mechaniczna", "Wentylacja naturalna", "Systemy filtrujace powietrze"};
        String[] materialyOdpornosciowe = {"Stopy aluminium", "Stal nierdzewna", "Poliweglan", "Poliamid"};
        String[] rodzajeKlimatyzacji = {"Mechaniczna", "Absorpcyjna", "Z obiegiem zamknietym", "Hybrydowa"};
        String[] przewoznik = {"PKP IC", "DB Cargo Polska", "Koleje Mazowieckie", "Polregio", "Koleje Dolnoslaskie"};
        String[] towaryWagTowPodstawowy = {"Kontenery", "Wegiel", "Piach", "Samochody", "Ciezki sprzet"};
        for (int i = 0; i < 2; i++) {
            WagonBagazowoPocztowy wagon = new WagonBagazowoPocztowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*50)+500, (int)(Math.random()*500)+500);
            WagonChlodniczy wagon1 = new WagonChlodniczy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagChlodniczy[(int)(Math.random()*(towaryWagChlodniczy.length))], "CH", izolacja[(int)(Math.random()*izolacja.length)], systemyChlodzenia[(int)(Math.random()*systemyChlodzenia.length)]);
            WagonNaMaterialyCiekle wagon2 = new WagonNaMaterialyCiekle(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagNaMaterialyCiekle[(int)(Math.random()*towaryWagNaMaterialyCiekle.length)], "MC", (int)(Math.random()*50)+500);
            WagonNaMaterialyGazowe wagon3 = new WagonNaMaterialyGazowe(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagNaMaterialyGazowe[(int)(Math.random()*towaryWagNaMaterialyGazowe.length)], "MG", (int)(Math.random()*150)+20, (int)(Math.random()*-(100)+50));
            WagonNaMaterialyToksyczne wagon4 = new WagonNaMaterialyToksyczne(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)], rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], systemyWentylacji[(int)(Math.random()*systemyWentylacji.length)], (int)(Math.random()*10)+2013);
            WagonNaMaterialyToksyczneCiekle wagon5 = new WagonNaMaterialyToksyczneCiekle(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250,umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)],rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], (int)(Math.random()*50)+70, "ERA", "Stal nierdzewna");
            WagonNaMaterialyWybuchowe wagon6 = new WagonNaMaterialyWybuchowe(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250,umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)],rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], materialyOdpornosciowe[(int)(Math.random()*materialyOdpornosciowe.length)]);
            WagonPasazerski wagon7 = new WagonPasazerski(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*50)+50, rodzajeKlimatyzacji[(int)(Math.random()*rodzajeKlimatyzacji.length)]);
            WagonPocztowy wagon8 = new WagonPocztowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*100)+50, przewoznik[(int)(Math.random()* przewoznik.length)]);
            WagonRestauracyjny wagon9 = new WagonRestauracyjny(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*5)+5, (int)(Math.random()*10)+15, (int)(Math.random()*5)+10);
            WagonTowarowyCiezki wagon10 = new WagonTowarowyCiezki(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)], rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)]);
            WagonTowarowyPodstawowy wagon11 = new WagonTowarowyPodstawowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagTowPodstawowy[(int)(Math.random()*towaryWagTowPodstawowy.length)], "TP" );
        }
            int temp = (int)(Math.random()*5)+5;
            stworzNowaLokomotywe();
            Sklad sklad = new Sklad(Lokomotywa.getListaLokomotyw().get((int)(Math.random()*Lokomotywa.getListaLokomotyw().size())));
            for (int j = 0; j < temp; j++) {
                sklad.dodajWagon(Wagon.getListaWagonow().get((int)(Math.random()*Wagon.getListaWagonow().size())));
        }
    }
    public static void stworzNowaLokomotywe() {
        String[] rodzaje = {"Spalinowa", "Elektryczna", "Hybrydowa"};
        Lokomotywa lokomotywa = new Lokomotywa("Lokomotywa", rodzaje[(int)(Math.random()*rodzaje.length)], (int)(Math.random()*50)+1950, StacjaKolejowa.getListaStacjiKolejowych().get((int)(Math.random()*StacjaKolejowa.getListaStacjiKolejowych().size())), StacjaKolejowa.getListaStacjiKolejowych().get((int)(Math.random()*StacjaKolejowa.getListaStacjiKolejowych().size())), StacjaKolejowa.getListaStacjiKolejowych().get((int)(Math.random()*StacjaKolejowa.getListaStacjiKolejowych().size())), (int)(Math.random()*40)+80, 1.435, (int)(Math.random()*10000)+750000, (int)(Math.random()*5)+10, (int)(Math.random()*5)+10);
    }

    public static void stworzNowyWagon() {
        String[] producenci = {"CRRC Corporation Limited", "Alstom", "Bombardier Transportation", "Siemens", "Kawasaki Heavy Industries", "Stadler Rail"};
        String[] towaryWagChlodniczy = {"Produkty spozywcze", "Leki i szczepionki", "Rosliny", "Kosmetyki i chemikalia"};
        String[] izolacja = {"Pianka poliuretanowa", "Polistyren", "Welna mineralna", "Panele izotermiczne"};
        String[] systemyChlodzenia = {"Mechaniczne", "Termoelektryczne", "Absorpcyjne", "Gazowe"};
        String[] towaryWagNaMaterialyCiekle = {"Substancje chlodzace", "Oleje przemyslowe i smary", "Farby i lakier", "Zywice i kleje", "Chemikalia", "Biopaliwa"};
        String[] towaryWagNaMaterialyGazowe = {"Paliwa gazowe", "Gazy przemyslowe", "Chlodziwa", "Sprezone powierze", "Gazy medyczne", "Gaz ziemny"};
        String[] umocnieniaKonstrukcji = {"Wzmocniona konstrukcja zbiornika", "Wzmocniona konstrukcja osłon", "Specjalne uszczelnienie zlaczy"};
        String[] rodzajeHamulcow = {"Pneumatyczne", "Elektrodynamiczne", "Reczne", "Hydrauliczne", "Magnetyczne"};
        String[] systemyWentylacji = {"Wentylacja mechaniczna", "Wentylacja naturalna", "Systemy filtrujace powietrze"};
        String[] materialyOdpornosciowe = {"Stopy aluminium", "Stal nierdzewna", "Poliweglan", "Poliamid"};
        String[] rodzajeKlimatyzacji = {"Mechaniczna", "Absorpcyjna", "Z obiegiem zamknietym", "Hybrydowa"};
        String[] przewoznik = {"PKP IC", "DB Cargo Polska", "Koleje Mazowieckie", "Polregio", "Koleje Dolnoslaskie"};
        String[] towaryWagTowPodstawowy = {"Kontenery", "Wegiel", "Piach", "Samochody", "Ibuprofen"};

        Scanner scan = new Scanner(System.in);
        String wejscieUzyt = scan.next();

        switch (wejscieUzyt) {
            case "WBP":
                WagonBagazowoPocztowy wagon = new WagonBagazowoPocztowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*50)+500, (int)(Math.random()*500)+500);
                break;
            case "WC":
                WagonChlodniczy wagon1 = new WagonChlodniczy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagChlodniczy[(int)(Math.random()*(towaryWagChlodniczy.length))], "CH", izolacja[(int)(Math.random()*izolacja.length)], systemyChlodzenia[(int)(Math.random()*systemyChlodzenia.length)]);
                break;
            case "WNMC":
                WagonNaMaterialyCiekle wagon2 = new WagonNaMaterialyCiekle(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagNaMaterialyCiekle[(int)(Math.random()*towaryWagNaMaterialyCiekle.length)], "MC", (int)(Math.random()*50)+500);
                break;
            case "WNMG":
                WagonNaMaterialyGazowe wagon3 = new WagonNaMaterialyGazowe(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagNaMaterialyGazowe[(int)(Math.random()*towaryWagNaMaterialyGazowe.length)], "MG", (int)(Math.random()*150)+20, (int)(Math.random()*-(100)+50));
                break;
            case "WNMT":
                WagonNaMaterialyToksyczne wagon4 = new WagonNaMaterialyToksyczne(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)], rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], systemyWentylacji[(int)(Math.random()*systemyWentylacji.length)], (int)(Math.random()*10)+2013);
                break;
            case "WNMTC":
                WagonNaMaterialyToksyczneCiekle wagon5 = new WagonNaMaterialyToksyczneCiekle(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250,umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)],rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], (int)(Math.random()*50)+70, "ERA", materialyOdpornosciowe[(int)(Math.random()*materialyOdpornosciowe.length)]);
                break;
            case "WNMW":
                WagonNaMaterialyWybuchowe wagon6 = new WagonNaMaterialyWybuchowe(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250,umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)],rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)], materialyOdpornosciowe[(int)(Math.random()*materialyOdpornosciowe.length)]);
                break;
            case "WPAS":
                WagonPasazerski wagon7 = new WagonPasazerski(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*50)+50, rodzajeKlimatyzacji[(int)(Math.random()*rodzajeKlimatyzacji.length)]);
                break;
            case "WPOCZ":
                WagonPocztowy wagon8 = new WagonPocztowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*100)+50, przewoznik[(int)(Math.random()* przewoznik.length)]);
                break;
            case "WRES":
                WagonRestauracyjny wagon9 = new WagonRestauracyjny(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, (int)(Math.random()*5)+5, (int)(Math.random()*10)+15, (int)(Math.random()*5)+10);
                break;
            case "WTCIEZ":
                WagonTowarowyCiezki wagon10 = new WagonTowarowyCiezki(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, umocnieniaKonstrukcji[(int)(Math.random()*umocnieniaKonstrukcji.length)], rodzajeHamulcow[(int)(Math.random()*rodzajeHamulcow.length)]);
                break;
            case "WTPODST":
                WagonTowarowyPodstawowy wagon11 = new WagonTowarowyPodstawowy(producenci[(int)(Math.random()*producenci.length)], (int)(Math.random()*50)+1970, (int)(Math.random()*1250)+7500, 1.435, (int)(Math.random()*5)+10, (int)(Math.random()*50)+250, towaryWagTowPodstawowy[(int)(Math.random()*towaryWagTowPodstawowy.length)], "TP" );
                break;
            default:
                System.out.println("Nie mozna utworzyc takiego wagonu, sprobuj ponownie!");
                break;
        }
    }

    public static void stworzNowaStacjeKolejowa() {
        String[] przewozy = {"Pasazerskie", "Towarowe", "Kombinowane"};
        String[] kategorie = {"Premium", "Wojewodzka", "Aglomeracyjna", "Regionalna", "Lokalna", "Turystyczna"};
        StacjaKolejowa stacjaKolejowa = new StacjaKolejowa("StacjaUzytkownika",przewozy[(int)(Math.random()*przewozy.length)], kategorie[(int)(Math.random()*kategorie.length)]);
    }

    public static void stworzNowePolaczeniePomiedzyStacjami() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz stacje, pomiedzy ktorymi chcesz stworzyc polaczenie - indeks pierwszej stacji, nastepnie indeks drugiej stacji");
        for (int i = 0; i < StacjaKolejowa.getListaStacjiKolejowych().size(); i++)
            System.out.print(StacjaKolejowa.getListaStacjiKolejowych().get(i)+", ");
        System.out.println();
        int a = scan.nextInt();
        int b = scan.nextInt();
        StacjaKolejowa.getListaStacjiKolejowych().get(a).dodajPolaczenie(StacjaKolejowa.getListaStacjiKolejowych().get(b), (int)(Math.random()*100)+100);
    }

    public static void dodajZaladunekDoWagonu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz wagon, do ktorego chcesz dodac zaladunek, podajac jego indeks");
        for (int i = 0; i < Wagon.getListaWagonow().size(); i++)
            System.out.print("Wagon nr: "+Wagon.getListaWagonow().get(i).getNrIdentyfikacyjny()+", ");
        System.out.println();
        int userInput = scan.nextInt();
        Wagon.getListaWagonow().get(userInput).setWagaLadunku(Wagon.getListaWagonow().get(userInput).getWagaLadunku()+(int)(Math.random()*500)+500);
    }

    public static void wyswietlRaport () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz sklad, o ktorym informacje chcesz wyswietlic, podajac jego indeks");
        for (int i = 0; i < Sklad.getListaPociagow().size(); i++)
            System.out.print("Sklad nr: "+Sklad.getListaPociagow().get(i).getNrSkladu()+", ");
        System.out.println();
        int userInput = scan.nextInt();
        System.out.println(Sklad.getListaPociagow().get(userInput).raport());
    }

    public static void dodajWagonDoSkladu () throws LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException, LimitWagonowPrzekroczonyException, LimitMasyPrzekroczonyException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz wagon, ktory chcesz dodac do skladu, podajac jego indeks");
        for (int i = 0; i < Wagon.getListaWagonow().size(); i++)
            System.out.print("Wagon nr: "+Wagon.getListaWagonow().get(i).getNrIdentyfikacyjny()+", ");
        System.out.println();
        int userInput = scan.nextInt();
        System.out.println("Wybierz sklad, do ktorego chcesz dodac wagon, podajac jego indeks");
        for (int i = 0; i < Sklad.getListaPociagow().size(); i++)
            System.out.print("Sklad nr: "+Sklad.getListaPociagow().get(i).getNrSkladu()+", ");
        System.out.println();
        int userInput2 = scan.nextInt();
        Sklad.getListaPociagow().get(userInput2).dodajWagon(Wagon.getListaWagonow().get(userInput));
    }

    public static void usunObiekt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz jaki obiekt chcesz usunac:");
        System.out.println("1 - stacja kolejowa");
        System.out.println("2 - lokomotywa");
        System.out.println("3 - wagon");
        System.out.println("4 - sklad pociagu");
        int userInput = scan.nextInt();
        switch (userInput) {
            case 1:
                System.out.println("Wybierz jaka stacje kolejowa chcesz usunac, podajac jej indeks");
                for (int i = 0; i < StacjaKolejowa.getListaStacjiKolejowych().size(); i++)
                    System.out.print(StacjaKolejowa.getListaStacjiKolejowych().get(i) + ", ");
                System.out.println();
                int userInput2 = scan.nextInt();
                StacjaKolejowa.getListaStacjiKolejowych().remove(StacjaKolejowa.getListaStacjiKolejowych().get(userInput2));
                System.out.println("Usunieto stacje!");
                break;
            case 2:
                System.out.println("Wybierz jaka lokomotywe chcesz usunac, podajac jej indeks");
                for (int i = 0; i < Lokomotywa.getListaLokomotyw().size(); i++)
                    System.out.print(Lokomotywa.getListaLokomotyw().get(i) + ", ");
                System.out.println();
                int userInput3 = scan.nextInt();
                Lokomotywa.getListaLokomotyw().remove(Lokomotywa.getListaLokomotyw().get(userInput3));
                System.out.println("Usunieto lokomotywe!");
                break;
            case 3:
                System.out.println("Wybierz jaki wagon chcesz usunac, podajac jego indeks");
                for (int i = 0; i < Wagon.getListaWagonow().size(); i++)
                    System.out.print(Wagon.getListaWagonow().get(i) + ", ");
                System.out.println();
                int userInput4 = scan.nextInt();
                Wagon.getListaWagonow().remove(Wagon.getListaWagonow().get(userInput4));
                System.out.println("Usunieto wagon!");
                break;
            case 4:
                System.out.println("Wybierz jaki sklad pociagu chesz usunac, podajac jego indeks");
                for (int i = 0; i < Sklad.getListaPociagow().size(); i++)
                    System.out.print(Sklad.getListaPociagow().get(i) + ", ");
                System.out.println();
                int userInput5 = scan.nextInt();
                Sklad.getListaPociagow().remove(Sklad.getListaPociagow().get(userInput5));
                System.out.println("Usunieto sklad pociagu!");
                break;
            default:
                System.out.println("Nie mozna usunac takiego obiektu - sprobuj ponownie!");
                break;
        }
    }

    public static void wyswietlMenu() {
        System.out.println("Menu funkcjonalnosci:");
        System.out.println("1 - wygeneruj stacje oraz polaczenia pomiedzy nimi (graf)");
        System.out.println("2 - wygeneruj sklady (lokomotywa oraz wagony) -> po utworzeniu co najmniej jednej stacji kolejowej");
        System.out.println("3 - uruchom symulacje");
        System.out.println("4 - stworz nowa lokomotywe -> po utworzeniu co najmniej jednej stacji kolejowej");
        System.out.println("5 - stworz nowy wagon");
        System.out.println("6 - stworz nowa stacje kolejowa");
        System.out.println("7 - stworz nowe polaczenie pomiedzy dwoma stacjami kolejowymi");
        System.out.println("8 - dodaj zaladunek do wagonu");
        System.out.println("9 - wyswietl raport o wskazanym skladzie pociagu");
        System.out.println("10 - dodaj wagon do skladu");
        System.out.println("11 - usun obiekt");
        System.out.println("12 - stworz nowy sklad (lokomotywa i wagony (od 5-10)) -> po utworzeniu co najmniej jednej stacji kolejowej");
        System.out.println("13 - zakoncz dzialanie programu");
        System.out.println("14 - wyswietl menu funkcjonalnosci");
    }
}


