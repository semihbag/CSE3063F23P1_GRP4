
public class Lecturer extends Person{

    private Id lecturerID;

    public Lecturer(String FirstName, String LastName, Id lecturerID) {
        super(FirstName, LastName);
        this.lecturerID = lecturerID;
    }

    public String getLecturerID() {
        return lecturerID.getId();
    }

    public void setLecturerID(String id) {
        lecturerID.setId(id);
    }
 
}