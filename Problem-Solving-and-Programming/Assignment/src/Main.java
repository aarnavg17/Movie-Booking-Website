import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please input the file pathname: ");
        String filepathname = in.nextLine();

        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(filepathname));
            if (!inFile.hasNext())
                throw new ExEmptyFile();

            String firstDay = inFile.nextLine();
            System.out.println("\n> " + firstDay);
            String[] cmd = firstDay.split("\\|");
            CurrentDay currDay = CurrentDay.createInstance(cmd[1]);

            while (inFile.hasNext()) {
                String cmdLine = inFile.nextLine();

                if (cmdLine.equals(""))
                    continue;

                System.out.println("\n> " + cmdLine);

                String[] cmdParts = cmdLine.split("\\|");

                int len = cmdParts.length;
                cmdParts[len-1] = cmdParts[len-1].trim();

                try {
                    switch (cmdParts[0]) {
                        case "assignProject":
                            (new CmdAssignProject()).execute(cmdParts);
                            break;
                        case "createExtensionProject":
                            (new CmdCreateProjectExtension()).execute(cmdParts);
                            break;
                        case "createProject":
                            (new CmdCreateProject()).execute(cmdParts);
                            break;
                        case "giveAssignmentSuggestions":
                            (new CmdGiveAssignmentSuggestions()).execute(cmdParts);
                            break;
                        case "hire":
                            (new CmdHire()).execute(cmdParts);
                            break;
                        case "joinTeam":
                            (new CmdJoinTeam()).execute(cmdParts);
                            break;
                        case "listEmployees":
                            (new CmdListAllEmployees()).execute(cmdParts);
                            break;
                        case "listProjects":
                            (new CmdListAllProjects()).execute(cmdParts);
                            break;
                        case "listProjectStaff":
                            (new CmdListAllProjectStaff()).execute(cmdParts);
                            break;
                        case "listStaffParticipations":
                            (new CmdListAllStaffParticipation()).execute(cmdParts);
                            break;
                        case "listTeamProjects":
                            (new CmdListAllTeamProjects()).execute(cmdParts);
                            break;
                        case "listTeams":
                            (new CmdListAllTeams()).execute(cmdParts);
                            break;
                        case "markCompletion":
                            (new CmdMarkComplete()).execute(cmdParts);
                            break;
                        case "redo":
                            RecordedCommand.redoOneCommand();
                            break;
                        case "setupTeam":
                            (new CmdSetUpTeam()).execute(cmdParts);
                            break;
                        case "startNewDay":
                            (new CmdStartNewDay()).execute(cmdParts);
                            break;
                        case "undo":
                            RecordedCommand.undoOneCommand();
                            break;
                        default:
                            throw new ExWrongCommand();
                    }
                } catch (ExEmptyUndoStack | ExEmptyRedoStack | ExWrongCommand e) {
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException | ExInsufficientArguments e) {
                    System.out.println("Insufficient command arguments.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (ArrayIndexOutOfBoundsException | ExInsufficientArguments e) {
            System.out.println("Insufficient command arguments.");
        } catch (ExEmptyFile | ExInstanceOfSingleton e) {
            System.out.println(e.getMessage());
        } finally {
            if (inFile != null)
                inFile.close();
            in.close();
        }
    }
}
