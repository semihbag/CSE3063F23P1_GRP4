import unittest

from python_development.Password import Password


class TestPassword(unittest.TestCase):
    def test_compare_current_password(self):
        password_obj = Password("Test123")

        self.assertTrue(password_obj.compareCurrentPassword("Test123"))
        self.assertFalse(password_obj.compareCurrentPassword("WrongPassword"))

    def test_check_password_cond(self):
        password_obj = Password("")

        self.assertFalse(password_obj.checkPasswordCond("short"))
        self.assertFalse(password_obj.checkPasswordCond("nouppercase"))
        self.assertFalse(password_obj.checkPasswordCond("NOLOWERCASE"))
        self.assertFalse(password_obj.checkPasswordCond("NoSpecialCharacter"))
        self.assertFalse(password_obj.checkPasswordCond("NoDigit1"))

        self.assertFalse(password_obj.checkPasswordCond("LongPasswordWithoutSpecialCharacterAndDigit"))

        self.assertTrue(password_obj.checkPasswordCond("ValidPassword1."))
        self.assertTrue(password_obj.checkPasswordCond("AnotherValidP_sord2"))

    def test_is_contains_special_char(self):
        password_obj = Password("")

        self.assertFalse(password_obj.isContainsSpecialChar("NoSpecialCharacter"))
        self.assertTrue(password_obj.isContainsSpecialChar("Contains_Underscore"))
        self.assertTrue(password_obj.isContainsSpecialChar("Contains.Dot"))

    def test_length_cond(self):
        password_obj = Password("")

        self.assertFalse(password_obj.lengthCond("Short"))
        self.assertFalse(password_obj.lengthCond("ThisIsAReallyLongPasswordThatExceedsTheMaximumLength"))

        self.assertTrue(password_obj.lengthCond("ValidLength1!"))

    def test_check_uppercase_cond(self):
        password_obj = Password("")

        self.assertFalse(password_obj.checkUpperCaseCond("nouppercase"))
        self.assertTrue(password_obj.checkUpperCaseCond("ContainsUpperCase"))

    def test_check_lowercase_cond(self):
        password_obj = Password("")

        self.assertFalse(password_obj.checkLowerCaseCond("NOLOWERCASE"))
        self.assertTrue(password_obj.checkLowerCaseCond("ContainsLowerCase"))

    def test_check_digit_case_cond(self):
        password_obj = Password("")

        self.assertFalse(password_obj.checkNumberCaseCond("NoDigit"))
        self.assertTrue(password_obj.checkNumberCaseCond("ContainsDigit1"))

if __name__ == '__main__':
    unittest.main()
