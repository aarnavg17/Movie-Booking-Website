public class ExTeamNameExists extends Exception {
    public ExTeamNameExists() {
        super("Team name already exists!");
    }

    public ExTeamNameExists (String message) {
        super(message);
    }
}
