
public class WagonNaMaterialyCiekle extends WagonTowarowyPodstawowy {

    private double objetoscZbiornika;

    private String[] ksztaltyCysterny = {"Kwadratowy", "Elipsoidalny"};

    public WagonNaMaterialyCiekle(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String przewozonyTowar, String identyfikatorWagonuTowarowego, double objetoscZbiornika) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, przewozonyTowar, identyfikatorWagonuTowarowego);
        this.objetoscZbiornika = objetoscZbiornika;
    }

    public void czyszczenieZbiornika() throws InterruptedException {
        String[] etapy = {"Oproznienie zbiornika", "Przygotowanie do czyszczenia", "Wprowadzenie srodka czyszczacego", "Plukanie zbiornika", "Usuniecie wody", "Kontrola jakosci czyszczenia"};
        for (int i = 0; i < etapy.length; i++) {
            Thread.sleep(1000);
            System.out.println(etapy[i]);
        }
        System.out.println("Zbiornik wyczyszczony!");
    }

    public void wyswietlKsztaltWagonu() {
        String cylinder = "  ***  \n" +
                " *   * \n" +
                "*     *\n" +
                "*     *\n" +
                " *   * \n" +
                "  ***  ";
        int rand = (int) (Math.random() * 2);
        if (rand == 1) {
            System.out.println(cylinder);
        } else {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(" *");
                }
                System.out.println();
            }
        }
    }

}
