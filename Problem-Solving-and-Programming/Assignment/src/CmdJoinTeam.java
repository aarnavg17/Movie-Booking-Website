public class CmdJoinTeam extends RecordedCommand {
    Team team;
    Employee employee;
    Company company;

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        company = Company.getInstance();
        try {
            this.team = company.searchTeam(cmdParts[1]);
            this.employee = company.searchEmployee(cmdParts[2]);
            if (this.team == null)
                throw new ExTeamNotFound();
            else if (this.employee == null)
                throw new ExEmployeeNotFound();
            else {
                this.employee.setTeam(this.team);
                this.team.addMember(this.employee);

                addUndoCommand(this);
                clearRedoList();

                System.out.println("Done.");
            }
        } catch (ExEmployeeNotFound e) {
            System.out.printf("Employee %s is not found!\n", cmdParts[2]);
        } catch (ExTeamNotFound e) {
            System.out.printf("Team %s is not found!", cmdParts[1]);
        } catch (ExEmployeeBelongsToTeam e) {
            System.out.printf("Employee %s already belongs to team %s!\n",
                    cmdParts[2], this.employee.getTeam().getTeamName());
        }
    }

    @Override
    public void undoCmd() {
        this.team.removeMember(this.employee);
        this.employee.removeTeam();

        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        this.team.addMember(this.employee);
        try {
            this.employee.setTeam(this.team);
        } catch (ExEmployeeBelongsToTeam ignored) {
        }

        addUndoCommand(this);
    }
}
