import java.io.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class Poised {
    // global variable/Scanner.
    static Scanner inputs = new Scanner(System.in);

    public static void main(String[] args) {
        // createFirstProject is an instance set to null
        // So that it can be called in any use case.
        ProjectPoised createPoisedProject = null;

        // The while loop is to display the menu.
        // The conditions are there to edit what is in
        // createFirstProject() which is an object.
        try {
            // ReadFromFile is a function that reads from a file.
            // WriteToFile is a function that writes to the text file.
            // controlLoop controls the while statement and breaks where the user wants
            // them to break in the option menu.
            ReadFromFile retrieveInfo = new ReadFromFile();
            retrieveInfo.readingFile();
            WriteToFile writeInfo = new WriteToFile();

            ProjectPoisedList projectList = ProjectPoisedList.getProjectObjects();
            boolean controlLoop = true;

            while (controlLoop) {
                System.out.println("""
                        Choose a number from the menu:\n
                        \n1 - Create project.
                        \n2 - Select and Update project or finalise the project.
                        \n3 - View all projects or incomplete projects or past due date.
                        \n4 - Exit.
                        """);

                String projectMenu = inputs.nextLine();

                // The switch statement calls the relevant functions and getters and setters.
                switch (projectMenu) {

                    // adds object to the list.
                    case "1":
                        createPoisedProject = createNewProject();
                        projectList.addProjectPoised(createPoisedProject);
                        System.out.println("Project has been added to poised_projects.txt");
                        break;

                    // Allows user to select/update/finalise the projects.
                    case "2":
                        System.out.println("Select a number to update a project");
                        projectList.choseProjectNumber();
                        int userChoice = Integer.parseInt(inputs.nextLine());
                        ProjectPoised updateProject = projectList.getProjectPoised(userChoice);
                        System.out.println("\n" + updateProject);

                        System.out.println("Choose a option to update project details:\n" +
                                "\n1 - Change the deadline project." +
                                "\n2 - Change contractor telephone details." +
                                "\n3 - Change total amount details." +
                                "\n4 - Finalise project.");

                        String updateDetails = inputs.nextLine();

                        if (updateDetails.equals("1")) {
                            System.out.println("\nChange the deadline of the project. Type like so;" +
                                    "e.g. 12 November 2022:");
                            String changeDeadline = inputs.nextLine();
                            updateProject.setDeadlineForProject(changeDeadline);
                            System.out.println("\n" + updateProject);

                        }
                        else if (updateDetails.equals("2")) {
                            PersonObjects retrieveContractor = updateProject.getContractor();
                            System.out.println("\nChange the contact details of the contractor: ");
                            String changeContacts = inputs.nextLine();
                            retrieveContractor.setTelephoneNumber(changeContacts);
                            System.out.println(retrieveContractor);

                        }
                        else if (updateDetails.equals("3")) {
                            System.out.println("Change total amount to date:");
                            int changeTotalDate = inputs.nextInt();
                            updateProject.setTotalAmountDate(changeTotalDate);
                            System.out.println(updateProject);

                            // if the user chose "f" then will write to text file called "Completed_project.txt
                        }
                        else if (updateDetails.equals("4")) {

                            PersonObjects customerContactDetails = updateProject.getCustomer();
                            System.out.println(customerContactDetails);
                            invoice(updateProject.getTotalFeeProject(), updateProject.getTotalAmountDate());
                            System.out.println("Choose one of the option:\n" +
                                    "F - Finalise Project." +
                                    "\nE - Exit.");
                            String projectCompletion = inputs.nextLine();

                            if (projectCompletion.equalsIgnoreCase("F")) {

                                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                                LocalDateTime currentTime = LocalDateTime.now();
                                updateProject.setDeadlineForProject(formatTime.format(currentTime));
                                updateProject.setStatus("completed");

                                System.out.println("This is the completed project:\n" + updateProject);
                                System.out.println("\nWritten to file called Completed_project.txt");
                                writeCompleteProjects(updateProject);
                            }
                        }
                        break;

                    // The 3rd option of the menu that the user chose.
                    //  The user is then given another of either to:
                    //  view all projects or see incomplete projects or see past the due date.
                    case "3":

                        try {
                            System.out.println("Choose options below:\n" +

                                    "1 - View all projects." +
                                    "\n2 - View incomplete projects." +
                                    "\n3 - View projects passed the due date.");

                            String userOptions = inputs.nextLine();
                            projectList = ProjectPoisedList.getProjectObjects();

                            // Calls relative functions based on user choice
                            if (userOptions.equals("1")) {
                                projectList.viewAllProjects();
                            }
                            else if (userOptions.equals("2")) {
                                projectList.incomplete();
                            }
                            else if (userOptions.equals("3")) {
                                projectList.overdueDateProjects();
                            }
                            break;
                        }
                        catch (InputMismatchException inputMismatchException) {
                            System.out.println("Enter the number from the menu.");
                        }
                        catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    // writes all information to text file "poised_project.txt
                    case "4":
                        writeInfo.writeProjectPoisedFile();
                        System.out.println("Thanks for using our program.");
                        controlLoop = false;
                        break;
                    default:
                        System.out.println("You probably entered something incorrectly. Try again.");
                }
                writeInfo.writeProjectPoisedFile();
            }
            // throws the exception if the user didn't the correct value from the menu
        }
        catch (InputMismatchException in) {
            System.out.println("Your input is invalid. Enter only the number from the menu.");
        }
    }

    // The createDetails() function / method is to
    // create the PersonObjects object by asking the
    //user to populate by asking for their input to
    // create an object and returns the users input
    // in the new PersonObjects.
    public static PersonObjects createDetails(String personName) {

        System.out.println("Enter name of " + personName + ": ");
        String name = inputs.nextLine();

        System.out.println("\nEnter surname of " + personName + ": ");
        String surname = inputs.nextLine();

        System.out.println("\nEnter telephone number of " + personName + ": ");
        String telephoneNumber = inputs.nextLine();

        System.out.println("\nEnter email address of " + personName + ":\n ");
        String emailAddress = inputs.nextLine();

        System.out.println("\nEnter physical address of " + personName + ": \n");
        String physicalAddress = inputs.nextLine();

        return new PersonObjects(name, surname,
                telephoneNumber,
                emailAddress,
                physicalAddress);
    }

    // The function/ method is to an object from the class
    // ProjectPoised by asking the user to populate it with
    // user input and returns the new ProjectPoised.
    public static ProjectPoised createNewProject() {
        // The variables with null and 0 values is to bring them into scope.
        String phyAddressProject = null;
        int erfNumber = 0;
        int totalFeeProject = 0;
        int projectNumber = 0;
        String projectName = null;
        String typeOfBuilding = null;
        int totalAmountDate = 0;
        String deadlineProject = null;
        PersonObjects architect = null;
        PersonObjects contractor = null;
        PersonObjects customer = null;
        String statusIncomplete = null;

        try {
            System.out.println("Enter the project number: ");
            projectNumber = Integer.parseInt(inputs.nextLine());

            System.out.println("Enter the Project Name: ");
            projectName = inputs.nextLine();

            System.out.println("Enter The type of building: ");
            typeOfBuilding = inputs.nextLine();

            System.out.println("Enter the physical address of the project: ");
            phyAddressProject = inputs.nextLine();

            System.out.println("Enter the ERF number: ");
            erfNumber = Integer.parseInt(inputs.nextLine());

            System.out.println("Enter the total fee of the project: ");
            totalFeeProject = Integer.parseInt(inputs.nextLine());

            System.out.println("Enter the total amount to date: ");
            totalAmountDate = Integer.parseInt(inputs.nextLine());

            System.out.println("Enter the deadline of the project. E.g(17 October 2022): ");
            deadlineProject = inputs.nextLine();

            System.out.println("******** Architect Details ********");
            architect = createDetails("Architect");

            System.out.println("******** Contractor Details ********");
            contractor = createDetails("Contractor");

            System.out.println("******** Customer Details ********");
            customer = createDetails("Customer");

            statusIncomplete = "Incomplete";
            // if the project name isn't given then the customer surname.
            // is given
            if (projectName.isEmpty()) {
                projectName = typeOfBuilding + " " + customer.getSurname();
            }
        }
        catch (InputMismatchException inputMismatchException) {
            System.out.println("Entered the wrong input details about the project");
        }
        catch (NumberFormatException numberFormatException) {
            System.out.println("Enter the correct number required for the input.");
        }
        return new ProjectPoised(projectNumber, projectName, typeOfBuilding,
                phyAddressProject, erfNumber, totalFeeProject,
                totalAmountDate, deadlineProject, architect,
                contractor, customer, statusIncomplete);
    }

    // Function performs calculation for the invoice by Total Fee minus Total amount.
    public static int invoice(int totalAmount, int totalFee) {
        int finalAmount = totalFee - totalAmount;
        if (totalFee < totalAmount || totalFee == totalAmount || totalAmount > totalFee) {
            System.out.println("Thanks for paying in full");
        }
        else {
            System.out.println("Here's the invoice:\n");
            System.out.println("This is the outstanding amount:\tR" + finalAmount);
        }
        return finalAmount;
    }

    // The function writes completed projects to the text file.
    public static void writeCompleteProjects(ProjectPoised completedProjects) {
        try {
            FileWriter writeProjects = new FileWriter("completed_project.txt", true);
            BufferedWriter bufferProjects = new BufferedWriter(writeProjects);
            PrintWriter complete = new PrintWriter(bufferProjects);
            complete.println(completedProjects.formatToFile());
            complete.close();

        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("This file doesn't exists");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
