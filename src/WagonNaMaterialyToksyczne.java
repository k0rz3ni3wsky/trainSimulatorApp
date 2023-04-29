
public class WagonNaMaterialyToksyczne extends WagonTowarowyCiezki {

    private String systemWentylacji;
    private int rokPrzegladuSystemuWentylacji;
    private boolean aktualnoscPrzegladu;

    public WagonNaMaterialyToksyczne(String producent, int rokBudowy, int wagaNetto, double rozstawSzyn, int dlugosc, int wagaLadunku, String rodzajUmocnieniaKonstrukcji, String rodzajHamulca, String systemWentylacji, int rokPrzegladuSystemuWentylacji) {
        super(producent, rokBudowy, wagaNetto, rozstawSzyn, dlugosc, wagaLadunku, rodzajUmocnieniaKonstrukcji, rodzajHamulca);
        this.systemWentylacji = systemWentylacji;
        this.rokPrzegladuSystemuWentylacji = rokPrzegladuSystemuWentylacji;
        this.aktualnoscPrzegladu = true;
    }


    public void czyPrzegladSystemuWentylacji() {
        if (rokPrzegladuSystemuWentylacji == 2023) {
            System.out.println("Przeglad aktualny!");
            aktualnoscPrzegladu = true;
        }
        else {
            System.out.println("Przeglad nieaktualny! - koniecznosc wykonania przegladu");
            aktualnoscPrzegladu = false;
        }
    }

   public void wyswietlOznakowanieWagonu () {
    int oznakowanie = (int)(Math.random()*2);
    if (oznakowanie == 0) {
        System.out.println("!!!!!!!!!");
        System.out.println("!! *T* !!");
        System.out.println("!! *O* !!");
        System.out.println("!! *K* !!");
        System.out.println("!! *S* !!");
        System.out.println("!! *Y* !!");
        System.out.println("!! *C* !!");
        System.out.println("!! *Z* !!");
        System.out.println("!! *N* !!");
        System.out.println("!! *E* !!");
        System.out.println("!!!!!!!!!");
    }
    else {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!! *T*O*K*S*Y*C*Z*N*E* !!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

   }
}
