from Person import Person
class Lecturer(Person):
    def __init__(self, FirstName,LastName , LecturerId, password):
        super().__init__(FirstName, LastName, LecturerId, password)

    def login(self, user_info, persons):
        username = user_info[0]
        password = user_info[1]
        for person in persons:
            if isinstance(person, Lecturer):
                lecturer = person
                if ("l" + str(lecturer.get_person_id().get_id()) == username and
                        lecturer.get_password().get_password() == password):
                    lecturer.create_syllabus(lecturer.get_given_courses())
                    return lecturer
        return None

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

