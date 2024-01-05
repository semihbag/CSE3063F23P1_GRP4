class Password:
    def __init__(self, password):
        self.password = password

    def getPassword(self):
        return self.password

    def setPassword(self, password):
        self.password = password

    def compareCurrentPassword(self, enteredPassword):
        return enteredPassword == self.password

    def checkPasswordCond(self, newPassword):
        contains_upper_case = self.checkUpperCaseCond(newPassword)
        contains_lower_case = self.checkLowerCaseCond(newPassword)
        contains_number = self.checkNumberCaseCond(newPassword)
        contains_special_character = self.isContainsSpecialChar(newPassword)
        length_condition = self.lengthCond(newPassword)

        if length_condition and contains_special_character and contains_upper_case and contains_lower_case and contains_number:
            return True
        return False

    def isContainsSpecialChar(self, newPassword):
        if "." in newPassword or "_" in newPassword:
            return True
        else:
            print("The password must contain some special character such as '_' , '.'.")
            return False

    def lengthCond(self, newPassword):
        if 8 < len(newPassword) < 20:
            return True
        else:
            print("The password must be at least 8 and at most 20 characters.")
            return False

    def checkUpperCaseCond(self, newPassword):
        if any(char.isupper() for char in newPassword):
            return True
        print("The password must contain at least one uppercase letter.")
        return False

    def checkLowerCaseCond(self, newPassword):
        if any(char.islower() for char in newPassword):
            return True
        print("The password must contain at least one lowercase letter.")
        return False

    def checkNumberCaseCond(self, newPassword):
        if any(char.isdigit() for char in newPassword):
            return True
        print("The password must contain at least one number.")
        return False

    def equals(self, obj):
        if isinstance(obj, Password):
            return obj.getPassword() == self.getPassword()
        return False