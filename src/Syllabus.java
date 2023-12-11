
public class Syllabus {

    private Course[][] syllabus;

    public Syllabus() {
        syllabus = new Course[13][5];
    }

    public int returnIndexDay(Day day) {

        switch(day) {
        case MONDAY:
            return 0;
        case TUESDAY:
            return 1;
        case WEDNESDAY:
            return 2;
        case THURSDAY:
            return 3;
        case FRIDAY:
            return 4;
        default:
            System.out.println("The content of day is invalid");
            return -1;
        }

    }

    public int returnIndexHour(Hour hour) {

        switch(hour) {
            
            case H_08_30_09_20:
                return 0;
            case H_09_30_10_20:
                return 1;
            case H_10_30_11_20:
                return 2;
            case H_11_30_12_20:
                return 3;
            case H_13_00_13_50:
                return 4;
            case H_14_00_14_50:
                return 5;
            case H_15_00_15_50:
                return 6;
            case H_16_00_16_50:
                return 7;
            case H_17_00_17_50:
                return 8;
            case H_18_00_18_50:
                return 9;
            case H_19_00_19_50:
                return 10;
            case H_20_00_20_50:
                return 11;
            case H_21_00_21_50:
                return 12;
            default:
                System.out.println("The content of hour is invalid");
                return -1;
        }

    }

  public void addCourseToSyllabus(Course course) {

    // courseların day attribute u single-dimensional array
    // courseların time attribute ları array içinde array olarak tutulacak

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
                    System.out.println("The course that you want to add conflicts with another course");
                    return true;
                }
            }
        }
        return false;
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
