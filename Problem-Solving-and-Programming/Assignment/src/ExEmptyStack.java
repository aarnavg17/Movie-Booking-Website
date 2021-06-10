public class ExEmptyStack extends Exception {
    public ExEmptyStack() {
        super("Empty Stack.");
    }

    public ExEmptyStack (String message) {
        super(message);
    }
}
