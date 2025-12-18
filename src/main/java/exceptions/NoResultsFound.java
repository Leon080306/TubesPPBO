package exceptions;

public class NoResultsFound extends Exception {
    public NoResultsFound() {
        super("User not found");
    }
}
