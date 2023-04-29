public class WagonNaMaterialyGazowe extends WagonTowarowyPodstawowy {

    private double cisnienieProbne;
    private double cisnienieRobocze;
    private double cisnieniePodczasTransportu;
    private int najnizszaTemperaturaPelnienia;

    public WagonNaMaterialyGazowe(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String przewozonyTowar, String identyfikatorWagonuTowarowego, double cisnienieRobocze, int najnizszaTemperaturaPelnienia) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, przewozonyTowar, identyfikatorWagonuTowarowego);
        this.cisnienieRobocze = cisnienieRobocze;
        this.cisnienieProbne = 1.5*cisnienieRobocze;
        this.cisnieniePodczasTransportu = cisnienieRobocze-10;
        this.najnizszaTemperaturaPelnienia = najnizszaTemperaturaPelnienia;
    }


    public void wyswietlOdpowiednieCisnienieProbne() { //do ew rozwinięcia
        System.out.println(1.75*cisnienieRobocze+" MPa");
    }

    public void sprawdzCisnienie() {
        if (cisnieniePodczasTransportu <= cisnienieRobocze)
            System.out.println("Cisnienie prawidlowe!");
        else
            System.out.println("Cisnienie robocze przekroczone - ryzyko uszkodzenia zbiornika lub wycieku!");
    }

}
