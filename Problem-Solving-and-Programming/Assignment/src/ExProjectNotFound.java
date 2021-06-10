public class ExProjectNotFound extends Exception {
    public ExProjectNotFound() {
        super("Project not found.\n");
    }

    public ExProjectNotFound (String message) {
        super(message);
    }
}