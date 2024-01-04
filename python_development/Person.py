from abc import ABC, abstractmethod

from python_development.Syllabus import Syllabus


class Person(ABC):
    def __init__(self, firstName, lastName, personId, password):
        self.firstName = firstName
        self.lastName = lastName
        self.password = password
        self.syllabus = Syllabus()
        self.personId = personId

    def equals(self, obj):
        if isinstance(obj, Person):
            return obj.getPersonId().getId() == self.getPersonId().getId()
        return False

    def getPersonId(self):
        return self.personId

    def setPersonId(self, personId):
        self.personId = personId

    @abstractmethod
    def login(self, user_info, persons):
        pass

    def getFirstName(self):
        return self.firstName

    def getLastName(self):
        return self.lastName

    def getPassword(self):
        return self.password

    def setPassword(self, password):
        self.password = password

    @abstractmethod
    def createSyllabus(self, courses):
        # Implementation of create_syllabus method in Python
        pass

    def getSyllabus(self):
        return self.syllabus

    def setSyllabus(self, syllabus):
        self.syllabus = syllabus
