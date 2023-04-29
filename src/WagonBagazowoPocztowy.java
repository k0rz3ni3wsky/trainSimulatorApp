public class WagonBagazowoPocztowy extends Wagon {

    private double przestrzenBagazowa;
    private double granicaObciazenia;

    public WagonBagazowoPocztowy(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, double przestrzenBagazowa, double granicaObciazenia) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.przestrzenBagazowa = przestrzenBagazowa;
        this.granicaObciazenia = granicaObciazenia;
    }

    public void posortujBagaz() throws InterruptedException {
        int czasSprzatania = 10;
        while (czasSprzatania > 0) {
            czasSprzatania--;
            Thread.sleep(1000);
            System.out.println("Czas pozostaly do posortowania bagazu: "+czasSprzatania);
        }
        System.out.println("Bagaze posortowane!");
    }

    public void wyswietlCiekawostke() {
        int counter = 0;
        int maksymalnaWagaBagazuPrzyZakupieStandardowegoBiletuWizzAir = 10;
        for (int i = 0; i < granicaObciazenia; i++) {
            if (granicaObciazenia > 0) {
                granicaObciazenia -= maksymalnaWagaBagazuPrzyZakupieStandardowegoBiletuWizzAir;
                counter++;
            }
        }
        System.out.println("Do tego wagonu mozna zaladowac "+counter+" walizek o wadze idealnie mieszczacej sie w limicie standardowego biletu Wizzair ;)");
    }
}
