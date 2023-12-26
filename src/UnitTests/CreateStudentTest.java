package UnitTests;

import CourseObject.*;
import PersonObject.*;
import Creator.CreateStudent;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PersonObject.Id;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateStudentTest {

    private CreateStudent createStudent;

    @BeforeEach
    void setUp() {
        String fileName = "src\\JSON_Files\\Students\\";
        String studentsFile = "src\\JSON_Files\\student_json.txt";
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Advisor> advisors = new ArrayList<>();
        try {
            createStudent = new CreateStudent(fileName, studentsFile, courses, advisors);
        } catch (JSONException | IOException e) {
        }
    }

    @Test
    void testGetStudents() {
        assertNotNull(createStudent.getStudents());
    }

    @Test
    void testCreateStudents() {
        try {
            String fileName = "src\\JSON_Files\\Students\\";
            String studentsFile = "src\\JSON_Files\\student_json.txt";
            createStudent = new CreateStudent(fileName, studentsFile, new ArrayList<>(),  new ArrayList<>());
            Advisor advisor = new Advisor("Mustafa","Agaoglu", new Id("1501002"), new Password("sql112233"));
            assertEquals(27, createStudent.getStudents().size());
            Student student = createStudent.getStudents().get(0);
            student.setAdvisor(advisor);
            assertEquals("Nihal", student.getFirstName());
            assertEquals("Akduran", student.getLastName());
            assertEquals("150122004", student.getPersonId().getId());
            assertEquals("Nihal.123",student.getPassword().getPassword());
            assertEquals("1501002",student.getAdvisor().getPersonId().getId());
            assertEquals(1,student.getTranscript().getTerm());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCalculateGPA_WithPassedCourses() {
        ArrayList<GradeClass> passedCourses = new ArrayList<>();
        ArrayList<GradeClass> failedCourses = new ArrayList<>();
        passedCourses.add(new GradeClass(new Course(new Id("Id1"), "Name", 10, 1,
                new Lecturer("A","B", new Id("1501000"), new Password("lecturer123")),
                new ArrayList<CourseSchedule>(), 3, CourseType.MANDATORY), Grade.AA));
        passedCourses.add(new GradeClass(new Course(new Id("Id2"), "Name2", 10, 1,
                new Lecturer("C","D", new Id("1501111"), new Password("lecturer123")),
                new ArrayList<CourseSchedule>(), 2, CourseType.MANDATORY), Grade.BB));
        double result = createStudent.calculateGPA(passedCourses, failedCourses);
        double expectedGPA = (4.0 * 3 + 3.0 * 2) / (3 + 2);
        assertEquals(expectedGPA, result);
    }

    @Test
    void testSetStudentCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(new Id("C1"), "Name", 10, 1,
                new Lecturer("A","B", new Id("1501000"), new Password("lecturer123")),
                new ArrayList<CourseSchedule>(), 3, CourseType.MANDATORY));
        courses.add(new CourseSession(new Id("C2"), "Name", 10, 1,
                new Lecturer("A","B", new Id("1501000"), new Password("lecturer123")), new Id("S1"),
                new ArrayList<CourseSchedule>(),2, CourseType.MANDATORY));

        String[] studentCoursesAr = {"C1", "C2.S1"};
        ArrayList<Course> result = createStudent.setStudentCourses(studentCoursesAr, courses);
        assertEquals(2, result.size());
        assertEquals("C1", result.get(0).getCourseId().getId());
        assertEquals("C2", result.get(1).getCourseId().getId());
        assertEquals("S1", ((CourseSession) result.get(1)).getSessionId().getId());
    }
}