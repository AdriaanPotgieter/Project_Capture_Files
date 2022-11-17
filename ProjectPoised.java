
public class ProjectPoised {
    //Attributes.
    int projectNumber;
    String projectName;
    String typeOfBuilding;
    String physicalAddressProject;
    int erfNumber;
    int totalFeeProject;
    int totalAmountDate;
    String deadlineForProject;

    // OBJECTS.
    PersonObjects architect;
    PersonObjects contractor;
    PersonObjects customer;
    String status;

    //Constructor method for the class name ProjectPoised.
    public ProjectPoised(int projectNumber, String projectName,
                         String typeOfBuilding, String physicalAddressProject,
                         int erfNumber, int totalFeeProject, int totalAmountDate,
                         String deadlineForProject, PersonObjects architect,
                         PersonObjects contractor, PersonObjects customer, String status) {

        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.typeOfBuilding = typeOfBuilding;
        this.physicalAddressProject = physicalAddressProject;
        this.erfNumber = erfNumber;
        this.totalFeeProject = totalFeeProject;
        this.totalAmountDate = totalAmountDate;
        this.deadlineForProject = deadlineForProject;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;
        this.status = status;
    }
    public String getProjectName() {
        return projectName;
    }

    public int getTotalFeeProject() {
        return totalFeeProject;
    }

    // The setter setDeadlineForProject() is to allow the user to change the due date.
    public void setDeadlineForProject(String newDueDate) {
        this.deadlineForProject = newDueDate;
    }

    // The setter setTotalAmountDate() is to allow the user to change the total amount date.
    public void setTotalAmountDate(int newAmountDate) {
        this.totalAmountDate = newAmountDate;
    }

    // Retrieves the contractors details.
    public PersonObjects getContractor() {
        return contractor;
    }

    public PersonObjects getCustomer() {
        return customer;
    }
    // gets the totalAmountDate.
    public int getTotalAmountDate() {
        return totalAmountDate;
    }

    public String getDeadlineForProject() {
        return deadlineForProject;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // The toString() method is to format attributes in ProjectPoised
    // in a String format.
    public String toString() {
        String projectDetails = "Project Number: " + projectNumber;
        projectDetails += "\nProject Name: " + projectName;
        projectDetails += "\nType Of Building: " + typeOfBuilding;
        projectDetails += "\nPhysical Address: " + physicalAddressProject;
        projectDetails += "\nERF Number: " + erfNumber;
        projectDetails += "\nThe Total Fee of The Project : " + totalFeeProject;
        projectDetails += "\nTotal Amount Date: " + totalAmountDate;
        projectDetails += "\nDeadline Project: " + deadlineForProject;
        projectDetails += "\nArchitect Details:\n" + architect;
        projectDetails += "\nContractor Details: " + contractor;
        projectDetails += "\nCustomer Details: " + customer;
//        projectDetails += "\nStatus: " + status;


        if(status == null){
            projectDetails += "";
        }
        else {
            projectDetails += "\nStatus: " + status;
        }

        return projectDetails;
    }


    // Formatting it to write to the file.
    public String formatToFile(){
        return projectNumber + ", "+ projectName + ", " + typeOfBuilding + ", "+ physicalAddressProject+ ", "+ erfNumber
                + ", "+ totalFeeProject +", "+ totalAmountDate +", "+ deadlineForProject +", "+ architect.formatToFile() +", "+
                contractor.formatToFile() +", "+ customer.formatToFile()  +", "+ status;
    }


}
