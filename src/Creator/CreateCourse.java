package Creator;

import CourseObject.*;
import PersonObject.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CreateCourse {
    private ArrayList<Course> courses = new ArrayList<>();
    private String fileName;

    public CreateCourse(String fileName, ArrayList<Lecturer> lecturers) {
        this.fileName = fileName;
        createCourses(lecturers);
    }
    private void createCourses(ArrayList<Lecturer> lecturers)  {
        try {
            String content = new String(Files.readAllBytes(Path.of(fileName)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray courseJSON = jsonObject.getJSONArray("courses");
            for (int i = 0; i < courseJSON.length(); i++) {
                String courseId = courseJSON.getJSONObject(i).getString("id");
                String name = courseJSON.getJSONObject(i).getString("name");
                int term = courseJSON.getJSONObject(i).getInt("term");
                int quota = courseJSON.getJSONObject(i).getInt("quota");
                String[] prerequisiteId = jsonArrToStrArr(courseJSON.getJSONObject(i).getJSONArray("prerequisite"));
                int credit = courseJSON.getJSONObject(i).getInt("credit");
                String courseTypeStr = courseJSON.getJSONObject(i).getString("type");
                Lecturer courseLecturer = null;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getLecturerId().getId().equals(courseJSON.getJSONObject(i).getString("lecturer"))) {
                        courseLecturer = lecturer;
                        break;
                    }
                }
                ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
                fillCourseSchedule(courseJSON.getJSONObject(i).getJSONArray("day"), courseJSON.getJSONObject(i).getJSONArray("hour"), courseSchedule);
                CourseType courseType = setCourseType(courseTypeStr);
                Course course = null;
                if (courseJSON.getJSONObject(i).getBoolean("isSession")) {
                    String sessionId = courseJSON.getJSONObject(i).getString("sessionId");
                    course = new CourseSession(new Id(courseId), name, quota, term, courseLecturer, new Id(sessionId), courseSchedule, credit, courseType);
                } else {
                    course = new Course(new Id(courseId), name, quota, term, courseLecturer, courseSchedule, credit, courseType);
                }

                for (String str : prerequisiteId) {
                    for (Course crs : courses) {
                        if (crs.getCourseId().getId().equals(str)) {
                            course.getPrerequisiteCourses().add(crs);
                            break;
                        }
                    }
                }
                courses.add(course);
            }
            assignCoursesToLecturer(lecturers);
        }
        catch (JSONException | IOException ignored){
            System.out.println("An error occurred in the courses JSON file. Please ensure that the file is created in the correct format and fix any errors.");
            System.exit(0);
        }
    }
    private void assignCoursesToLecturer(ArrayList<Lecturer> lecturers) {
        for (Course course : courses) {
            for (Lecturer lecturer : lecturers) {
                if (course.getLecturer().getLecturerId().getId().equals(lecturer.getLecturerId().getId())) {
                    lecturer.getGivenCourses().add(course);
                }
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

    private void fillCourseSchedule(JSONArray dayJsonArr,JSONArray hourJsonArr,ArrayList<CourseSchedule> courseSchedules) throws JSONException {
        String[] dayStrArr = jsonArrToStrArr(dayJsonArr);
        for(int i=0;i<dayJsonArr.length();i++){
            Day currentDay = getCourseDay(dayStrArr[i]);
            ArrayList<Hour> hours = new ArrayList<>();
            JSONArray currentHourJsonArr= hourJsonArr.getJSONArray(i);
            for(int j=0;j<currentHourJsonArr.length();j++){
                hours.add(getCourseHour(currentHourJsonArr.getString(j)));
            }
            courseSchedules.add(new CourseSchedule(currentDay,hours));
        }

    }
    private Day getCourseDay(String strDay){
        return switch (strDay.toUpperCase()) {
            case "MONDAY" -> Day.MONDAY;
            case "TUESDAY" -> Day.TUESDAY;
            case "WEDNESDAY" -> Day.WEDNESDAY;
            case "THURSDAY" -> Day.THURSDAY;
            case "FRIDAY" -> Day.FRIDAY;
            default -> null;
        };
    }
    private Hour getCourseHour(String strHour){
        return switch (strHour.toUpperCase()) {
            case "8.30" -> Hour.H_08_30_09_20;
            case "9.30" -> Hour.H_09_30_10_20;
            case "10.30" -> Hour.H_10_30_11_20;
            case "11.30" -> Hour.H_11_30_12_20;
            case "13.00" -> Hour.H_13_00_13_50;
            case "14.00" -> Hour.H_14_00_14_50;
            case "15.00" -> Hour.H_15_00_15_50;
            case "16.00" -> Hour.H_16_00_16_50;
            case "17.00" -> Hour.H_17_00_17_50;
            case "18.00" -> Hour.H_18_00_18_50;
            case "19.00" -> Hour.H_19_00_19_50;
            case "20.00" -> Hour.H_20_00_20_50;
            case "21.00" -> Hour.H_21_00_21_50;
            default -> null;
        };
    }
    private CourseType setCourseType(String courseTypeStr) {
        return switch (courseTypeStr) {
            case "mandatory" -> CourseType.MANDATORY;
            case "technical" -> CourseType.TECHNICAL;
            case "nontechnical" -> CourseType.NONTECHNICAL;
            case "faculty" -> CourseType.FACULTY;
            default -> null;
        };
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
