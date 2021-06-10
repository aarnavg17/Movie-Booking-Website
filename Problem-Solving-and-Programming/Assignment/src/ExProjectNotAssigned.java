public class ExProjectNotAssigned extends Exception {
    public ExProjectNotAssigned() {
        super("Project assignment has not been started!");
    }

    public ExProjectNotAssigned (String message) {
        super(message);
    }
}
