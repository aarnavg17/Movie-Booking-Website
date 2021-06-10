public class ExEmptyFile extends Exception {
    public ExEmptyFile() {
        super("Empty file.");
    }

    public ExEmptyFile (String message) {
        super(message);
    }
}
