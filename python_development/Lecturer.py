from Person import Person
class Lecturer(Person):
    def __init__(self, FirstName,LastName , LecturerId, password):
        super().__init__(FirstName, LastName, LecturerId, password)

    def selectCourse(self, index):
        index = index - 1
        currentCourse = self.givenCourses[index]
        self.setSelectedCourse(currentCourse)

    def createSyllabus(self, givenCourses):
        self.getSyllabus().fillSyllabus(givenCourses)

    def getLecturerId(self):
        return self.lecturerId

    def getGivenCourses(self):
        return self.givenCourses

    def setGivenCourses(self, givenCourses):
        self.givenCourses = givenCourses

    def getSelectedCourse(self):
        return self.selectedCourse

    def setSelectedCourse(self, selectedCourse):
        self.selectedCourse = selectedCourse

