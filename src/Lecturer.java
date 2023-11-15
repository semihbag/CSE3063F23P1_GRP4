
public class Lecturer extends Person{

    private ID lecturerID;

    public Lecturer(String FirstName, String LastName, ID lecturerID) {
        super(FirstName, LastName);
        this.lecturerID = lecturerID;
    }

    public String getLecturerID() {
        return lecturerID.getID();
    }

    public void setLecturerID(String id) {
        lecturerID.setID(id);
    }
 
}