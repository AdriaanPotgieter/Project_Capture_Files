public class PersonObjects {
    //Attributes.
    String name;
    String surname;
    String telephoneNumber;
    String emailAddress;
    String physicalAddress;

    // Constructor method.
    public PersonObjects(String name, String surname,
                         String telephoneNumber, String emailAddress,
                         String physicalAddress){
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    public String getSurname() {
        return surname;
    }

    public void setTelephoneNumber(String newContactDetails) {
        this.telephoneNumber = newContactDetails;
    }

    // The toString() method is to format attributes in PersonObjects
    // in a String format.
    public String toString(){
        String details = "Name: " + this.name;
        details += "\nSurname: " + this.surname;
        details += "\nTelephone Number:" + this.telephoneNumber;
        details += "\nEmail Address: " + this.emailAddress;
        details += "\nPhysical Address: " + this.physicalAddress;

        return details;
    }

    // Formatting it to write to the file.
    public String formatToFile(){
        return name +", "+ surname  +", "+ telephoneNumber +", "+ emailAddress +", "+ physicalAddress;
    }
}
