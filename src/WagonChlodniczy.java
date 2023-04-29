public class WagonChlodniczy extends WagonTowarowyPodstawowy implements PodlaczenieDoSieciElektrycznejLokomotywy {

    private String izolacjaTermiczna;
    private String systemChlodzenia;

    public WagonChlodniczy(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String przewozonyTowar, String identyfikatorWagonuTowarowego, String izolacjaTermiczna, String systemChlodzenia) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, przewozonyTowar, identyfikatorWagonuTowarowego);
        this.izolacjaTermiczna = izolacjaTermiczna;
        this.systemChlodzenia = systemChlodzenia;
    }

    public void wyswietlProducentaSystemuChlodzenia() {
        String[] producenci = {"Carrier Transicold", "Thermo King", "Mitsubishi Electric", "Zanotti", "Eberspracher"};
        int rand = (int)(Math.random()*producenci.length);
        System.out.println(producenci[rand]);
    }

    public void sprawdzSystemChlodzenia() throws InterruptedException {
        int rand = (int)(Math.random()*2);
        int czas = 5;
        if (rand == 0) {
            System.out.println("System chlodzenia sprawny, transport jest bezpieczny!");
        }
        else {
            System.out.println("System chlodzenia niesprawny, uruchamiam systemy awaryjne!");
            while (czas > 0) {
                Thread.sleep(1000);
                czas--;
                System.out.println(czas);
            }
            System.out.println("Systemy awaryjne uruchomione - transport bezpieczny!");
        }
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
