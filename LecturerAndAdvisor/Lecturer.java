
public class Lecturer extends Person{

    private int lecturerID;

    public Lecturer(String FirstName, String LastName, int lecturerID) {
        super(FirstName, LastName);
        this.lecturerID = lecturerID;
    }

    public int getLecturerID() {
        return lecturerID;
    }
 
}