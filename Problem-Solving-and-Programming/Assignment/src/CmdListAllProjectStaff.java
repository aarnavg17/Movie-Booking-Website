public class CmdListAllProjectStaff implements Command {

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        try {
            Project project = company.searchProject(cmdParts[1]);
            if (project == null)
                throw new ExProjectNotFound();
            else
                project.listProjectStaff();
        } catch (ExProjectNotFound e) {
            System.out.printf("Project %s is not found!\n", cmdParts[1]);
        }
    }
}
