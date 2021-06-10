public class CmdListAllTeamProjects implements Command {

    @Override
    public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
        company.listTeamProjects();
    }
}
