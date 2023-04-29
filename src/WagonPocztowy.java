import java.util.Scanner;

public class WagonPocztowy extends Wagon implements PodlaczenieDoSieciElektrycznejLokomotywy {

    private int iloscSkrytekPocztowych;
    private String przewoznik;

    public WagonPocztowy(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, int iloscSkrytekPocztowych, String przewoznik) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku);
        this.iloscSkrytekPocztowych = iloscSkrytekPocztowych;
        this.przewoznik = przewoznik;
    }

    public void nadajList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj ilosc listow, ktora chcesz nadac (maksymalna liczba to 5 listow naraz)");
        int userInput = scan.nextInt();
        switch (userInput) {
            case 1:
                System.out.println("Nalezy sie 5 zlotych");;
                break;
            case 2:
                System.out.println("Nalezy sie 9 zlotych");
                break;
            case 3:
                System.out.println("Nalezy sie 13.5 zlotych");
                break;
            case 4:
                System.out.println("Nalezy sie 17 zlotych");
                break;
            case 5:
                System.out.println("Nalezy sie 20 zlotych");
                break;
            default:
                System.out.println("Nie mozna nadac takiej ilosci listow");
                break;
        }
    }

    public void wyswietlIleListowZmiesciSieDoWagonu() {
        int sredniaIloscListowWSkrytce = (int)(Math.random()*50)+150;
        int iloscListow = sredniaIloscListowWSkrytce*iloscSkrytekPocztowych;
        System.out.println("Do wagonu mozna zaladowac "+iloscListow+" listow");
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
