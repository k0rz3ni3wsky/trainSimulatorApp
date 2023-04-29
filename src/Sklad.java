
import java.util.*;

public class Sklad implements Runnable {
    private Lokomotywa lokomotywa;
    private ArrayList<Wagon> wagony;
    private int masaWagonow;
    private int wagonyPodlaczoneDoSieciElektrycznejLokomotywy;
    private int nrSkladu;
    private List<StacjaKolejowa> odwiedzoneStacje;
    private int dlugoscTrasyCalkowitej;
    private int dlugoscTrasyDoNastepnejStacji;
    private StacjaKolejowa aktualnaStacja;
    private StacjaKolejowa nastepnaStacja;
    private double procentTrasyDoNastepnejStacji;
    private double procentTrasyDoStacjiDocelowej;
    private static int counter;
    private static List<Sklad> listaPociagow = new ArrayList<>();

    public Sklad(Lokomotywa lokomotywa) {
        this.lokomotywa = lokomotywa;
        this.wagony = new ArrayList<>();
        this.odwiedzoneStacje = new ArrayList<>();
        this.nrSkladu = counter++;
        this.aktualnaStacja = lokomotywa.getStacjaZrodlowa();
        this.nastepnaStacja = lokomotywa.getStacjaZrodlowa();
        listaPociagow.add(this);
    }

    public void dodajWagon(Wagon wagon) throws LimitMasyPrzekroczonyException, LimitWagonowPrzekroczonyException, LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException {
        if (lokomotywa.getMaksymalnyUciag() < masaWagonow)
            throw new LimitMasyPrzekroczonyException();
        if (lokomotywa.getLimitWagonow() < wagony.size())
            throw new LimitWagonowPrzekroczonyException();
        if (wagonyPodlaczoneDoSieciElektrycznejLokomotywy > lokomotywa.getLimitWagonowPodlaczonychDoSieciElektrycznej())
            throw new LimitWagonowPodlaczonychDoSieciElektrycznejLokomotywyPrzekroczonyException();
        wagony.add(wagon);
        masaWagonow += wagon.getWagaNetto() + wagon.getWagaLadunku();
        if (wagon instanceof PodlaczenieDoSieciElektrycznejLokomotywy)
            wagonyPodlaczoneDoSieciElektrycznejLokomotywy++;
    }

    public Lokomotywa getLokomotywa() {
        return lokomotywa;
    }

    public double getProcentTrasyDoStacjiDocelowej() {
        return procentTrasyDoStacjiDocelowej;
    }

    @Override
    public String toString() {
        return "========================================================\n" +
                "Sklad: "+ nrSkladu +" \n"+
                "Lokomotywa: " + lokomotywa.getNrIdentyfikacyjny()+ " \n" +
                "Podlaczone wagony: " + wagony +" ";
    }

    public String raport() {
        return "Lokomotywa: "+lokomotywa.getNrIdentyfikacyjny()+", Stacja Zrodlowa: "+lokomotywa.getStacjaZrodlowa()+", Stacja Docelowa: "+lokomotywa.getStacjaDocelowa()+", \n" +
                "% ukonczonej drogi do stacji docelowej: "+procentTrasyDoStacjiDocelowej+", % ukonczonej drogi do stacji posredniej: "+procentTrasyDoNastepnejStacji+", \n" +
                "liczba wagonow w skladzie: "+wagony.size()+", liczba wagonow podlaczonych do sieci elektrycznej lokomotywy: "+wagonyPodlaczoneDoSieciElektrycznejLokomotywy+", laczna masa ladunku: "+masaWagonow+". ";
    }

    public void wyswietlAktualnaPredkoscPociagu() {
        System.out.println(lokomotywa.getPredkosc());
    }
    public ArrayList<Wagon> getWagony() {
        return wagony;
    }

    public int getMasaWagonow() {
        return masaWagonow;
    }

    public int getWagonyPodlaczoneDoSieciElektrycznejLokomotywy() {
        return wagonyPodlaczoneDoSieciElektrycznejLokomotywy;
    }
    public int getNrSkladu() {
        return nrSkladu;
    }

    public void wyznaczDlugoscIStacjePosrednie() {
        dlugoscTrasyCalkowitej = 0;
        if (aktualnaStacja.equals(lokomotywa.getStacjaDocelowa())) {
            ustalNowaTrase();
        }
        StacjaKolejowa temp = lokomotywa.getStacjaZrodlowa();
        odwiedzoneStacje.add(temp);
        boolean stillWork = true;
        while (stillWork) {
            for (StacjaKolejowa sk : StacjaKolejowa.getListaStacjiKolejowych()) {
                if ((temp.getTrasa().keySet().contains(sk) && !odwiedzoneStacje.contains(sk))) {
                    dlugoscTrasyCalkowitej += temp.getTrasa().get(sk);
                    temp = sk;
                    odwiedzoneStacje.add(temp);
                    if (temp.equals(lokomotywa.getStacjaDocelowa())){
                        stillWork = false;
                        break;
                    }
                    else {
                        for (Map.Entry<StacjaKolejowa, Integer> entry : temp.getTrasa().entrySet()) {
                            if (entry.getKey().equals(lokomotywa.getStacjaDocelowa())) {
                                temp = entry.getKey();
                                dlugoscTrasyCalkowitej += entry.getValue();
                                odwiedzoneStacje.add(temp);
                                stillWork = false;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void ustalNowaTrase() {
        lokomotywa.setStacjaZrodlowa(aktualnaStacja);
        StacjaKolejowa losowaStacja = StacjaKolejowa.getListaStacjiKolejowych().get((int) (Math.random() * StacjaKolejowa.getListaStacjiKolejowych().size()));
        lokomotywa.setStacjaDocelowa(losowaStacja);
        odwiedzoneStacje.clear();
        wyznaczDlugoscIStacjePosrednie();
    }

    public void jedz() throws InterruptedException, RailroadHazardException {
        wyznaczDlugoscIStacjePosrednie();
        double pokonanyDystans = 0;
        double temp = 0;
        for (int i = 0; i < odwiedzoneStacje.size()-1; i++) {
            aktualnaStacja = odwiedzoneStacje.get(i);
            nastepnaStacja = odwiedzoneStacje.get(i + 1);
            if (aktualnaStacja.getTrasa().get(nastepnaStacja) != null && nastepnaStacja != null)
                dlugoscTrasyDoNastepnejStacji = aktualnaStacja.getTrasa().get(nastepnaStacja);
            temp = dlugoscTrasyDoNastepnejStacji;
            synchronized (aktualnaStacja.getTrasa().get(nastepnaStacja)) {
                while (dlugoscTrasyDoNastepnejStacji > 0) {
                    Thread.sleep(1000);
                    dlugoscTrasyDoNastepnejStacji -= lokomotywa.getPredkosc();
                    pokonanyDystans += lokomotywa.getPredkosc();
                    procentTrasyDoNastepnejStacji = ((temp - dlugoscTrasyDoNastepnejStacji) / temp * 100);
                    procentTrasyDoStacjiDocelowej = ((pokonanyDystans / dlugoscTrasyCalkowitej) * 100);
                    if (procentTrasyDoStacjiDocelowej > 100) {
                        procentTrasyDoStacjiDocelowej = 100;
                    }
                    if (procentTrasyDoNastepnejStacji > 100) {
                        procentTrasyDoNastepnejStacji = 100;
                    }
                }
                if (nastepnaStacja.equals(lokomotywa.getStacjaDocelowa())) {
                    aktualnaStacja = nastepnaStacja;
                    ustalNowaTrase();
                    Thread.sleep(30000);
                } else {
                    Thread.sleep(2000);
                }
            }
        }
    }

    public List<StacjaKolejowa> getOdwiedzoneStacje() {
        return odwiedzoneStacje;
    }

    @Override
    public void run() {
        try {
            while (true) {
                jedz();
            }
        } catch (InterruptedException | RailroadHazardException e) {
            e.printStackTrace();
        }
    }
    public static List<Sklad> getListaPociagow () {
            return listaPociagow;
        }

    public StacjaKolejowa getAktualnaStacja() {
        return aktualnaStacja;
    }

}


