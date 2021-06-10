public class ExEmptyUndoStack extends ExEmptyStack {
    public ExEmptyUndoStack() {
        super("Nothing to undo.");
    }

    public ExEmptyUndoStack (String message) {
        super(message);
    }
}
