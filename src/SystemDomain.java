import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import Draft_Classes.*;

public class SystemDomain {
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Advisor> advisors = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public SystemDomain() throws JSONException, IOException {
        createLecturers();
        createAdvisors();
        for (int i = 0; i < getAdvisors().size(); i++) {
            getLecturers().add(getAdvisors().get(i));
        }
        createCourses();
        createStudents();
        assignCoursesToLecturer();
        assignStudentsToAdvisor();
    }

    public void createLecturers() throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\lecturers.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray lecturerJSON = jsonObject.getJSONArray("lecturers");
        for(int i =0 ; i< lecturerJSON.length();i++){
            String name = lecturerJSON.getJSONObject(i).getString("name");
            String surname = lecturerJSON.getJSONObject(i).getString("surname");
            String lecturerId = lecturerJSON.getJSONObject(i).getString("lecturerId");
            lecturers.add(new Lecturer(new Id(lecturerId),name,surname));
        }
    }

    private void createAdvisors() throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\advisors.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray advisorJSON = jsonObject.getJSONArray("advisors");
        for(int i =0 ; i< advisorJSON.length();i++){
            String name = advisorJSON.getJSONObject(i).getString("name");
            String surname = advisorJSON.getJSONObject(i).getString("surname");
            String advisorId = advisorJSON.getJSONObject(i).getString("advisorId");
            String password = advisorJSON.getJSONObject(i).getString("password");
            advisors.add(new Advisor(name,surname,new Id(advisorId),new Password(password)));
        }
    }

    public void createStudents() throws JSONException, IOException {
        File allStudentFiles = new File("src\\JSON_Files\\student_json.txt");
        Scanner allStudentFilesInput = new Scanner(allStudentFiles);
        while (allStudentFilesInput.hasNextLine()) {

            String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\" + allStudentFilesInput.nextLine())));
            JSONObject jsonStudent = new JSONObject(content);
            JSONObject transcript = jsonStudent.getJSONObject("transcript");
            JSONObject registration = jsonStudent.getJSONObject("registration");

            String id = jsonStudent.getString("id");
            String name = jsonStudent.getString("name");
            String lastname = jsonStudent.getString("lastname");
            String advisorID = jsonStudent.getString("advisor");
            Boolean booleanValue = jsonStudent.getBoolean("request");
            String notification = jsonStudent.getString("notification");
            String password = jsonStudent.getString("password");

            String[] failedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("failedcourses"));
            String[] completedCoursesAr = jsonArrToStrArr(transcript.getJSONArray("completedcourses"));
            int year = transcript.getInt("year");
            double gpa = transcript.getDouble("gpa");

            String[] selectedCoursesAr = jsonArrToStrArr(registration.getJSONArray("selectedcourses"));
            String[] approvedCoursesAr = jsonArrToStrArr(registration.getJSONArray("approvedcourses"));

            ArrayList<Course> failedCourses = setTranscriptCourses(failedCoursesAr);
            ArrayList<Course> completedCourses = setTranscriptCourses(completedCoursesAr);
            ArrayList<Course> selectedCourses = setStudentCourses(selectedCoursesAr);
            ArrayList<Course> approvedCourses = setStudentCourses(approvedCoursesAr);

            Advisor advisor = null;
            for (int i = 0; i < getAdvisors().size(); i++) {
                if (getAdvisors().get(i).getId().getId().equals(advisorID)) {
                    advisor = getAdvisors().get(i);
                }
            }

            Student crtStudent = new Student(name, lastname, new Id(id), new Password(password), advisor,
                    new Transcript(gpa, year, completedCourses, failedCourses), courses);
            crtStudent.setRequest(booleanValue);
            crtStudent.setNotification(notification);
            crtStudent.setSelectedCourses(selectedCourses);
            crtStudent.setApprovedCourses(approvedCourses);
            students.add(crtStudent);
        }
    }

    private ArrayList<Course> setStudentCourses(String[] studentCoursesAr) {
        ArrayList<Course> studentCoursesList = new ArrayList<>();
        for (int i = 0; i < studentCoursesAr.length; i++) {
            if (studentCoursesAr[i].equals("")) {
                break;
            }
            for (int j = 0; j < getCourses().size(); j++) {
                if (studentCoursesAr[i].equals(getCourses().get(j).getCourseID().getId())) {
                    studentCoursesList.add(getCourses().get(j));
                }
            }
        }
        return studentCoursesList;
    }

    private ArrayList<Course> setTranscriptCourses(String[] transcriptCoursesAr) {
        ArrayList<Course> transcriptCoursesList = new ArrayList<>();
        for (int i = 0; i < transcriptCoursesAr.length; i++) {
            for (int j = 0; j < getCourses().size(); j++) {
                if (transcriptCoursesAr[i].equals(getCourses().get(j).getCourseID().getId())) {
                    transcriptCoursesList.add(getCourses().get(j));
                }
            }
        }
        return transcriptCoursesList;
    }

    private void createCourses() throws JSONException, IOException{
        String content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i =0 ; i< courseJSON.length();i++){
            String courseId =courseJSON.getJSONObject(i).getString("id");
            String name = courseJSON.getJSONObject(i).getString("name");
            int year = courseJSON.getJSONObject(i).getInt("year");
            if(courseJSON.getJSONObject(i).getBoolean("hasSession")){
                JSONArray sessionJSON = courseJSON.getJSONObject(i).getJSONArray("session");
                String[] prerequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prerequisite"));
                for(int j =0 ; j< sessionJSON.length();j++){
                    int quota = sessionJSON.getJSONObject(j).getInt("quota");
                    String day_hour=sessionJSON.getJSONObject(j).getString("day_hour");
                    String sessionId = sessionJSON.getJSONObject(j).getString("sessionId");
                    Lecturer courseLecturer=null;
                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getId().getId().equals(sessionJSON.getJSONObject(j).getString("lecturer"))) {
                            courseLecturer=lecturer;
                            break;
                        }
                    }
                    CourseSession courseSession =new CourseSession(new Id(courseId),name,quota,year,day_hour, courseLecturer, new Id(sessionId));
                    for(String str: prerequisiteId){
                        for(Course crs: courses){
                            for(Course prerequisiteCourse: courses){
                                if(crs.getCourseID().getId().equals(prerequisiteCourse.getCourseID().getId())){
                                    courseSession.getPrerequisiteCourses().add(prerequisiteCourse);
                                    break;
                                }
                            }
                        }
                    }
                    courses.add(courseSession);
                }
            }
            else{
                int quota = courseJSON.getJSONObject(i).getInt("quota");
                String day_hour = courseJSON.getJSONObject(i).getString("day_hour");
                String[] prerequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prerequisite"));
                Lecturer courseLecturer=null;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getId().getId().equals(courseJSON.getJSONObject(i).getString("lecturer"))) {
                        courseLecturer=lecturer;
                        break;
                    }
                }
                Course course = new Course(new Id(courseId),name, quota, year ,day_hour,courseLecturer);
                for(String str: prerequisiteId){
                    for(Course crs: courses){
                        if(str.equals(crs.getCourseID().getId())){
                            for(Course prerequisite: courses){
                                if(crs.getCourseID().getId().equals(prerequisite.getCourseID().getId())){
                                    course.getPrerequisiteCourses().add(prerequisite);
                                    break;
                                }
                            }

                        }
                    }
                }
                courses.add(course);
            }
        }
    }

    public String[] jsonArrToStrArr(JSONArray jsonArray) throws JSONException {
        String[] strings = new String[jsonArray.length()];
        for(int i=0; i<jsonArray.length();i++){
            strings[i]=jsonArray.getString(i);
        }
        return strings;
    }

    private void assignStudentsToAdvisor() {
        for (Student student : students) {
            for (Advisor advisor : advisors) {
                if (student.getAdvisor().getId().getId().equals(advisor.getId().getId())) {
                    advisor.getStudents().add(student);
                    break;
                }
            }
        }
    }

    private void assignCoursesToLecturer() {
        for (Course course : courses) {
            for (Lecturer lecturer : lecturers) {
                if (course.getLecturer().getId().getId().equals(lecturer.getId().getId())) {
                    lecturer.getGivenCourses().add(course);
                }
            }
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }
}