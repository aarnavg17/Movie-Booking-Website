public class CmdCreateProject extends RecordedCommand {
    private Project project;
    private Company company;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        company = Company.getInstance();
        this.project = new Project(String.format("P%03d", company.getNumberOfProjects() + 1), cmdParts[1]);
        company.addProject(this.project);

        addUndoCommand(this);
        clearRedoList();

        System.out.printf("Project created: [%s] %s (%s)\n", this.project.getCode(), this.project.getTitle(),
                this.project.getCreationDate());
    }

    @Override
    public void undoCmd() {
        company.removeProject(this.project);

        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        company.addProject(this.project);

        addUndoCommand(this);
    }
}
