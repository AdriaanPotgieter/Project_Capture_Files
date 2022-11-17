import java.io.*;
import java.util.Formatter;

public class WriteToFile {
    // attribute.
    private ProjectPoisedList objectPoised;
    // How it should be formatted when writing to the file.
    public WriteToFile(){
        objectPoised = ProjectPoisedList.getProjectObjects();
    }
    // Writes to the text file called poised_project.txt.
    public void writeProjectPoisedFile(){
        try
        {
            Formatter writeFile = new Formatter("poised_projects.txt");
            writeFile.format(objectPoised.formatToFile());
            writeFile.close();
        }
        catch (FileNotFoundException fe){
            System.out.println("File not found");
        }
    }
}
