public class CmdHire extends RecordedCommand {
    private Company company;
    private Employee employee;

    @Override
    public void execute(String[] cmdParts) throws ArrayIndexOutOfBoundsException {
        company = Company.getInstance();
        try {
            if (company.searchEmployee(cmdParts[1]) != null)
                throw new ExEmployeeNameExists();
            else {
                this.employee = new Employee(cmdParts[1]);
                company.addEmployee(this.employee);

                addUndoCommand(this);
                clearRedoList();

                System.out.println("Done.");
            }
        } catch (ExEmployeeNameExists e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoCmd() {
        company.removeEmployee(this.employee);

        addRedoCommand(this);
    }

    @Override
    public void redoCmd() {
        company.addEmployee(this.employee);

        addUndoCommand(this);
    }
}