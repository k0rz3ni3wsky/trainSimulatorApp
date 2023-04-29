import java.util.ArrayList;
import java.util.List;

public abstract class Wagon {
    private String producent;
    private int rokBudowy;
    private int wagaNetto;
    private double rozstawSzyn;
    private int dlugosc;
    private int wagaLadunku;
    private int nrIdentyfikacyjny;
    private int wagaBrutto;
    private static List<Wagon> listaWagonow = new ArrayList<>();
    private static int counter;

    public Wagon(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku) {
        this.producent = producent;
        this.rokBudowy = rokBudowy;
        this.wagaNetto = wagaNetto;
        this.rozstawSzyn = rozstawSzyn;
        this.dlugosc = dlugosc;
        this.wagaLadunku = wagaLadunku;
        this.wagaBrutto = wagaLadunku+wagaNetto;
        this.nrIdentyfikacyjny = counter++;
        listaWagonow.add(this);
    }

    public int getNrIdentyfikacyjny() {
        return nrIdentyfikacyjny;
    }

    public int getWagaNetto() {
        return wagaNetto;
    }

    public void setWagaLadunku(int wagaLadunku) {
        this.wagaLadunku = wagaLadunku;
    }

    public int getWagaBrutto() {
        return wagaBrutto;
    }

    @Override
    public String toString() {
        return "Wagon: "+nrIdentyfikacyjny+" Producent: "+producent;
    }

    public int getWagaLadunku() {
        return wagaLadunku;
    }

    public static List<Wagon> getListaWagonow() {
        return listaWagonow;
    }

    public void wyswietlWiekWagonu() {
        int wiek = 2023-rokBudowy;
        if (wiek > 40) {
            System.out.println("Wagon pamietajacy czasy PRL, wiek: "+wiek);
        } else
            System.out.println("Wagon wzglednie nowoczesny, wiek: "+wiek);
    }

    public void wyswietlRodzajRozstawuSzyn() {
        if (rozstawSzyn == 143.5)
            System.out.println("Rozstaw normalnotorowy");
        else if (rozstawSzyn > 143.5)
            System.out.println("Rozstaw szerokotorowy");
        else
            System.out.println("Rozstaw wąskotorowy");
    }

    public void dodajLadunek() {
        this.wagaLadunku++;
    }

    public String getProducent() {
        return producent;
    }

    public int getRokBudowy() {
        return rokBudowy;
    }

    public double getRozstawSzyn() {
        return rozstawSzyn;
    }

    public int getDlugosc() {
        return dlugosc;
    }
}
