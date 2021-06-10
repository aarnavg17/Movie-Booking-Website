public class ExInstanceOfSingleton extends Exception {
    public ExInstanceOfSingleton() {
        super("Cannot create one more system date instance.");
    }

    public ExInstanceOfSingleton (String message) {
        super(message);
    }
}
