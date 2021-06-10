public class CmdMarkComplete extends RecordedCommand {
    Project project;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        this.project = company.searchProject(cmdParts[1]);
        try {
            if (this.project == null)
                throw new ExProjectNotFound();
            else {
                this.project.markCompleted();

                addUndoCommand(this);
                clearRedoList();

                System.out.println("Done.");
            }
        } catch (ExProjectNotFound e) {
            System.out.printf("Project %s is not found!\n", cmdParts[1]);
        } catch (ExProjectAlreadyCompleted e) {
            System.out.printf("Project %s has been marked as completed before!\n", this.project.getCode());
        } catch (ExProjectNotAssigned e) {
            System.out.printf("Assignment of project %s has not been started!\n", this.project.getCode());
        }
    }

    @Override
    public void undoCmd() {
        this.project.markIncomplete();
        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        try {
            this.project.markCompleted();
        } catch (ExProjectAlreadyCompleted | ExProjectNotAssigned ignored) {
        }
        addUndoCommand(this);
    }
}
