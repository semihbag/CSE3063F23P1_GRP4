import unittest
import os

from iter3.python_development.PersonObject.Lecturer import Lecturer
from iter3.python_development.Creator.CreateAdvisor import CreateAdvisor


class TestCreateAdvisor(unittest.TestCase):
    def setUp(self):
        self.createAdvisor = None
        self.lecturers = []

        # Initialize necessary objects for testing
        # Provide a sample JSON file for testing
        fileContent = '''{
            "advisors": [
                {
                    "password": "ruby99",
                    "advisorId": "1501001",
                    "surname": "Corut Ergin",
                    "name": "Fatma"
                },
                {
                    "password": "Deneme_123",
                    "advisorId": "1501002",
                    "surname": "Agaoglu",
                    "name": "Mustafa"
                },
                {
                    "password": "Abo_12345",
                    "advisorId": "1501005",
                    "surname": "Boz",
                    "name": "Betul"
                }
            ]
        }'''


        # Write content to a temporary file
        with open("advisorsTest.json", 'w') as file:
            file.write(fileContent)

        self.createAdvisor = CreateAdvisor("advisorsTest.json", self.lecturers)

        # Delete the temporary file
        os.remove("advisorsTest.json")

    def test_create_advisors(self):
        advisors = self.createAdvisor.getAdvisors()

        self.assertEqual(3, len(advisors))
        self.assertEqual(3, len(self.lecturers))
        self.assertTrue(isinstance(self.lecturers[0], Lecturer))


if __name__ == '__main__':
    unittest.main()
