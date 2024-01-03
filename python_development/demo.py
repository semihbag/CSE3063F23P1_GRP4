from Person import Person
from student import Student
from lecturer import Lecturer

# Kullanım örneği


try:
    person1 = Person("Ali", "Yılmaz")
except TypeError as e:
    print("Hata:", e)

student1 = Student("Ahmet", "Yılmaz", "12345")
student1.tanit()

lecturer1 = Lecturer("Ayşe", "Demir", "Doçent")
lecturer1.tanit()