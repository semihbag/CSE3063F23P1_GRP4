import os

from iter3.python_development.Creator.CreateLecturer import CreateLecturer
import unittest


class CreateLecturerTest(unittest.TestCase):

    def setUp(self):
        self.createLecturer = None
        self.lecturers = []

        fileContent = '''{
            "lecturers": [
                {
                    "password": "test123",
                    "lecturerId": "100",
                    "surname": "B",                            
                    "name": "A"
                },
                {
                    "password": "test123",
                    "lecturerId": "101",
                    "surname": "D",
                    "name": "C"                        
                },
                {
                    "password": "test123",
                    "lecturerId": "102",
                    "surname": "F",
                    "name": "E"
                }                    ]
        }'''

        with open("lecturersTest.json", 'w') as file:
            file.write(fileContent)

        self.createLecturer = CreateLecturer("lecturersTest.json")

        os.remove("lecturersTest.json")

    def test_create_lecturers(self):
        lecturers = self.createLecturer.getLecturers()
        self.assertEqual(3, len(lecturers))
        self.assertEqual(lecturers[0].getPersonId().getId(), "100")
        self.assertEqual(lecturers[1].getPersonId().getId(), "101")
        self.assertEqual(lecturers[2].getPersonId().getId(), "102")
        self.assertEqual(lecturers[0].getFirstName(), "A")
        self.assertEqual(lecturers[1].getFirstName(), "C")
        self.assertEqual(lecturers[2].getFirstName(), "E")


if __name__ == '__main__':
    unittest.main()
