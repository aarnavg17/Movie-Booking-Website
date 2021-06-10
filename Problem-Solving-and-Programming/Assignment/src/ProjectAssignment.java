import java.util.ArrayList;
import java.util.Collections;

public class ProjectAssignment {
    private final Project project;
    private final Team team;
    private final ArrayList<Employee> allExternalSupport = new ArrayList<>();
    private final Day assignedOn;

    public ProjectAssignment(Project project, Team team) throws ExProjectAlreadyAssigned {
        project.assign(this);
        team.addProject(project);
        this.project = project;
        this.team = team;
        this.assignedOn = CurrentDay.getInstance().clone();
    }

    public ProjectAssignment(Project project, Team team, String[] externalSupport)
            throws ExProjectAlreadyAssigned, ExEmployeeNotFound {
        addExternalSupport(externalSupport, project);
        project.assign(this);
        team.addProject(project);
        this.project = project;
        this.team = team;
        this.assignedOn = CurrentDay.getInstance().clone();
    }

    private void addExternalSupport(String[] externalSupport, Project project) throws ExEmployeeNotFound {
        Company company = Company.getInstance();
        ArrayList<Employee> temp = new ArrayList<>();
        for (int i = 3; i < externalSupport.length; i++) {
            Employee e = company.searchEmployee(externalSupport[i]);
            if (e == null)
                throw new ExEmployeeNotFound(externalSupport[i]);
            else
                temp.add(e);
        }
        for (Employee e: temp) {
            this.allExternalSupport.add(e);
            Collections.sort(allExternalSupport);
            e.addProject(project);
        }
    }

    public void assignAgain() throws ExProjectAlreadyAssigned {
        this.project.assign(this);
        this.addProjectForExternalSupport();
        this.team.addProject(this.project);
    }

    private void addProjectForExternalSupport() {
        for (Employee e: allExternalSupport)
            e.addProject(this.project);
    }

    public Team getTeam() {
        return this.team;
    }

    public Day getDate() {
        return this.assignedOn;
    }

    public String listExternalSupport() {
        if (this.allExternalSupport.isEmpty())
            return "(none)";
        else {
            StringBuilder s = new StringBuilder();
            int i = 0;
            for (Employee e: allExternalSupport) {
                i++;
                if (i != 1)
                    s.append(", ");
                s.append(e.getName());
            }
            return s.toString();
        }
    }

    public void deleteAssignment() {
        for (Employee e: allExternalSupport)
            e.removeProject(this.project);
    }
}
