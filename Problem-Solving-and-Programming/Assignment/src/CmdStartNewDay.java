public class CmdStartNewDay extends RecordedCommand {
    private String previousDay;
    private String newDay;

    @Override
    public void execute(String[] cmdParts) throws ArrayIndexOutOfBoundsException, ExInsufficientArguments {
        CurrentDay currDay = CurrentDay.getInstance();
        this.previousDay = currDay.toString();
        this.newDay = new Day(cmdParts[1]).toString();
        currDay.updateCurrentDay(this.newDay);

        addUndoCommand(this);
        clearRedoList();

        System.out.println("Done.");
    }

    @Override
    public void undoCmd() {
        try {
            CurrentDay currDay = CurrentDay.getInstance();
            currDay.updateCurrentDay(this.previousDay);
            addRedoCommand(this);
        } catch (ExInsufficientArguments e) {
            System.out.println("Insufficient command arguments.");
        }
    }

    @Override
    public void redoCmd() {
        try {
            CurrentDay currDay = CurrentDay.getInstance();
            currDay.updateCurrentDay(this.newDay);
            addUndoCommand(this);
        } catch (ExInsufficientArguments e) {
            System.out.println("Insufficient command arguments.");
        }
    }
}
