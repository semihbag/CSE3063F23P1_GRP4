package UnitTests;

import Creator.*;
import CourseObject.*;
import PersonObject.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateCourseTest {
    private CreateCourse createCourse;

    void setUp() throws IOException{
        // Create a Lecturer instance for testing
        Lecturer lecturer = new Lecturer("Mahmut","Kertil",new Id("1303001"),new Password("12345")); // Replace with actual lecturer ID

        // Create temporary file content for testing
        String fileContent = "{\"courses\": [\n" +
                "    {\n" +
                "        \"hour\": [[\"13.00\"]],\n" +
                "        \"quota\": 15,\n" +
                "        \"name\": \"Calculus I\",\n" +
                "        \"studentList\": [],\n" +
                "        \"prerequisite\": [],\n" +
                "        \"lecturer\": \"1303001\",\n" +
                "        \"term\": 1,\n" +
                "        \"id\": \"MATH1001\",\n" +
                "        \"type\": \"mandatory\",\n" +
                "        \"credit\": 6,\n" +
                "        \"isSession\": false,\n" +
                "        \"day\": [\"TUESDAY\"]\n" +
                "    }]}";

        // Create temporary file with mock content
        File file = new File("src\\UnitTests\\temp.json");
        Path tempFile = Path.of("src\\UnitTests\\temp.json");
        Files.write(tempFile, fileContent.getBytes());

        // Create CreateCourse instance and test
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        lecturers.add(lecturer);

        createCourse = new CreateCourse("src\\UnitTests\\temp.json", lecturers);
        // Clean up temporary file
        Files.deleteIfExists(tempFile);
    }
    @Test
    void testCreateCourse() throws IOException {

        setUp();
        ArrayList<Course> courses = createCourse.getCourses();
        // Test if courses are created and added properly
        assertNotNull(courses);
        assertEquals(1, courses.size());
        Course course = courses.get(0);
        assertEquals("MATH1001", course.getCourseId().getId());
        assertEquals("Calculus I", course.getCourseName());
        assertEquals(1, course.getTerm());
        assertEquals(15, course.getQuota());
        assertEquals(6, course.getCredit());
        assertEquals(Day.TUESDAY, course.getCourseSchedules().get(0).getCourseDay());
        assertEquals(Hour.H_13_00_13_50, course.getCourseSchedules().get(0).getCourseHours().get(0));

    }

    @Test
    void testJsonArrToStrArr() throws JSONException, IOException {
        setUp();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Item1");
        jsonArray.put("Item2");

        String[] result = createCourse.jsonArrToStrArr(jsonArray);
        assertArrayEquals(new String[]{"Item1", "Item2"}, result);
    }
}
