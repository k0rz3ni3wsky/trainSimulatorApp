import java.util.Scanner;

public class WagonNaMaterialyWybuchowe extends WagonTowarowyCiezki {

    private String materialWzmacniajacyKonstrukcje;
    private boolean systemMonitorowania;
    private boolean systemGasniczy;

    public WagonNaMaterialyWybuchowe(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String rodzajUmocnieniaKonstrukcji, String rodzajHamulca, String materialWzmacniajacyKonstrukcje) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, rodzajUmocnieniaKonstrukcji, rodzajHamulca);
        this.materialWzmacniajacyKonstrukcje = materialWzmacniajacyKonstrukcje;
        this.systemGasniczy = true;
        this.systemMonitorowania = true;
    }

    public void wlaczSystemy() {
        systemMonitorowania = true;
        systemGasniczy = true;
        System.out.println("Systemy bezpieczenstwa wlaczone!");
    }

    public void wylaczSystemy() {
        if (!systemGasniczy && !systemMonitorowania)
            System.out.println("Systemy juz sa nieaktywne!");
        else {
            systemMonitorowania = false;
            systemGasniczy = false;
            System.out.println("Systemy nieaktywne!");
        }
    }

    public void zdetonujLadunek() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Czy napewno chcesz wysadzic pociag - wpisz T (TAK) lub N (NIE)");
        int userInput = scan.next().charAt(0);
        if (userInput == 'T')
            System.exit(0);
        else
            System.out.println("Procedura detonacji wylaczona!");
    }

}
