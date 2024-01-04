from Person import Person
class Lecturer(Person):
    def __init__(self, FirstName,LastName , LecturerId, password):
        super().__init__(FirstName, LastName, LecturerId, password)
        self.givenCourses = []
        self.selectedCourse = None

    def login(self, user_info, persons):
        username = user_info[0]
        password = user_info[1]
        for person in persons:
            if isinstance(person, Lecturer):
                lecturer = person
                if ("l" + str(lecturer.getPersonId().getId()) == username and
                        lecturer.getPassword().getPassword() == password):
                    lecturer.createSyllabus(lecturer.getGivenCourses())
                    return lecturer
        return None

    def selectCourse(self, index):
        index = index - 1
        currentCourse = self.givenCourses[index]
        self.setSelectedCourse(currentCourse)

    def createSyllabus(self, givenCourses):
        self.getSyllabus().fillSyllabus(givenCourses)


    def getGivenCourses(self):
        return self.givenCourses

    def setGivenCourses(self, givenCourses):
        self.givenCourses = givenCourses

    def getSelectedCourse(self):
        return self.selectedCourse

    def setSelectedCourse(self, selectedCourse):
        self.selectedCourse = selectedCourse

