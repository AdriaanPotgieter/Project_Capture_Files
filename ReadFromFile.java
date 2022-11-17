import java.io.*;
import java.util.*;
public class ReadFromFile {
    // attribute.
    ProjectPoisedList instanceList;
    // getting the objects.
    public ReadFromFile(){
        instanceList = ProjectPoisedList.getProjectObjects();
    }
    // Reading from the text file poised_project.txt.
    public void readingFile(){
        try {
            File readFile = new File("poised_projects.txt");
            Scanner interpretFile = new Scanner(readFile);

            while (interpretFile.hasNext()){
                String projectPoised = interpretFile.nextLine();
                String poisedArray [] = projectPoised.split(", ");
                PersonObjects architect = new PersonObjects(poisedArray[8], poisedArray[9], poisedArray[10], poisedArray[11], poisedArray[12]);
                PersonObjects contractor =  new PersonObjects(poisedArray[13], poisedArray[14], poisedArray[15], poisedArray[16], poisedArray[17]);
                PersonObjects customer = new PersonObjects(poisedArray[18], poisedArray[19], poisedArray[20], poisedArray[21], poisedArray[22]);
                ProjectPoised poised = new ProjectPoised(Integer.parseInt(poisedArray[0]), poisedArray[1],
                        poisedArray[2], poisedArray[3], Integer.parseInt(poisedArray[4]),
                        Integer.parseInt(poisedArray[5]), Integer.parseInt(poisedArray[6]), poisedArray[7], architect,contractor,
                        customer, poisedArray[23]);
                instanceList.addProjectPoised(poised);
            }
        }
        catch (FileNotFoundException fe){
            System.out.println("This file doesn't exits.");
        }

    }
}
