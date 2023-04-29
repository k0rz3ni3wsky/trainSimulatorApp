public class WagonTowarowyPodstawowy extends Wagon {

    private String przewozonyTowar;
    private String identyfikatorWagonuTowarowego;

    public WagonTowarowyPodstawowy(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String przewozonyTowar, String identyfikatorWagonuTowarowego) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.przewozonyTowar = przewozonyTowar;
        this.identyfikatorWagonuTowarowego = identyfikatorWagonuTowarowego;
    }

    public String getPrzewozonyTowar() {
        return przewozonyTowar;
    }

    public void przeprowadzInspekcjeTowaru() {
        int counter = 0;
        String[] substancjeZabrionone = {"Ibuprofen", "Kwas askorbinowy"};
        for (int i = 0; i < substancjeZabrionone.length; i++)
            if (przewozonyTowar.equals(substancjeZabrionone[i]))
                counter++;
        if (counter > 0)
            System.out.println("Wagon przewozi substancje niedozwolona!");
        else
            System.out.println("Wszystko w porzadku!");
    }

    public void przejzyzjWagonPrzedTrainHopperami() {
        int szansaNaZlapanieGagatka = (int)(Math.random()*2);
        if (szansaNaZlapanieGagatka == 1)
            System.out.println("Nikogo nie ma, wagony przeszukane!");
        else
            System.out.println("Jak w Subway Surfers - uciekli nam, nikogo juz nie ma, mozna ruszac!");
    }
}
