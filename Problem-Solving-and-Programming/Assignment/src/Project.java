import java.util.ArrayList;
import java.util.Collections;

public class Project implements Comparable<Project> {
    private final String code;
    private final String title;
    private final Day createdOn;
    private String status;
    private ProjectAssignment assignment;
    private Day completedOn;
    private final ArrayList<ProjectExtension> allExtensions;

    public Project(String code, String title) {
        this.code = code;
        this.title = title;
        this.createdOn = CurrentDay.getInstance().clone();
        this.status = "Pending";
        this.assignment = null;
        this.completedOn = null;
        this.allExtensions = new ArrayList<>();
    }

    @Override
    public int compareTo(Project o) {
        return this.code.compareTo(o.code);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        if (this.status.equals("Pending"))
            s.append(String.format("%-9s%-23s%-13s%-13s%-14s%-13s%-13s\n", this.code, this.title, this.createdOn,
                this.status, "--", "--", "--"));
        else if (this.status.equals("In-progress"))
            s.append(String.format("%-9s%-23s%-13s%-13s%-14s%-13s%-13s\n", this.code, this.title, this.createdOn,
                    this.status, this.assignment.getTeam().getTeamName(), this.assignment.getDate(), "--"));
        else
            s.append(String.format("%-9s%-23s%-13s%-13s%-14s%-13s%-13s\n", this.code, this.title, this.createdOn,
                    this.status, this.assignment.getTeam().getTeamName(), this.assignment.getDate(), this.completedOn));

        for (ProjectExtension p: this.allExtensions)
            s.append(p);
        return s.toString();
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreationDate() {
        return this.createdOn.toString();
    }

    public String getStatus() {
        return this.status;
    }

    public Team getTeam() {
        return this.assignment.getTeam();
    }

    public void assign(ProjectAssignment assignment) throws ExProjectAlreadyAssigned {
        if (this.status.equals("In-progress"))
            throw new ExProjectAlreadyAssigned();
        else {
            this.assignment = assignment;
            this.status = "In-progress";
        }
    }

    public void deleteAssignment() {
        this.assignment.deleteAssignment();
        this.assignment = null;
        this.status = "Pending";
    }

    public void markCompleted() throws ExProjectAlreadyCompleted, ExProjectNotAssigned {
        if (this.status.equals("Completed"))
            throw new ExProjectAlreadyCompleted();
        else if (this.status.equals("Pending"))
            throw new ExProjectNotAssigned();
        else {
            this.status = "Completed";
            this.completedOn = CurrentDay.getInstance().clone();
        }
    }

    public void markIncomplete() {
        this.status = "In-progress";
        this.completedOn = null;
    }

    public Day getAssignmentDate() {
        return this.assignment.getDate();
    }

    public int getNumberofExtensions() {
        return this.allExtensions.size();
    }

    public void addExtension(ProjectExtension projectExtension) {
        this.allExtensions.add(projectExtension);
        Collections.sort(allExtensions);
    }

    public void removeExtension(ProjectExtension projectExtension) {
        this.allExtensions.remove(projectExtension);
    }

    public Project searchExtension(String code) {
        for (ProjectExtension p: this.allExtensions)
            if (p.getCode().equals(code))
                return p;
        return null;
    }

    public void listProjectStaff() {
        try {
            if (this.status.equals("Pending"))
                throw new ExProjectNotAssigned();
            else {
                Team t = this.assignment.getTeam();
                System.out.println("Project team: " + t.getTeamName());
                System.out.println("Project team members: " + t.listLeaderAndMembers());
                System.out.println("External support: " + this.assignment.listExternalSupport());
            }
        } catch (ExProjectNotAssigned e) {
            System.out.printf("Assignment of project %s has not been started!\n", this.code);
        }
    }
}
