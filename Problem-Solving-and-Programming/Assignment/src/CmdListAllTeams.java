public class CmdListAllTeams implements Command {

    @Override
    public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
        company.listTeams();
    }
}
