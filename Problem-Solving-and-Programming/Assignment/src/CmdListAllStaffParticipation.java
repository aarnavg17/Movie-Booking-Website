public class CmdListAllStaffParticipation implements Command {

    @Override
    public void execute(String[] cmdParts) throws ExInsufficientArguments {
        Company company = Company.getInstance();
        company.listStaffParticipation();
    }
}
