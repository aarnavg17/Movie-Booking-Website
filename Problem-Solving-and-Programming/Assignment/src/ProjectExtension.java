public class ProjectExtension extends Project {
    Project parentProject;

    public ProjectExtension(String code, String title) {
        super(code, title);
        String parentCode = code.split("-")[0];
        Company company = Company.getInstance();
        this.parentProject = company.searchProject(parentCode);
    }

    public void giveAssignmentSuggestions() throws ExProjectAlreadyAssigned {
        if (!this.getStatus().equals("Pending"))
            throw new ExProjectAlreadyAssigned();
        else {
            Company company = Company.getInstance();
            boolean exists = false;
            for (Team t: company.getTeams()) {
                exists = t.lookForSimilarProjects(this.parentProject, this);
                if (exists)
                    break;
            }
            if (!exists)
                System.out.println("No team or staff has worked on related projects.");
            else {
                System.out.println("These teams have worked on related projects:");
                for (Team t: company.getTeams())
                    if (t.listProjects(this.parentProject, this) != null)
                        System.out.println(t.listProjects(this.parentProject, this));
                System.out.println("These staff have worked on related projects:");
                for (Employee e: company.getEmployees())
                    if (e.listProjects(this.parentProject, this) != null)
                        System.out.println(e.listProjects(this.parentProject, this));
            }
        }
    }

    public Project getParentProject() {
        return this.parentProject;
    }
}
