public class CmdAssignProject extends RecordedCommand {
    private Project project;
    private Team team;
    private ProjectAssignment assignment;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        try {
            this.project = company.searchProject(cmdParts[1]);
            this.team = company.searchTeam(cmdParts[2]);
            if (this.project == null)
                throw new ExProjectNotFound();
            else if (company.searchTeam(cmdParts[2]) == null)
                throw new ExTeamNotFound();
            else {
                if (cmdParts.length == 3)
                    this.assignment = new ProjectAssignment(this.project, this.team);
                else
                    this.assignment = new ProjectAssignment(this.project, this.team, cmdParts);

                addUndoCommand(this);
                clearRedoList();

                System.out.println("Done.");
            }
        } catch (ExProjectNotFound e) {
            System.out.printf("Project %s is not found!\n", cmdParts[1]);
        } catch (ExTeamNotFound e) {
            System.out.printf("Team %s is not found!\n", cmdParts[2]);
        } catch (ExEmployeeNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExProjectAlreadyAssigned e) {
            System.out.printf("Project %s has been assigned to team %s on %s already!\n",
                    project.getCode(), project.getTeam().getTeamName(), project.getAssignmentDate());
        }
    }

    @Override
    public void undoCmd() {
        this.project.deleteAssignment();
        this.team.deleteProject(this.project);

        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        try {
            this.assignment.assignAgain();
        } catch (ExProjectAlreadyAssigned ignored) {
        }

        addUndoCommand(this);
    }
}
