package Creator;

import CourseObject.*;
import PersonObject.Advisor;
import PersonObject.Id;
import PersonObject.Password;
import PersonObject.Student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CreateStudent {
    private ArrayList<Student> students = new ArrayList<>();
    private String fileName;
    private String studentsFile;

    public CreateStudent(String fileName, String studentsFile, ArrayList<Course> courses, ArrayList<Advisor> advisors) throws JSONException, IOException{
        this.fileName = fileName;
        this.studentsFile = studentsFile;
        createStudents(courses,advisors);
    }

    private void createStudents(ArrayList<Course> courses,ArrayList<Advisor> advisors) throws IOException, JSONException {
        File allStudentFiles = new File(studentsFile);
        Scanner allStudentFilesInput = new Scanner(allStudentFiles);
        while (allStudentFilesInput.hasNextLine()) {
            try {
                String content = new String(Files.readAllBytes(Path.of(fileName + allStudentFilesInput.nextLine())));
                JSONObject jsonStudent = new JSONObject(content);
                JSONObject transcript = jsonStudent.getJSONObject("transcript");
                JSONObject registration = jsonStudent.getJSONObject("registration");

                String id = jsonStudent.getString("id");
                String name = jsonStudent.getString("name");
                String lastname = jsonStudent.getString("lastname");
                String advisorID = jsonStudent.getString("advisor");
                String booleanString = jsonStudent.getString("request");
                String[] readNotification = jsonArrToStrArr(jsonStudent.getJSONArray("readNotification"));
                String[] unreadNotification = jsonArrToStrArr(jsonStudent.getJSONArray("unreadNotification"));
                String password = jsonStudent.getString("password");

                String[] failedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("failedCourses"));
                String[] completedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("passedCourses"));
                String[] gradesPassed = jsonArrToStrArr(transcript.getJSONArray("gradesPassed"));
                String[] gradesFailed = jsonArrToStrArr(transcript.getJSONArray("gradesFailed"));
                int[] termPassed = jsonArrToIntArr(transcript.getJSONArray("termPassed"));

                String[] selectedCoursesAr = jsonArrToStrArr(registration.getJSONArray("selectedCourses"));
                String[] approvedCoursesAr = jsonArrToStrArr(registration.getJSONArray("approvedCourses"));

                ArrayList<GradeClass> failedCourses = setTranscriptCourses(failedCoursesAr, gradesFailed, termPassed,courses);
                ArrayList<GradeClass> passedCourses = setTranscriptCourses(completedCoursesAr, gradesPassed, termPassed,courses);

                ArrayList<Course> selectedCourses = setStudentCourses(selectedCoursesAr,courses);
                ArrayList<Course> approvedCourses = setStudentCourses(approvedCoursesAr,courses);

                int term = transcript.getInt("term");
                double gpa = calculateGPA(passedCourses, failedCourses);

                Student crtStudent = new Student(name, lastname, new Id(id), new Password(password), findAdvisor(advisorID,advisors),
                        new Transcript(gpa, term, passedCourses, failedCourses), courses);

                crtStudent.setRequest(booleanString);
                crtStudent.setReadNotifications(new ArrayList<>(Arrays.asList(readNotification)));
                crtStudent.setUnreadNotifications(new ArrayList<>(Arrays.asList(unreadNotification)));
                crtStudent.setSelectedCourses(selectedCourses);
                crtStudent.setApprovedCourses(approvedCourses);
                crtStudent.filterCourses();
                students.add(crtStudent);
            } catch (JSONException | IOException ignored) {
            }
        }
        assignStudentsToAdvisor(advisors);
        fillStudentListCourse(courses);
    }

    private String[] jsonArrToStrArr(JSONArray jsonArray) throws JSONException {
        String[] strings = new String[jsonArray.length()];
        for(int i=0; i<jsonArray.length();i++){
            strings[i]=jsonArray.getString(i);
        }
        return strings;
    }

    private int[] jsonArrToIntArr(JSONArray jsonArray) throws JSONException {
        int[] ints = new int[jsonArray.length()];
        for(int i=0; i<jsonArray.length();i++){
            ints[i]=jsonArray.getInt(i);
        }
        return ints;
    }

    private ArrayList<GradeClass> setTranscriptCourses(String[] transcriptCourses, String[] grades, int[] termPassed,ArrayList<Course> courses) {
        ArrayList<GradeClass> transcriptCourseList = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < transcriptCourses.length; i++) {
            for (Course course : courses) {
                if (course.getCourseId().getId().equals(transcriptCourses[i]) && !courseExists(course, transcriptCourseList)) {
                    GradeClass gradeClass = new GradeClass(course, getCourseGrade(grades[i]));
                    if(course.getCourseType()== CourseType.NONTECHNICAL || course.getCourseName().contains("Is Sagligi ve Guvenligi")) {
                        gradeClass.setTerm(termPassed[j]);
                        j++;
                    }
                    else {
                        gradeClass.setTerm(course.getTerm());
                    }
                    transcriptCourseList.add(gradeClass);
                }
            }
        }
        return transcriptCourseList;
    }

    public ArrayList<Course> setStudentCourses(String[] studentCoursesAr, ArrayList<Course> courses) {
        ArrayList<Course> studentCoursesList = new ArrayList<>();
        for (String s : studentCoursesAr) {
            for (int j = 0; j < courses.size(); j++) {
                if ((courses.get(j) instanceof CourseSession &&
                        (courses.get(j).getCourseId().getId() + "." + ((CourseSession) courses.get(j)).getSessionId().getId()).equals(s))
                        || s.equals(courses.get(j).getCourseId().getId())) {
                    studentCoursesList.add(courses.get(j));
                }
            }
        } return studentCoursesList;
    }

    private Advisor findAdvisor(String advisorID,ArrayList<Advisor> advisors) {
        Advisor advisor = null;
        for (int i = 0; i < advisors.size(); i++) {
            if (advisors.get(i).getLecturerId().getId().equals(advisorID)) {
                advisor = advisors.get(i);
            }
        }
        return advisor;
    }

    private void assignStudentsToAdvisor(ArrayList<Advisor> advisors) {
        for (Student student : students) {
            for (Advisor advisor : advisors) {
                if (student.getAdvisor().getLecturerId().getId().equals(advisor.getLecturerId().getId())) {
                    advisor.getStudentList().add(student);
                    break;
                }
            }
        }
    }

    private void fillStudentListCourse(ArrayList<Course> courses) throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i=0; i<courseJSON.length();i++){
            JSONObject currentCourse = courseJSON.getJSONObject(i);
            String[] courseStudentsId = jsonArrToStrArr(currentCourse.getJSONArray("studentList"));
            for (String currStudentId : courseStudentsId) {
                for (Student st : students) {
                    if (st.getStudentId().getId().equals(currStudentId) && (!(courses.get(i).getStudentList().contains(st)))) {
                        courses.get(i).getStudentList().add(st);
                        break;
                    }
                }
            }
        }
    }

    private boolean courseExists(Course course, ArrayList<GradeClass> transcriptCourseList) {
        for (GradeClass gradeClass : transcriptCourseList) {
            if (course.getCourseId().getId().equals(gradeClass.getCourse().getCourseId().getId())) {
                return true;
            }
        } return false;
    }

    public double calculateGPA(ArrayList<GradeClass> passedCourses, ArrayList<GradeClass> failedCourses) {
        double gpa=0;
        int totalCredit=0;
        for(GradeClass current: passedCourses){
            gpa += (current.getCourse().getCredit())*(letterToGrade(current.getGrade()));
            totalCredit+=current.getCourse().getCredit();
        }
        for(GradeClass current: failedCourses){
            gpa += (current.getCourse().getCredit())*(letterToGrade(current.getGrade()));
            totalCredit+=current.getCourse().getCredit();
        }
        if(totalCredit==0){
            return 0;
        }
        else{
            return gpa/totalCredit;
        }
    }

    private double letterToGrade(Grade grade){
        return switch (grade){
            case AA -> 4.0;
            case BA -> 3.5;
            case BB -> 3.0;
            case CB -> 2.5;
            case CC -> 2.0;
            case DC -> 1.5;
            case DD -> 1.0;
            case FD -> 0.5;
            case FF, DZ -> 0.0;
        };
    }

    private Grade getCourseGrade(String strGrade){
        return switch (strGrade.toUpperCase()) {
            case "AA" -> Grade.AA;
            case "BA" -> Grade.BA;
            case "BB" -> Grade.BB;
            case "CB" -> Grade.CB;
            case "CC" -> Grade.CC;
            case "DC" -> Grade.DC;
            case "DD" -> Grade.DD;
            case "FD" -> Grade.FD;
            case "FF" -> Grade.FF;
            case "DZ" -> Grade.DZ;
            default -> null;
        };
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}

