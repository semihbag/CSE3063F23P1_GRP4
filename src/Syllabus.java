import java.util.ArrayList;

public class Syllabus {

    private Course[][] syllabus = new Course[13][5];

    public int returnIndexDay(Day day) {
        return switch (day) {
            case MONDAY -> 0;
            case TUESDAY -> 1;
            case WEDNESDAY -> 2;
            case THURSDAY -> 3;
            case FRIDAY -> 4;
        };
    }

    public int returnIndexHour(Hour hour) {
        return switch (hour) {
            case H_08_30_09_20 -> 0;
            case H_09_30_10_20 -> 1;
            case H_10_30_11_20 -> 2;
            case H_11_30_12_20 -> 3;
            case H_13_00_13_50 -> 4;
            case H_14_00_14_50 -> 5;
            case H_15_00_15_50 -> 6;
            case H_16_00_16_50 -> 7;
            case H_17_00_17_50 -> 8;
            case H_18_00_18_50 -> 9;
            case H_19_00_19_50 -> 10;
            case H_20_00_20_50 -> 11;
            case H_21_00_21_50 -> 12;
        };
    }

  public void addCourseToSyllabus(Course course) {

        for (int i = 0 ; i < course.getCourseSchedules().size() ; i++) {
            CourseSchedule courseSchedule = course.getCourseSchedules().get(i);
            int day_column = returnIndexDay(courseSchedule.getCourseDay());
            for (int j = 0 ; j < courseSchedule.getCourseHours().size() ; j++) {
                int hour_row = returnIndexHour(courseSchedule.getCourseHours().get(j));
                syllabus[hour_row][day_column] = course;
            }
        }
    } 

  public boolean checkConflict(Course course) {

        for (int i = 0 ; i < course.getCourseSchedules().size() ; i++) {
            CourseSchedule courseSchedule = course.getCourseSchedules().get(i);
            int day_column = returnIndexDay(courseSchedule.getCourseDay());
            for (int j = 0 ; j < courseSchedule.getCourseHours().size() ; j++) {
                int hour_row = returnIndexHour(courseSchedule.getCourseHours().get(j));
                if (!isEmpty(hour_row, day_column)) {
                    System.out.println("The course that you want to add " + course.getCourseName() + " conflicts with " + syllabus[hour_row][day_column].getCourseName());
                    return true;
                }
            }
        }
        return false;
    }

    // fill 
    public void fillSyllabus(ArrayList<Course> selectedCourses) {

        int totalSelectedCourses = selectedCourses.size();
        for (int i = 0 ; i < totalSelectedCourses ; i++) {
            Course selectCourse = selectedCourses.get(i);
            int totalCourseSchedules = selectCourse.getCourseSchedules().size();
            for (int j = 0 ; j < totalCourseSchedules ; j++) {
                CourseSchedule courseSchedule = selectCourse.getCourseSchedules().get(j);
                Day courseDay = courseSchedule.getCourseDay();
                int totalTimeCourseDay = courseSchedule.getCourseHours().size();
                for (int k = 0 ; k < totalTimeCourseDay ; k++) {
                    int hour_row = returnIndexHour(courseSchedule.getCourseHours().get(k));
                    int day_column = returnIndexDay(courseDay);
                    syllabus[hour_row][day_column] = selectCourse;
                }
            }
        }

    }
    // delete
    public void removeCourseFromSyllabus(Course course) {

        int totalCourseSchedules = course.getCourseSchedules().size();
        for (int i = 0 ; i < totalCourseSchedules ; i++ ) {
            CourseSchedule courseSchedule = course.getCourseSchedules().get(i);
            Day courseDay = courseSchedule.getCourseDay();
            int totalTimeCourseDay = courseSchedule.getCourseHours().size();
            for(int j = 0 ; j < totalTimeCourseDay ; j++) {
                int hour_row = returnIndexHour(courseSchedule.getCourseHours().get(j));
                int day_column = returnIndexDay(courseDay);
                syllabus[hour_row][day_column] = null;
            }
        }
    }

  public boolean isEmpty(int rowIndex, int columnIndex) {
        
        if (getSyllabus()[rowIndex][columnIndex] != null) {
            return false;
        }
        else {
            return true;
        }
        
    }  
    
    public Course[][] getSyllabus() {
        return syllabus;
    }
    public void setSyllabus(Course[][] syllabus) {
        this.syllabus = syllabus;
    }  
}
