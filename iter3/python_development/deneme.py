class SuperClass:
    def isequal(self):
        print("SuperClass isequal")

class SubClass(SuperClass):
    def isequal(self):
        print("SubClass isequal")

    def sub_isequal(self):
        print("SubClass specific isequal")

# Alt sınıftan bir nesne oluştur
sub_obj = SubClass()

# Alt sınıfa özgü isequal metodunu çağır
sub_obj.isequal()

# Üst sınıfın isequal metodunu çağır
sub_obj.super().isequal()

# Alt sınıfa özgü isequal metodunu çağır
sub_obj.sub_isequal()
