from iter3.python_development.Creator.CreateAdvisor import CreateAdvisor
from iter3.python_development.Creator.CreateCourse import CreateCourse
from iter3.python_development.Creator.CreateLecturer import CreateLecturer
from iter3.python_development.Creator.CreatePage import CreatePage
from iter3.python_development.Creator.CreateStudent import CreateStudent


class SystemDomain:
    def __init__(self):
        self.lecturerCreator = CreateLecturer("JSON_Files\\lecturers.json")
        self.advisorCreator = CreateAdvisor("JSON_Files\\advisors.json", self.lecturerCreator.getLecturers())
        self.courseCreator = CreateCourse("JSON_Files\\courses.json", self.lecturerCreator.getLecturers())
        self.studentCreator = CreateStudent("JSON_Files\\Students\\", "JSON_Files\\student_json.txt",
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

