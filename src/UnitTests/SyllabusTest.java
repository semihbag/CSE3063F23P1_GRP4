package UnitTests;


import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import CourseObject.*;
import PersonObject.Id;


import org.junit.Before;
import java.util.ArrayList;
import java.util.Arrays;


public class SyllabusTest {

    Syllabus syllabus = new Syllabus();
    ArrayList<Course> courses = new ArrayList<Course>();
    Course ataCourse;

    @Before
    public void createSyllabusObject() {
        ArrayList<Hour> courseHour1 = new ArrayList<>(Arrays.asList(Hour.H_10_30_11_20,Hour.H_11_30_12_20));
        ArrayList<Hour> courseHour2 = new ArrayList<>(Arrays.asList(Hour.H_13_00_13_50));
        ArrayList<CourseSchedule> courseSchedules = new ArrayList<>(Arrays.asList(new CourseSchedule(Day.MONDAY,courseHour1), new CourseSchedule(Day.FRIDAY,courseHour2)));
        Course course1 = new Course(new Id("ATA121"), "Ataturk Ilkeleri ve Inkilap Tarihi I", 15, 1, null,courseSchedules,2,CourseType.MANDATORY);
        ataCourse = course1;
        syllabus.addCourseToSyllabus(course1);
    }

    @Test
    @DisplayName("FRIDAY value must be converted to the '4'.")
    public void fridayToFour() {
        assertEquals(4, syllabus.returnIndexDay(Day.FRIDAY));
    }

    @Test
    @DisplayName("H_14_00_14_50 value must be converted to the '5'.")
    public void H_14_00_14_50toFive() {
        assertEquals(5, syllabus.returnIndexHour(Hour.H_14_00_14_50));
    }

    @Test
    @DisplayName("ATA121 must be in ")
    public void checkATA121IsInSyllabus() {
        assertFalse(syllabus.isEmpty(2, 0));
        assertFalse(syllabus.isEmpty(3,0));
        assertFalse(syllabus.isEmpty(4, 4));
        assertTrue(syllabus.isEmpty(0, 0));
    }

    @Test
    @DisplayName("There must be a confict between ATA121 and MBG1201")
    public void conflictBetweenATAandMBG() {
        ArrayList<Hour> courseHour3 = new ArrayList<>(Arrays.asList(Hour.H_08_30_09_20,Hour.H_10_30_11_20));
        ArrayList<CourseSchedule> courseSchedules1 = new ArrayList<>(Arrays.asList(new CourseSchedule(Day.MONDAY,courseHour3)));
        Course course2 = new Course(new Id("MBG1201"),"Introduction to Modern Biology", 15,1,null,courseSchedules1,5,CourseType.MANDATORY);

        assertEquals(true, syllabus.checkConflict(course2));

    }
    
    @Test
    @DisplayName("Remove ATA121 from the syllabus")
    public void removeATA121FromSyllabus() {
        syllabus.removeCourseFromSyllabus(ataCourse);
        assertTrue(syllabus.isEmpty(2, 0));
        assertTrue(syllabus.isEmpty(3,0));
        assertTrue(syllabus.isEmpty(4, 4));
    }

    @Test
    @DisplayName("Add initally selected courses to the Syllabus")
    public void addInitiallyCoursesToTheSyllabus() {
        ArrayList<Hour> courseHour4 = new ArrayList<>(Arrays.asList(Hour.H_08_30_09_20,Hour.H_09_30_10_20));
        ArrayList<CourseSchedule> courseSchedules4 = new ArrayList<>(Arrays.asList(new CourseSchedule(Day.MONDAY,courseHour4)));
        Course course4 = new Course(new Id("MBG1201"),"Introduction to Modern Biology", 15,1,null,courseSchedules4,5,CourseType.MANDATORY);

        ArrayList<Hour> courseHour3 = new ArrayList<>(Arrays.asList(Hour.H_08_30_09_20,Hour.H_09_30_10_20));
        ArrayList<CourseSchedule> courseSchedules3 = new ArrayList<>(Arrays.asList(new CourseSchedule(Day.TUESDAY,courseHour3)));
        Course course3 = new Course(new Id("CSE1200"),"Introduction to Computer Engineering",15,1,null,courseSchedules3,4,CourseType.MANDATORY);

        ArrayList<Course> myCourses = new ArrayList<>();
        myCourses.add(course3);
        myCourses.add(course4);

        syllabus.fillSyllabus(myCourses);

        assertFalse(syllabus.isEmpty(0, 0));
        assertFalse(syllabus.isEmpty(1, 0));
        assertEquals("Introduction to Modern Biology", syllabus.getSyllabus()[0][0].getCourseName());
        assertEquals("Introduction to Modern Biology", syllabus.getSyllabus()[1][0].getCourseName());

        assertFalse(syllabus.isEmpty(0, 1));
        assertFalse(syllabus.isEmpty(1, 1));
        assertEquals("Introduction to Computer Engineering", syllabus.getSyllabus()[0][1].getCourseName());
        assertEquals("Introduction to Computer Engineering", syllabus.getSyllabus()[1][1].getCourseName());


    }
}
