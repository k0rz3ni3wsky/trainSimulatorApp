import java.util.Scanner;
public class WagonRestauracyjny extends Wagon implements PodlaczenieDoSieciElektrycznejLokomotywy {

    private int iloscStolikow;
    private double przestrzenRestauracyjna;
    private double przestrzenKuchenna;

    public WagonRestauracyjny(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, int iloscStolikow, double przestrzenRestauracyjna, double przestrzenKuchenna) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.iloscStolikow = iloscStolikow;
        this.przestrzenKuchenna = przestrzenKuchenna;
        this.przestrzenRestauracyjna = przestrzenRestauracyjna;
    }


    public void wyswietlIloscSiedzien() {
        System.out.println("Ilosc miejsc siedzacych przy stolikach: "+(iloscStolikow*4));
    }

    public void kupDanie () {
        Scanner scan = new Scanner(System.in);
        System.out.println("MENU: ");
        System.out.println("1 Gnoczci");
        System.out.println("2 Schab po parysku");
        System.out.println("3 Pierogi ruskie z cebula i boczkiem");
        System.out.println("4 Grilled cheese sandwich z szynka i pomidorem");
        System.out.println("5 Kebab z kurczakiem, salatka i sosem tzatziki");
        System.out.println("6 Filet z lososia z ryzem i warzywami na parze");
        int userInput = scan.nextInt();
        switch (userInput) {
            case 1:
                System.out.println("25 zlotych");
                break;
            case 2:
                System.out.println("35 zlotych");
                break;
            case 3:
                System.out.println("20 zlotych");
                break;
            case 4:
                System.out.println("35 zlotych");
                break;
            case 5:
                System.out.println("30 zlotych");
                break;
            case 6:
                System.out.println("40 zlotych");
                break;
            default:
                System.out.println("Nie mamy takiego dania - sprobuj ponownie!");
                break;
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
