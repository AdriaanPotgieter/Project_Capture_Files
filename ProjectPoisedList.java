import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectPoisedList {
    // An array list contains a list of objects.
    ArrayList <ProjectPoised> poisedArrayList;
    private static ProjectPoisedList projectObjects = null;
    // Puts objects in an ArrayList.
    private ProjectPoisedList(){
        poisedArrayList = new ArrayList<ProjectPoised>();
    }
    // The method adds projects.
    public void addProjectPoised(ProjectPoised projects){
        poisedArrayList.add(projects);
    }
    // gets the project index.
    public ProjectPoised getProjectPoised(int index){
        return poisedArrayList.get(index);
    }
    // returns a single object.
    public static ProjectPoisedList getProjectObjects(){
        if(projectObjects == null){
            projectObjects = new ProjectPoisedList();
        }
        return projectObjects;
    }
    // Allows user to select the projects they want.
    public void choseProjectNumber(){
        int projectNumbers = 0;

        for(ProjectPoised projects: poisedArrayList){
            System.out.println(projectNumbers + "- "+ projects.getProjectName());
            projectNumbers ++;
        }
    }
    // displays all projects.
    public void viewAllProjects(){
        for (ProjectPoised poised: poisedArrayList){
            System.out.println(poised + " \n\n===================================================================\n\n");
        }
    }
    // Retrieves projects that are incomplete.
    public void incomplete() {
        for (ProjectPoised poised : poisedArrayList){
            if (poised.getStatus().equalsIgnoreCase("incomplete")){
                System.out.println( poised + "\n\n===============================================================\n\n");
            }else {
                System.out.println("All projects are completed");
            }
        }
    }
    // retrieves projects that overdue.
    public void overdueDateProjects() throws ParseException {
        for (ProjectPoised poised : poisedArrayList){
            Date deadlineProject = new SimpleDateFormat("dd MMMM yyyy").parse(poised.getDeadlineForProject());
            Date dueDate = new Date();
            if (deadlineProject.compareTo(dueDate) > 0){
                System.out.println(poised + "\n\n===============================================================\n\n");
            }else{
                System.out.println("All projects are up to date.");
            }
        }
    }
    // Formatting the data to be written to the file.
    public String formatToFile(){
        String details = "";
        for (ProjectPoised info: poisedArrayList){
            details += info.formatToFile() + "\n";
        }
        details = details.substring(0, details.length() - 1);
        return details;
    }

}
