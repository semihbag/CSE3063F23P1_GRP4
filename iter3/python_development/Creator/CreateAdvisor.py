import json
import logging

from iter3.python_development.PersonObject.Advisor import Advisor
from iter3.python_development.PersonObject.Id import Id
from iter3.python_development.PersonObject.Password import Password


class CreateAdvisor:
    def __init__(self, file_name, lecturers):
        self.advisors = []
        self.fileName = file_name
        self.createAdvisors(lecturers)

    def createAdvisors(self, lecturers):
        try:
            with open(self.fileName, 'r') as file:
                content = file.read()
                json_object = json.loads(content)
                advisor_json = json_object['advisors']
                for advisor_data in advisor_json:
                    name = advisor_data['name']
                    surname = advisor_data['surname']
                    advisor_id = advisor_data['advisorId']
                    password = advisor_data['password']
                    advisor = Advisor(name, surname, Id(advisor_id), Password(password))
                    self.advisors.append(advisor)
                    lecturers.append(advisor)
        except (json.JSONDecodeError, FileNotFoundError, IOError) as e:
            logging.exception(f"Error in advisor date in advisors.json file: {e}")
            exit(0)

    def getAdvisors(self):
        return self.advisors

    def getFileName(self):
        return self.fileName

    def setFileName(self, file_name):
        self.fileName = file_name
