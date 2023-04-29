import java.util.*;

public class StacjaKolejowa {

    private String miasto;
    private String rodzajPrzewozow;
    private String kategoria;
    private Map<StacjaKolejowa, Integer> trasa;
    private static List<StacjaKolejowa> listaStacjiKolejowych = new ArrayList<>();

    public StacjaKolejowa(String miasto, String rodzajPrzewozow, String kategoria) {
        this.miasto = miasto;
        this.rodzajPrzewozow = rodzajPrzewozow;
        this.kategoria = kategoria;
        this.trasa = new HashMap<>();
        listaStacjiKolejowych.add(this);
    }

    public void opoznieniaKomunikaty() {
        int rand = (int)(Math.random()*3);
        boolean stanTechnicznyTablicyWyswietlajacejKomunikaty = false;
        if (rand == 0)
            System.out.println("Nie ma opoznien, wszystkie przyjazdy i odjazdy zgodnie z harmonogramem!");
        else if (rand == 1 && !stanTechnicznyTablicyWyswietlajacejKomunikaty)
            System.out.println("Tablica wyswietlajaca komunikaty nie dziala :(");
        else
            System.out.println("Pociag "+Sklad.getListaPociagow().get((int) (Math.random()*Sklad.getListaPociagow().size()))+" opozniony o 10 godzin, za utrudnienia przepraszamy");
    }

    public void zapukajDoAdministracji() {
        int humorPaniSekretarki = (int)(Math.random()*2);
        if (humorPaniSekretarki == 0)
            System.out.println("Trwa przerwa obiadowa - prosze pobrac numerek i poczekac w kolejce!");
        else
            System.out.println("Zapraszam serdecznie, w czy moge pomoc? :)");
    }

    public void dodajPolaczenie (StacjaKolejowa stacjaKolejowa, Integer dluglosc) {
        if (!trasa.keySet().contains(stacjaKolejowa)) {
            trasa.put(stacjaKolejowa, dluglosc);
            stacjaKolejowa.dodajPolaczenie(this, dluglosc);
        }
    }
    public String getMiasto() {
        return miasto;
    }
    public static List<StacjaKolejowa> getListaStacjiKolejowych() {
        return listaStacjiKolejowych;
    }
    public Map<StacjaKolejowa, Integer> getTrasa() {
        return trasa;
    }

    @Override
    public String toString() {
        return "("+miasto+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StacjaKolejowa that = (StacjaKolejowa) o;
        return Objects.equals(miasto, that.miasto) && Objects.equals(rodzajPrzewozow, that.rodzajPrzewozow) && Objects.equals(kategoria, that.kategoria);
    }
    @Override
    public int hashCode() {
        return Objects.hash(miasto, rodzajPrzewozow, kategoria);
    }
}
