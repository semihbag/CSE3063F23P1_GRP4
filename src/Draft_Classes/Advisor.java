package Draft_Classes;

import java.util.ArrayList;

public class Advisor extends Lecturer {
    private ArrayList <Student> students = new ArrayList<>();
    private ArrayList<Student> awaitingStudents = new ArrayList<>();
    private Password password;

    Advisor(String name, String surname, Id advisorId, Password password){
        super(advisorId,name, surname);
        this.password= password;
    }





    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getAwaitingStudents() {
        return awaitingStudents;
    }

    public void setAwaitingStudents(ArrayList<Student> awaitingStudents) {
        this.awaitingStudents = awaitingStudents;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
