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

    SystemDomain() throws JSONException, IOException {
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
        String content = null;
        content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\lecturers.json")));
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
        String content = null;
        content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\advisors.json")));
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
            String name = jsonStudent.getString("name");
            String lastname = jsonStudent.getString("lastname");
            String id = jsonStudent.getString("id");
            String password = jsonStudent.getString("password");
            String advisorID = jsonStudent.getString("advisor");
            Advisor advisor = null;
            for (int i = 0; i < getAdvisors().size(); i++) {
                if (getAdvisors().get(i).getId().getId().equals(advisorID)) {
                    advisor = getAdvisors().get(i);
                }
            }
            double gpa = transcript.getDouble("gpa");
            int year = transcript.getInt("year");

            String[] completedCoursesID = jsonArrToStrArr(transcript.getJSONArray("completedcourses"));
            ArrayList<Course> completedCourses = new ArrayList<>();
            for (String value : completedCoursesID) {
                for (int j = 0; j < getCourses().size(); j++) {
                    if (value.equals(getCourses().get(j).getCourseID().getId())) {
                        completedCourses.add(getCourses().get(j));
                    }
                }
            }

            String[] failedCoursesID = jsonArrToStrArr(transcript.getJSONArray("failedcourses"));
            ArrayList<Course> failedCourses = new ArrayList<>();
            for (String s : failedCoursesID) {
                for (int i = 0; i < courses.size(); i++) {
                    if (s.equals(courses.get(i).getCourseID().getId())) {
                        failedCourses.add(getCourses().get(i));
                    }
                }
            }
            students.add(new Student(name, lastname, new Id(id), new Password(password), advisor,
                    new Transcript(gpa, year, completedCourses, failedCourses)));
        }
    }

    private void createCourses() throws JSONException, IOException{
        String content = null;
        content = new String(Files.readAllBytes(Path.of("src\\JSON_Files\\courses.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray courseJSON = jsonObject.getJSONArray("courses");
        for(int i =0 ; i< courseJSON.length();i++){
            String courseId =courseJSON.getJSONObject(i).getString("id");
            String name = courseJSON.getJSONObject(i).getString("name");
            int year = courseJSON.getJSONObject(i).getInt("year");
            if(courseJSON.getJSONObject(i).getBoolean("hasSession")){
                JSONArray sessionJSON = courseJSON.getJSONObject(i).getJSONArray("session");
                String[] prequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prequisite"));
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
                    for(String str: prequisiteId){
                        for(Course crs: courses){
                            if(str.equals(crs.getCourseID().getId())){
                                courseSession.getPrequisiteCourses().add(crs.getCourseID());
                            }
                        }
                    }
                    courses.add(courseSession);
                }
            }
            else{
                int quota = courseJSON.getJSONObject(i).getInt("quota");
                String day_hour = courseJSON.getJSONObject(i).getString("day_hour");
                String[] prequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prequisite"));
                Lecturer courseLecturer=null;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getId().getId().equals(courseJSON.getJSONObject(i).getString("lecturer"))) {
                        courseLecturer=lecturer;
                        break;
                    }
                }
                Course course = new Course(new Id(courseId),name, quota, year ,day_hour,courseLecturer);
                for(String str: prequisiteId){
                    for(Course crs: courses){
                        if(str.equals(crs.getCourseID().getId())){
                            course.getPrequisiteCourses().add(crs.getCourseID());
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

    public void assignStudentsToAdvisor() {
        for (Student student : students) {
            for (Advisor advisor : advisors) {
                if (student.getAdvisor().getId().getId().equals(advisor.getId().getId())) {
                    advisor.getStudents().add(student);
                    break;
                }
            }
        }
    }

    public void assignCoursesToLecturer() {
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