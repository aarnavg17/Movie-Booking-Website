public class CmdSetUpTeam extends RecordedCommand {
    Company company;
    Team team;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        company = Company.getInstance();
        try {
            if (company.searchTeam(cmdParts[1]) != null)
                throw new ExTeamNameExists();
            else if (company.searchEmployee(cmdParts[2]) == null)
                throw new ExEmployeeNotFound();
            else if (company.searchEmployee(cmdParts[2]).getTeam() != null)
                throw new ExEmployeeBelongsToTeam();
            else {
                this.team = new Team(cmdParts[1], company.searchEmployee(cmdParts[2]));
                this.team.getLeader().setTeam(this.team);
                company.addTeam(this.team);

                addUndoCommand(this);
                clearRedoList();

                System.out.println("Done.");
            }
        } catch (ExEmployeeNotFound e) {
            System.out.printf("Employee %s is not found!\n", cmdParts[2]);
        } catch (ExTeamNameExists e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeBelongsToTeam e) {
            System.out.printf("Employee %s already belongs to team %s!\n",
                    cmdParts[2], company.searchEmployee(cmdParts[2]).getTeam().getTeamName());
        }
    }

    @Override
    public void undoCmd() {
        company.removeTeam(this.team);
        this.team.getLeader().removeTeam();
        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        company.addTeam(this.team);
        try {
            this.team.getLeader().setTeam(this.team);
        } catch (ExEmployeeBelongsToTeam ignored) {
        }
        addUndoCommand(this);
    }
}
