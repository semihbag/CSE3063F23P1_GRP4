import json
import logging

from iter3.python_development.PersonObject.Lecturer import Lecturer
from iter3.python_development.PersonObject.Id import Id
from iter3.python_development.PersonObject.Password import Password


class CreateLecturer:
    def __init__(self, fileName):
        self.lecturers = []
        self.fileName = fileName
        self.createLecturers()

    def createLecturers(self):
        try:
            with open(self.fileName, 'r') as file:
                data = json.load(file)
                lecturers_data = data.get('lecturers', [])
                for lecturer_info in lecturers_data:
                    name = lecturer_info['name']
                    surname = lecturer_info['surname']
                    lecturer_id = lecturer_info['lecturerId']
                    password = lecturer_info['password']
                    lecturer = Lecturer(name, surname, Id(lecturer_id), Password(password))
                    self.lecturers.append(lecturer)
        except (json.JSONDecodeError, FileNotFoundError) as e:
            logging.exception(f"Error in lecturer data in lecturers.json file: {e}")
            exit(0)

    def getLecturers(self):
        return self.lecturers
