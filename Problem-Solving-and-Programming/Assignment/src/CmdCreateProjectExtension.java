public class CmdCreateProjectExtension extends RecordedCommand {
    private ProjectExtension projectExtension;
    private Project parentProject;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        this.parentProject = company.searchProject(cmdParts[1]);
        try {
            if (this.parentProject == null)
                throw new ExProjectNotFound();
            else {
                this.projectExtension = new ProjectExtension(String.format("%s-E%d",
                        cmdParts[1], this.parentProject.getNumberofExtensions() + 1), cmdParts[2]);
                this.parentProject.addExtension(this.projectExtension);

                addUndoCommand(this);
                clearRedoList();

                System.out.printf("Project created: [%s] %s (%s)\n", this.projectExtension.getCode(),
                        this.projectExtension.getTitle(), this.projectExtension.getCreationDate());
            }
        } catch (ExProjectNotFound e) {
            System.out.printf("Project %s is not found!\n", cmdParts[1]);
        }

    }

    @Override
    public void undoCmd() {
        this.parentProject.removeExtension(this.projectExtension);

        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        this.parentProject.addExtension(this.projectExtension);

        addUndoCommand(this);
    }
}
