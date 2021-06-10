public interface Command {
    void execute(String[] cmdParts) throws ExInsufficientArguments;
}
