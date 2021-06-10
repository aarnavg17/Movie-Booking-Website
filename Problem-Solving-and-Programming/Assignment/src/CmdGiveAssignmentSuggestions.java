public class CmdGiveAssignmentSuggestions implements Command {
    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        ProjectExtension project = (ProjectExtension) company.searchProject(cmdParts[1]);
        try {
            if (project == null)
                throw new ExProjectNotFound();
            else
                project.giveAssignmentSuggestions();
        } catch (ExProjectNotFound | ExProjectAlreadyAssigned e) {
            System.out.println(e.getMessage());
        }
    }
}
