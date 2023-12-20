package UnitTests;

import Creator.CreateLecturer;
import PersonObject.Lecturer;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateLecturerTest {

    @Test
    void testCreateLecturers() {
        String fileName = "src\\JSON_Files\\lecturers.json";
        CreateLecturer createLecturer = null;
        createLecturer = new CreateLecturer(fileName);
        createLecturer.createLecturers();
        assert createLecturer != null;
        ArrayList<Lecturer> lecturers = createLecturer.getLecturers();
        assertNotNull(lecturers);
        assertEquals(54, lecturers.size());
        Lecturer firstLecturer = lecturers.get(0);
        assertEquals("Ayse", firstLecturer.getFirstName());
        assertEquals("Yilmaz", firstLecturer.getLastName());
        assertEquals("1505001", firstLecturer.getLecturerId().getId());
        assertEquals("Ataturk_81", firstLecturer.getPassword().getPassword());
    }
}
