public class ExProjectAlreadyCompleted extends Exception {
    public ExProjectAlreadyCompleted() {
        super("Project has been completed!");
    }

    public ExProjectAlreadyCompleted (String message) {
        super(message);
    }
}
