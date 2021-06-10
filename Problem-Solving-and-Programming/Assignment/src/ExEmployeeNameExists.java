public class ExEmployeeNameExists extends Exception {
    public ExEmployeeNameExists() {
        super("Employee name already exists!");
    }

    public ExEmployeeNameExists(String message) {
        super(message);
    }
}
