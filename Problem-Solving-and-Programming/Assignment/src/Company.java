import java.util.ArrayList;
import java.util.Collections;

public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    private static Company instance = new Company();

    private Company() {
        this.allEmployees = new ArrayList<>();
        this.allTeams = new ArrayList<>();
        this.allProjects = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }

    public void listEmployees() {
        for (Employee e: allEmployees)
            System.out.println(e);
    }

    public void addEmployee(Employee employee) {
        allEmployees.add(employee);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee employee) {
        allEmployees.remove(employee);
    }

    public Employee searchEmployee(String eName) {
        for (Employee e: allEmployees)
            if (e.getName().equals(eName))
                return e;
        return null;
    }

    public Team searchTeam(String tName) {
        for (Team t: allTeams)
            if (t.getTeamName().equals(tName))
                return t;
        return null;
    }

    public void addTeam(Team team) {
        allTeams.add(team);
        Collections.sort(allTeams);
    }

    public void removeTeam(Team team) {
        allTeams.remove(team);
    }

    public void listTeams() {
        System.out.printf("%-15s%-10s%-13s%s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t: allTeams)
            System.out.print(t);
    }

    public int getNumberOfProjects() {
        return this.allProjects.size();
    }

    public void addProject(Project project) {
        this.allProjects.add(project);
        Collections.sort(allProjects);
    }

    public void removeProject(Project project) {
        this.allProjects.remove(project);
    }

    public void listProjects() {
        System.out.printf("%-9s%-23s%-13s%-13s%-14s%-13s%-13s\n", "Code", "Project Title", "Created on", "Status",
                "Assigned to", "Assigned on", "Completed on");
        for (Project p: allProjects)
            System.out.print(p);
    }

    public Project searchProject(String code) {
        for (Project p: allProjects)
            if (p.getCode().equals(code))
                return p;
            else if (p.searchExtension(code) != null)
                return p.searchExtension(code);
        return null;
    }

    public void listTeamProjects() {
        StringBuilder s = new StringBuilder();
        for (Team t: allTeams)
            s.append(t.getTeamName()).append(": ").append(t.listProjects()).append("\n");
        System.out.print(s);
    }

    public void listStaffParticipation() {
        for (Employee e: allEmployees) {
            System.out.println(e.getName() + ": " + e.listProjects());
        }
    }

    public ArrayList<Team> getTeams() {
        return this.allTeams;
    }

    public ArrayList<Employee> getEmployees() {
        return this.allEmployees;
    }
}
