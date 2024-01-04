from python_development.Creator.CreateLecturer import CreateLecturer
import json
import unittest

class CreateLecturerTest(unittest.TestCase):
    def test_create_lecturers(self):
        file_name = "JSON_files\\lecturers.json"
        lecturer_creator = CreateLecturer(file_name)
        lecturers = lecturer_creator.getLecturers()
        self.assertIsNotNone(lecturer_creator)
        lecturers = lecturer_creator.getLecturers()
        self.assertIsNotNone(lecturers)
        #self.assertEqual(54, len(lecturers))
        firstLecturer = lecturers[0]
        self.assertEqual("Ayse", firstLecturer.getFirstName())
        self.assertEqual("Yilmaz", firstLecturer.getLastName())
        self.assertEqual("1505001", firstLecturer.getPersonId().getId())
        self.assertEqual("Ataturk_81", firstLecturer.getPassword().getPassword())

if __name__ == '__main__':
    unittest.main()
