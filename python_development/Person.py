from abc import ABC, abstractmethod

from Syllabus import Syllabus


class Person(ABC):
    def __init__(self, first_name, last_name, person_id, password):
        self.first_name = first_name
        self.last_name = last_name
        self.password = password
        self.syllabus = Syllabus()
        self.person_id = person_id

    def is_equal(self, obj):
        if isinstance(obj, Person):
            return obj.get_person_id().get_id() == self.get_person_id().get_id()
        return False

    def get_person_id(self):
        return self.person_id

    def set_person_id(self, person_id):
        self.person_id = person_id

    @abstractmethod
    def login(self, user_info, persons):
        pass

    def get_first_name(self):
        return self.first_name

    def get_last_name(self):
        return self.last_name

    def get_password(self):
        return self.password

    def set_password(self, password):
        self.password = password

    @abstractmethod
    def create_syllabus(self, courses):
        # Implementation of create_syllabus method in Python
        pass

    def get_syllabus(self):
        return self.syllabus

    def set_syllabus(self, syllabus):
        self.syllabus = syllabus