from python_development.Creator.CreateAdvisor import CreateAdvisor
from python_development.Creator.CreateCourse import CreateCourse
from python_development.Creator.CreateLecturer import CreateLecturer
from python_development.Creator.CreateStudent import CreateStudent


class CreatePage:
    pass


class SystemDomain:
    def __init__(self):
        self.lecturerCreator = CreateLecturer("python_development\\JSON_Files\\lecturers.json")
        self.advisorCreator = CreateAdvisor("python_development\\JSON_Files\\advisors.json", self.lecturerCreator.getLecturers())
        self.courseCreator = CreateCourse("python_development\\JSON_Files\\courses.json", self.lecturerCreator.getLecturers())
        self.studentCreator = CreateStudent("python_development\\JSON_Files\\Students\\", "python_development\\JSON_Files\\student_json.txt",
                                             self.courseCreator.getCourses(), self.advisorCreator.getAdvisors())
        self.pageCreator = CreatePage()

    def getLecturerCreator(self):
        return self.lecturerCreator

    def getAdvisorCreator(self):
        return self.advisorCreator

    def getCourseCreator(self):
        return self.courseCreator

    def getStudentCreator(self):
        return self.studentCreator

    def getPageCreator(self):
        return self.pageCreator
