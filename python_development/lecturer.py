from Person import Person
class Lecturer(Person):
    def __init__(self, ad, soyad, unvan):
        super().__init__(ad, soyad)  # Person sınıfının __init__ metodu çağrılıyor
        self._unvan = unvan

    def get_unvan(self):
        return self._unvan

    def set_unvan(self, yeni_unvan):
        self._unvan = yeni_unvan

