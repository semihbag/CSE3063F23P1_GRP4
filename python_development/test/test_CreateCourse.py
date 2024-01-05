import unittest
from python_development.Creator.CreateCourse import CreateCourse
from python_development.PersonObject.Lecturer import Lecturer
from python_development.PersonObject.Id import Id
from python_development.PersonObject.Password import Password
from python_development.CourseObject.Day import Day
from python_development.CourseObject.Hour import Hour
import json
import os

class CreateCourseTest(unittest.TestCase):
    def setUp(self):
        # Create a Lecturer instance for testing
        lecturer = Lecturer("Mahmut", "Kertil", Id("1303001"), Password("12345"))  # Replace with actual lecturer ID

        # Create temporary file content for testing
        file_content = {
            "courses": [
                {
                    "hour": [["13.00"]],
                    "quota": 15,
                    "name": "Calculus I",
                    "studentList": [],
                    "prerequisite": [],
                    "lecturer": "1303001",
                    "term": 1,
                    "id": "MATH1001",
                    "type": "mandatory",
                    "credit": 6,
                    "isSession": False,
                    "day": ["TUESDAY"]
                }
            ]
        }

        # Create temporary file with mock content
        file_path = "temp.json"
        with open(file_path, 'w') as file:
            json.dump(file_content, file)

        # Create CreateCourse instance and test
        lecturers = [lecturer]
        self.create_course = CreateCourse(file_path, lecturers)
        # Clean up temporary file
        os.remove(file_path)

    def test_create_course(self):
        courses = self.create_course.getCourses()
        # Test if courses are created and added properly
        self.assertIsNotNone(courses)
        self.assertEqual(1, len(courses))
        course = courses[0]
        self.assertEqual("MATH1001", course.getCourseId().getId())
        self.assertEqual("Calculus I", course.getCourseName())
        self.assertEqual(1, course.getTerm())
        self.assertEqual(15, course.getQuota())
        self.assertEqual(6, course.getCredit())
        self.assertEqual(Day.TUESDAY, course.getCourseSchedules()[0].getCourseDay())
        self.assertEqual(Hour.H_13_00_13_50, course.getCourseSchedules()[0].getCourseHours()[0])

    def test_json_arr_to_str_arr(self):
        jsonArray = ["Item1", "Item2"]

        result = self.create_course.jsonArrToStrArr(jsonArray)
        self.assertListEqual(["Item1", "Item2"], result)

if __name__ == '__main__':
    unittest.main()
