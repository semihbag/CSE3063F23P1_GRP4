package UnitTests;

import CourseObject.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import PersonObject.*;

import org.junit.Before;

import java.util.ArrayList;

public class StudentTest {

    @Test
    public void isFailedCourse() {
        Id id1 = new Id("CSE2023");
        Id id2 = new Id("MATH1002");
        Id id3 = new Id("MBG1201");

        // Örnek kurslar oluşturuluyor
        Course course1 = new Course(id1, null, 0, 3, null, null, 0,null);
        Course course2 = new Course(id2, null, 0, 2, null, null, 0,null);
        Course course3 = new Course(id3, null, 0, 1, null, null, 0,null);


        // Örnek GradeClass nesneleri oluşturuluyor
        GradeClass passedCourse1 = new GradeClass(course1, Grade.AA);
        GradeClass failedCourse1 = new GradeClass(course2, Grade.FF);
        GradeClass eklenmedi = new GradeClass(course3, Grade.FD);

        // Transcript nesnesi oluşturuluyor ve bu nesneye başarılı ve başarısız dersler ekleniyor
        ArrayList<GradeClass> passedCourses = new ArrayList<>();
        ArrayList<GradeClass> failedCourses = new ArrayList<>();

        passedCourses.add(passedCourse1);
        failedCourses.add(failedCourse1);

        Transcript transcript = new Transcript(0, 4, passedCourses, failedCourses);

        // Student nesnesi oluşturuluyor ve bu nesneye Transcript nesnesi ekleniyor
        Student student = new Student(null, null, null, null, null, transcript,null);

        // İlk ders başarılı olduğu için false dönmeli
        assertFalse(student.isFailedCourse(course1));

        // İkinci ders başarısız olduğu için tru dönmeli
        assertTrue(student.isFailedCourse(course2));

        // Hiç ders eklenmemişse false dönmeli
        assertFalse(student.isFailedCourse(course3));
    }


    @Test
    public void exceed(){
        ArrayList<GradeClass> passedCourses = new ArrayList<>();

        Transcript transcript = new Transcript(0,0,passedCourses,null);
        Student student = new Student(null,null,null,null,null,transcript,null);

        Course course1 = new Course(null, null,0,0, null, null, 0, CourseType.NONTECHNICAL);
        GradeClass gradeClass1 = new GradeClass(course1, Grade.AA);
        passedCourses.add(gradeClass1);

        assertFalse(student.exceed(course1.getCourseType(), 2));

        Course course2 = new Course(null, null,0,0, null, null, 0, CourseType.NONTECHNICAL);
        GradeClass gradeClass2 = new GradeClass(course1, Grade.AA);
        passedCourses.add(gradeClass2);
        assertTrue(student.exceed(course2.getCourseType(), 2));

        Course course3 = new Course(null, null,0,0, null, null, 0, CourseType.FACULTY);
        GradeClass gradeClass3 = new GradeClass(course3, Grade.AA);
        passedCourses.add(gradeClass3);

        assertFalse(student.exceed(course3.getCourseType(), 2));

        Course course4 = new Course(null, null,0,0, null, null, 0, CourseType.FACULTY);
        GradeClass gradeClass4 = new GradeClass(course4, Grade.AA);
        passedCourses.add(gradeClass4);
        assertTrue(student.exceed(course4.getCourseType(), 2));

    }
    @Test
    public void exceedTerm(){
        Student student= new Student(null, null, null, null, null, null, null);
        
        //CASE 1: Check status if student does not select specific course type in current term
        assertFalse(student.exceedTerm(CourseType.MANDATORY));
        assertFalse(student.exceedTerm(CourseType.TECHNICAL));
        assertFalse(student.exceedTerm(CourseType.FACULTY));

        //CASE2 : If student select one specific course type in current term
        Course nonTechnical = new Course(null, "Strategic Entrepreneurship",0,3,null,null,0,CourseType.NONTECHNICAL);
        Course technical = new Course(null, "Microprocessors",0,3,null,null,0,CourseType.TECHNICAL);
        Course faculty = new Course(null, "Introduction to Image Processing",0,3,null,null,0,CourseType.FACULTY);


        ArrayList<Course> selectedCourses = new ArrayList<Course>();
        selectedCourses.add(nonTechnical);
        selectedCourses.add(technical);
        selectedCourses.add(faculty);

        student.setSelectedCourses(selectedCourses);


        assertTrue(student.exceedTerm(CourseType.NONTECHNICAL));
        assertTrue(student.exceedTerm(CourseType.TECHNICAL));
        assertTrue(student.exceedTerm(CourseType.FACULTY));


    }

}