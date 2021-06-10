public class ExEmployeeNotFound extends Exception {
    public ExEmployeeNotFound() {
        super("Employee not found.");
    }

    public ExEmployeeNotFound (String emp) {
        super(String.format("Employee %s is not found!", emp));
    }
}
