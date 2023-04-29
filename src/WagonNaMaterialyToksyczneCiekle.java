
public class WagonNaMaterialyToksyczneCiekle extends WagonTowarowyCiezki implements CechyWagonuNaMaterialyCiekle {

    private double objetoscZbiornika;
    private String[] ksztaltyCysterny  = {"Kwadratowy", "Elipsoidalny"};
    private String certyfikacja;
    private String materialOdpornosciowy;

    public WagonNaMaterialyToksyczneCiekle(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String rodzajUmocnieniaKonstrukcji, String rodzajHamulca, double objetoscZbiornika, String certyfikacja, String materialOdpornosciowy) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, rodzajUmocnieniaKonstrukcji, rodzajHamulca);
        this.objetoscZbiornika = objetoscZbiornika;
        this.certyfikacja = certyfikacja;
        this.materialOdpornosciowy = materialOdpornosciowy;
    }


    @Override
    public void czyszczenieZbiornika() {
        String[] etapy = {"Oproznienie zbiornika", "Przygotowanie do czyszczenia", "Wprowadzenie srodka czyszczacego", "Plukanie zbiornika", "Usuniecie wody", "Kontrola jakosci czyszczenia"};
        for (int i = 0; i < etapy.length; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(etapy[i]);
        }
        System.out.println("Zbiornik wyczyszczony!");
    }

    @Override
    public void wyswietlKsztaltWagonu() {
        String cylinder =   "  ***  \n" +
                " *   * \n" +
                "*     *\n" +
                "*     *\n" +
                " *   * \n" +
                "  ***  ";
        int rand = (int)(Math.random()*1);
        if (rand == 1) {
            System.out.println(cylinder);
        }
        else {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(" *");
                }
                System.out.println();
            }
        }
    }

    public void przeprowadzSymulacjeWypadku() throws InterruptedException {
        int czasSymulacji = 2;
        System.out.println("Rozpoczynam symulacje!");
        while (czasSymulacji > 0) {
            Thread.sleep(1500);
            System.out.println("SKRZSTTKSKTTTRZASK!");
            Thread.sleep(1500);
            System.out.println("SKRRRPALPAL");
            czasSymulacji--;
        }
        System.out.println("Symulacja przebiegla pomyslnie!");
    }

    public void wyswietlInformacjeOMaterialeChroniacymPrzedWyciekiem() {
        if (materialOdpornosciowy.equals("Stopy aluminium"))
            System.out.println("tworzywa metaliczne otrzymane przez stopienie aluminium z jednym lub większą liczbą metali (bądź z niemetalami), celowo wytworzone dla uzyskania żądanych właściwości.");
        else if (materialOdpornosciowy.equals("Stal nierdzewna"))
            System.out.println("grupa stali o specjalnych właściwościach fizykochemicznych, odpornych na korozję ze strony np.: czynników atmosferycznych (korozja gazowa), rozcieńczonych kwasów, roztworów alkalicznych (korozja w cieczach).");
        else if (materialOdpornosciowy.equals("Poliweglan"))
            System.out.println("grupa polimerów z grupy poliestrów, będące formalnie estrami kwasu węglowego.\n" +
                    "Poliwęglany są amorficznymi, termoplastycznymi (formowanymi przez wtryskiwanie i wytłaczanie na gorąco) tworzywami sztucznymi o bardzo dobrych własnościach mechanicznych, szczególnie udarności i dużej przezroczystości.");
        else
            System.out.println("polimery, zawierające wiązania amidowe −C(O)−NH− w swoich łańcuchach głównych.\n" +
                    "Wykazują bardzo silną tendencję do krystalizacji, dodatkowo wzmacnianą tworzeniem się wiązań wodorowych między atomem tlenu i azotu z dwóch różnych grup amidowych");
    }
}
