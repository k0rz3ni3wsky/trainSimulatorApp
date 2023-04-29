public class WagonPasazerski extends Wagon implements PodlaczenieDoSieciElektrycznejLokomotywy {

    private int iloscMiejsc;
    private String klimatyzacja;
    private boolean czyKlimatyzacjaWlaczona;
    private boolean toaletaZajeta;

    public WagonPasazerski(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, int iloscMiejsc, String klimatyzacja) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.iloscMiejsc = iloscMiejsc;
        this.klimatyzacja = klimatyzacja;
        this.czyKlimatyzacjaWlaczona = true;
        this.toaletaZajeta = false;
    }

    public void otworzOkno() {
        if (czyKlimatyzacjaWlaczona)
            System.out.println("Nie mozna otworzyc okien - dziala klimatyzacja!");
        else
            System.out.println("Okna otwarte!");
    }

    public void idzDoToalety() throws InterruptedException {
        if (!toaletaZajeta) {
            toaletaZajeta = true;
            System.out.println("Ale ulga!");
        }
        else if (toaletaZajeta) {
            System.out.println("Chwileczke, zajete");
            Thread.sleep(5000);
            System.out.println("Toaleta jest wolna, zapraszam!");
        }
    }

    public void setKlimatyzacja(String klimatyzacja) {
        this.klimatyzacja = klimatyzacja;
    }

    @Override
    public void wyswietlKomunikatOPodlaczeniu() {
        System.out.println("Wagon podlaczony do sieci elektycznej lokomotywy!");
    }

    @Override
    public void wyswietlNapieciekV() {
        System.out.println("Napiecie wynosi 3kV");
    }
}
