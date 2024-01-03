class Password:
    def __init__(self, password):
        self.password = password

    def get_password(self):
        return self.password

    def set_password(self, password):
        self.password = password

    def compare_current_password(self, entered_password):
        return entered_password == self.password

    def check_password_cond(self, new_password):
        contains_upper_case = self.check_upper_case_cond(new_password)
        contains_lower_case = self.check_lower_case_cond(new_password)
        contains_number = self.check_number_case_cond(new_password)
        contains_special_character = self.is_contains_special_char(new_password)
        length_condition = self.length_cond(new_password)

        if length_condition and contains_special_character and contains_upper_case and contains_lower_case and contains_number:
            return True
        return False

    def is_contains_special_char(self, new_password):
        if "." in new_password or "_" in new_password:
            return True
        else:
            print("The password must contain some special character such as '_' , '.'.")
            return False

    def length_cond(self, new_password):
        if 8 < len(new_password) < 20:
            return True
        else:
            print("The password must be at least 8 and at most 20 characters.")
            return False

    def check_upper_case_cond(self, new_password):
        if any(char.isupper() for char in new_password):
            return True
        print("The password must contain at least one uppercase letter.")
        return False

    def check_lower_case_cond(self, new_password):
        if any(char.islower() for char in new_password):
            return True
        print("The password must contain at least one lowercase letter.")
        return False

    def check_number_case_cond(self, new_password):
        if any(char.isdigit() for char in new_password):
            return True
        print("The password must contain at least one number.")
        return False

    def is_equal(self, obj):
        if isinstance(obj, Password):
            return obj.get_password() == self.get_password()
        return False