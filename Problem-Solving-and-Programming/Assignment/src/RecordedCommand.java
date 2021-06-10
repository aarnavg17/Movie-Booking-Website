import java.util.Stack;

public abstract class RecordedCommand implements Command {
    public abstract void undoCmd();
    public abstract void redoCmd();

    private static final Stack<RecordedCommand> undoList = new Stack<>();
    private static final Stack<RecordedCommand> redoList = new Stack<>();

    protected static void addUndoCommand(RecordedCommand cmd) {
        undoList.push(cmd);
    }
    protected static void addRedoCommand(RecordedCommand cmd) {
        redoList.push(cmd);
    }

    protected static void clearRedoList() {
        redoList.clear();
    }

    public static void undoOneCommand() throws ExEmptyUndoStack {
        if (undoList.isEmpty())
            throw new ExEmptyUndoStack();
        undoList.peek().undoCmd();
        undoList.pop();
    }
    public static void redoOneCommand() throws ExEmptyRedoStack {
        if (redoList.isEmpty())
            throw new ExEmptyRedoStack();
        redoList.peek().redoCmd();
        redoList.pop();
    }
}
