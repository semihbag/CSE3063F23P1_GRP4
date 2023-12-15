package UnitTests;

import CourseObject.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import PersonObject.*;

import org.junit.Before;

import java.util.ArrayList;

public class StudentTest {

    @Test
    public void checkCourseType() {

        //NONTECHNICAL
        Course BUS1004 = new Course(null, "Strategic Entrepreneurship",0,3,null,null,0,CourseType.NONTECHNICAL);

        // CASE1: Student term < 2
        Transcript term1 = new Transcript(0,1,new ArrayList<GradeClass>(),null);
        Student studentTerm1 = new Student(null, null, null, null, null, term1, null);
        assertFalse(studentTerm1.checkCourseType(BUS1004));

        // CASE2: Student term >=2
        Transcript term2 = new Transcript(0,2,new ArrayList<GradeClass>(),null);
        Student studentTerm2 = new Student(null, null, null, null, null, term2, null);
        assertTrue(studentTerm2.checkCourseType(BUS1004));


        //TECHNICAL
        Course CSE4217 = new Course(null, "Microprocessors",0,7,null,null,0,CourseType.TECHNICAL);

        // CASE 1: Student term = 7
        Transcript term7 = new Transcript(0,7,new ArrayList<GradeClass>(),null);
        Student studentTerm7 = new Student(null, null, null, null, null, term7, null);
        assertTrue(studentTerm7.checkCourseType(CSE4217));

        // CASE 2: Student term != 7 OR 8
        assertFalse(studentTerm1.checkCourseType(CSE4217));


        //FACULTY
        Course EE4062 = new Course(null, "Introduction to Image Processing",0,0,null,null,0,CourseType.FACULTY);

        //CASE 1: Student term == 7
        assertTrue(studentTerm7.checkCourseType(EE4062));

        //CASE 2: Student term !=7
        assertFalse(studentTerm1.checkCourseType(EE4062));


        //MANDATORY
        Course ATA121 = new Course(null, "Ataturk Ilkeleri ve Inkilap Tarihi I",0,1,null,null,0,CourseType.MANDATORY);
        Course Engineering_Project_I = new Course(null, "Engineering Project I",0,0,null,null,0,CourseType.MANDATORY);
        Course Engineering_Project_II = new Course(null, "Engineering Project II",0,0,null,null,0,CourseType.MANDATORY);


        //Engineering Project I and Engineering Project II

        //CASE1: COURSE: Engineering Project I, Student's credit >= 165
        term7.setTotalCredit(180);
        assertTrue(studentTerm7.checkCourseType(Engineering_Project_I));

        //CASE2: COURSE: Engineering Project I, Student's credit < 165
        term7.setTotalCredit(100);
        assertFalse(studentTerm7.checkCourseType(Engineering_Project_I));

        //CASE3: COURSE: Engineering Project II, Student's credit >= 165 TRUE
        term7.setTotalCredit(180);
        assertTrue(studentTerm7.checkCourseType(Engineering_Project_II));

        //CASE4: COURSE: Engineering Project II, Student's credit < 165 FALSE
        term7.setTotalCredit(90);
        assertFalse(studentTerm7.checkCourseType(Engineering_Project_II));

        //Taking the course of his own term
        //CASE5: course's term == Student's term
        assertFalse(studentTerm2.checkCourseType(ATA121));


        //Is Sagligi ve Guvenligi I and Is Sagligi ve Guvenligi II
        Course isgI = new Course(null, "Is Sagligi ve Guvenligi I",0,7,null,null,0,CourseType.MANDATORY);
        Transcript term3 = new Transcript(2.5,3,new ArrayList<GradeClass>(),null);
        Student studentTerm3 = new Student(null, null, null, null, null, term3, null);

        //CASE6: Student's gpa < 3.0 && Student's year >= 3
        assertFalse(studentTerm3.checkCourseType(isgI));

        //Taking a course at a higher level"
        //CAS7: Student's gpa >= 3.0 && Student's year < 3
        Transcript term3_1 = new Transcript(3.1,1,new ArrayList<GradeClass>(),null);
        Student studentTerm3_1 = new Student(null, null, null, null, null, term3_1, null);
        assertFalse(studentTerm3_1.checkCourseType(isgI));

        //CASE8: Student's gpa >= 3.0 && Student's year >= 3
        Transcript term3_2 = new Transcript(3.1,3,new ArrayList<GradeClass>(),null);
        Student studentTerm3_2 = new Student(null, null, null, null, null, term3_2, null);
        assertTrue(studentTerm3_2.checkCourseType(isgI));

        //CASE9: Student's gpa >= 3.0 && Student's year >= 3 course's year == Student's year +2
        Course courseTerm5 = new Course(null, "Digital Logic Design",0,5,null,null,0,CourseType.MANDATORY);
        assertTrue(studentTerm3_2.checkCourseType(courseTerm5));

        //CASE10: Student's gpa >= 3.0 && Student's year >= 3 course's != Student's year +2
        Course courseTerm7 = new Course(null, "Computer Networks",0,7,null,null,0,CourseType.MANDATORY);
        assertFalse(studentTerm3_2.checkCourseType(courseTerm7));

    }

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