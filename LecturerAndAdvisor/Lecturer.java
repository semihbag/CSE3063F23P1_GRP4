
public class Lecturer extends Person{

    private LecturerID lecturerID;

    public Lecturer(String FirstName, String LastName, LecturerID lecturerID) {
        super(FirstName, LastName);
        this.lecturerID = lecturerID;
    }

    public String getLecturerID() {
        return lecturerID.getID();
    }
 
}