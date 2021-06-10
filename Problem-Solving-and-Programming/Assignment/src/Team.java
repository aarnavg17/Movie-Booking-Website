import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
    private final String name;
    private final Employee leader;
    private final Day setupDate;
    private final ArrayList<Employee> allMembers;
    private final ArrayList<Project> allProjects;

    public Team(String name, Employee leader) {
        this.name = name;
        this.leader = leader;
        this.setupDate = CurrentDay.getInstance().clone();
        this.allMembers = new ArrayList<>();
        this.allProjects = new ArrayList<>();
    }

    @Override
    public int compareTo(Team o) {
        return this.name.compareTo(o.name);
    }

    public String getTeamName() {
        return this.name;
    }

    public String toString() {
        return String.format("%-15s%-10s%-13s%s\n", this.name, this.leader.getName(), this.setupDate, this.memberList());
    }

    private String memberList() {
        if (allMembers.isEmpty())
            return "(no member)";
        else {
            StringBuilder list = new StringBuilder();
            int i = 0;
            for (Employee e: allMembers) {
                i++;
                if (i != 1)
                    list.append(", ");
                list.append(e.getName());
            }
            return list.toString();
        }
    }

    public void addMember(Employee member) {
        this.allMembers.add(member);
        Collections.sort(allMembers);
    }

    public void removeMember(Employee member) {
        this.allMembers.remove(member);
    }

    public Employee getLeader() {
        return this.leader;
    }

    public void addProject(Project project) {
        this.allProjects.add(project);
        this.leader.addProject(project);
        for (Employee e: allMembers)
            e.addProject(project);
        Collections.sort(allProjects);
    }

    public void deleteProject(Project project) {
        this.allProjects.remove(project);
        this.leader.removeProject(project);
        for (Employee e: allMembers)
            e.removeProject(project);
    }

    public String listProjects() {
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (Project p: allProjects) {
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
            for (Project p : allProjects)
                if (p == project || (p instanceof ProjectExtension && ((ProjectExtension) p).getParentProject() == project
                        && p != realProject)) {
                    i++;
                    if (i != 1)
                        s.append(", ");
                    s.append(p.getCode()).append("(").append(p.getStatus()).append(")");
                }
            return s.toString();
        }
        return null;
    }

    public String listLeaderAndMembers() {
        StringBuilder s = new StringBuilder();
        s.append(this.leader.getName()).append(" (The Leader)");
        for (Employee e: allMembers)
            s.append(", ").append(e.getName());
        return s.toString();
    }

    public boolean lookForSimilarProjects(Project project, ProjectExtension realProject) {
        for (Project p: this.allProjects)
            if (p == project || (p instanceof ProjectExtension && ((ProjectExtension) p).getParentProject() == project
                    && p != realProject))
                return true;
        return false;
    }
}
