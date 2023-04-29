import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lokomotywa implements Runnable{
    private String nazwa;
    private String rodzajLokomotywy;
    private int rokBudowy;
    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaDocelowa;
    private StacjaKolejowa stacjaZrodlowa;
    private int predkosc;
    private int nrIdentyfikacyjny;
    private double rozstawSzyn;
    private double maksymalnyUciag;
    private int limitWagonow;
    private int limitWagonowPodlaczonychDoSieciElektrycznej;
    private static List<Lokomotywa> listaLokomotyw = new ArrayList<>();
    private static int counter;

    public Lokomotywa(String nazwa, String rodzajLokomotywy, int rokBudowy, StacjaKolejowa stacjaMacierzysta, StacjaKolejowa stacjaZrodlowa, StacjaKolejowa stacjaDocelowa, int predkosc, double rozstawSzyn, double maksymalnyUciag, int limitWagonow, int limitWagonowPodlaczonychDoSieciElektrycznej) {
        this.nazwa = nazwa;
        this.rokBudowy = rokBudowy;
        this.stacjaMacierzysta = stacjaMacierzysta;
        this.stacjaDocelowa = stacjaDocelowa;
        this.stacjaZrodlowa = stacjaZrodlowa;
        this.predkosc = predkosc;
        this.nrIdentyfikacyjny = counter++;
        this.rozstawSzyn = rozstawSzyn;
        this.maksymalnyUciag = maksymalnyUciag;
        this.limitWagonow = limitWagonow;
        this.limitWagonowPodlaczonychDoSieciElektrycznej = limitWagonowPodlaczonychDoSieciElektrycznej;
        this.rodzajLokomotywy = rodzajLokomotywy;
        listaLokomotyw.add(this);
    }
    public double getMaksymalnyUciag() {
        return maksymalnyUciag;
    }
    public String getNazwa() {
        return nazwa;
    }
    public int getLimitWagonow() {
        return limitWagonow;
    }
    public int getLimitWagonowPodlaczonychDoSieciElektrycznej() {
        return limitWagonowPodlaczonychDoSieciElektrycznej;
    }

    public void zatrab() {
        System.out.println("ŁIIIIIIIIIIIJUUU!");
        Toolkit.getDefaultToolkit().beep();
    }

    public void wyhamuj() {
        int sprawnoscHamulca = (int)(Math.random()*3);
        if (sprawnoscHamulca == 0){
            System.out.println("Hamulec sprawny i zaciagniety!");
            this.predkosc--;
        }
        else if (sprawnoscHamulca == 1)
            System.out.println("Hamulec niesprawny, mamy problem!");
        else
            System.out.println("Prawdziwi kolejarze nie zwalniaja! (no chyba, ze na stacjach)");
    }

    public static List<Lokomotywa> getListaLokomotyw() {
        return listaLokomotyw;
    }
    public int getPredkosc() {
        return predkosc;
    }
    public StacjaKolejowa getStacjaMacierzysta() {
        return stacjaMacierzysta;
    }
    public StacjaKolejowa getStacjaDocelowa() {
        return stacjaDocelowa;
    }
    public StacjaKolejowa getStacjaZrodlowa() {
        return stacjaZrodlowa;
    }
    public void setPredkosc(int predkosc) {
        this.predkosc = predkosc;
    }
    public void setStacjaDocelowa(StacjaKolejowa stacjaDocelowa) {
        this.stacjaDocelowa = stacjaDocelowa;
    }
    public void setStacjaZrodlowa(StacjaKolejowa stacjaZrodlowa) {
        this.stacjaZrodlowa = stacjaZrodlowa;
    }
    public int getNrIdentyfikacyjny() {
        return nrIdentyfikacyjny;
    }

    @Override
    public String toString() {
        return "Lokomotywa: "+nrIdentyfikacyjny+", aktualna predkosc: "+predkosc;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                int rand = (int) (Math.random() * 2);
                if (rand == 1) {
                    predkosc += predkosc * 0.03;
                    if (predkosc > 200)
                        throw new RailroadHazardException(toString());
                } else {
                    if (predkosc > 1) {
                        predkosc -= predkosc * 0.03;
                        if (predkosc > 200)
                            throw new RailroadHazardException(toString());
                    }
                }
            } catch (InterruptedException | RailroadHazardException e) {
                e.printStackTrace();
            }
        }
    }
}
