public class RailroadHazardException extends Exception {
    public RailroadHazardException(String message) {
        super("Przekroczono 200km/h! "+message);
    }
}
