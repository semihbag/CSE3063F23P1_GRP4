package UnitTests;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import SourceCodes.*;

import org.junit.Before;

import java.util.ArrayList;


public class AdvisorTest {

    Advisor advisor;
    Student student;
    
    @Before
    public void createInstanceOfLecture() {
        advisor = new Advisor("Mustafa", "Agaoglu", new Id("1501002"), new Password("sql112233"));
        student = new Student("Eren", "Cetin", new Id("150120008"), new Password("Eren_123"), advisor, null, null);
        student.setUnreadNotifications(new ArrayList<>());
        student.setReadNotifications(new ArrayList<>());
        advisor.setSelectStudent(student);

    }

    @Test
    @DisplayName ("If Advisor doesn't write anything and operation is Approved, the default message must be sent to the student")
    public void sendApprovedMessageToStudent() {
        String message = "";
        String messageType = "A";
        advisor.sendNotification(message, messageType);

        assertEquals(1, student.getUnreadNotifications().size());
        assertEquals("The request is approved!", student.getUnreadNotifications().get(0));

    }

    @Test
    @DisplayName ("If Advisor doesn't write anything and operation is DisApproved, the default message must be sent to the student")
    public void sendDisapprovedMessageToStudent() {
        String message = "";
        String messageType = "D";
        advisor.sendNotification(message, messageType);

        assertEquals(1, student.getUnreadNotifications().size());
        assertEquals("The request is disapproved!", student.getUnreadNotifications().get(0));

    }

    @Test
    @DisplayName ("If Advisor write a message, that message is sent to the student")
    public void sendMessageToTheStudent() {
        // Assume The message is "You have a busy program but I still aprroved"
        String message = "You have a busy program but I still aprroved";
        advisor.sendNotification(message, "A");
        assertEquals(1, student.getUnreadNotifications().size());
        assertEquals("You have a busy program but I still aprroved", student.getUnreadNotifications().get(0));

    }

}

