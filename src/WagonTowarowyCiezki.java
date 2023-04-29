
public class WagonTowarowyCiezki extends Wagon {

    private String rodzajUmocnieniaKonstrukcji;
    private String rodzajHamulca;

    public WagonTowarowyCiezki(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String rodzajUmocnieniaKonstrukcji, String rodzajHamulca) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.rodzajUmocnieniaKonstrukcji = rodzajUmocnieniaKonstrukcji;
        this.rodzajHamulca = rodzajHamulca;
    }

    public void wyswietlRodzajeHamulcowMozliweDoZamontowania() {
        String[] rodzajeHamulcow = {"Pneumatyczne", "Elektrodynamiczne", "Reczne", "Hydrauliczne", "Magnetyczne"};
        for (int i = 0; i < rodzajeHamulcow.length; i++)
            System.out.print(rodzajeHamulcow[i]+", ");
        System.out.println();
    }
    public void zaladujWagonGruzem() {
        dodajLadunek();
        System.out.println("Zaladowano wagon gruzem!");
    }
    @Override
    public void dodajLadunek() {
        int waga = getWagaLadunku();
        int rand = (int)(Math.random()*750)+500;
        setWagaLadunku(waga + rand);
        System.out.println("Dodano ladunek!");
    }
}
