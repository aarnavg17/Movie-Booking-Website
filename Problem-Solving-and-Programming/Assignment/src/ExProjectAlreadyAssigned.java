public class ExProjectAlreadyAssigned extends Exception {
    public ExProjectAlreadyAssigned() {
        super("Project has been assigned or completed!");
    }

    public ExProjectAlreadyAssigned (String message) {
        super(message);
    }
}
