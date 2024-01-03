import json

from python_development.Lecturer import Lecturer
from python_development.Id import Id
from python_development.Password import Password

class CreateLecturer:
    def __init__(self, fileName):
        self.lecturers = []
        self.fileName = fileName
        self.create_lecturers()

    def create_lecturers(self):
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
            print(f"An error occurred in the lecturers JSON file: {e}. Please ensure the file is in the correct format.")

    def getLecturers(self):
        return self.lecturers
