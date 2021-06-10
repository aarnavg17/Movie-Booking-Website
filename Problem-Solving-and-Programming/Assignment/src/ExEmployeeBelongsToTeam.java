public class ExEmployeeBelongsToTeam extends Exception {
    public ExEmployeeBelongsToTeam() {
        super("Employee already belongs to a team!");
    }

    public ExEmployeeBelongsToTeam (String message) {
        super(message);
    }
}
