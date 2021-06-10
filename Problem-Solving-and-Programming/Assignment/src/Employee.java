import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
    private final String name;
    private Team team;
    private final ArrayList<Project> allProjects;

    public Employee(String name) {
        this.name = name;
        this.team = null;
        this.allProjects = new ArrayList<>();
    }

    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }

    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        returnValue.append(this.name);
        if (this.team != null)
            returnValue.append(" (").append(this.team.getTeamName()).append(")");
        return returnValue.toString();
    }

    public String getName() {
        return this.name;
    }

    public Team getTeam () {
        return this.team;
    }

    public void setTeam(Team team) throws ExEmployeeBelongsToTeam {
        if (this.team != null)
            throw new ExEmployeeBelongsToTeam();
        this.team = team;
    }

    public void removeTeam() {
        this.team = null;
    }

    public void addProject(Project project) {
        this.allProjects.add(project);
        Collections.sort(allProjects);
    }

    public void removeProject(Project project) {
        this.allProjects.remove(project);
    }

    public String listProjects() {
        if (this.allProjects.isEmpty())
            return "(no project)";
        int i = 0;
        StringBuilder s = new StringBuilder();
        for (Project p: this.allProjects) {
            i++;
            if (i != 1)
                s.append(", ");
            s.append(p.getCode()).append("(").append(p.getStatus()).append(")");
        }
        return s.toString();
    }

    public String listProjects(Project project, ProjectExtension realProject) {
        if (this.lookForSimilarProjects(project, realProject)) {
            StringBuilder s = new StringBuilder();
            s.append(this.name).append(": ");
            int i = 0;
            for (Project p : allProjects) {
                if (p == project || (p instanceof ProjectExtension && ((ProjectExtension) p).getParentProject() == project
                        && p != realProject)) {
                    i++;
                    if (i != 1)
                        s.append(", ");
                    s.append(p.getCode()).append("(").append(p.getStatus()).append(")");
                }
            }
            return s.toString();
        }
        return null;
    }

    private boolean lookForSimilarProjects(Project project, ProjectExtension realProject) {
        for (Project p: this.allProjects)
            if (p == project || (p instanceof ProjectExtension && ((ProjectExtension) p).getParentProject() == project
                    && p != realProject))
                return true;
        return false;
    }
}
