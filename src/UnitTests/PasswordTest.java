package UnitTests;

import static org.junit.Assert.*;
import org.junit.Test;

import PersonObject.Password;

public class PasswordTest {

    @Test
    public void testCompareCurrentPassword() {
        Password passwordObj = new Password("Test123");

        assertTrue(passwordObj.compareCurrentPassword("Test123"));
        assertFalse(passwordObj.compareCurrentPassword("WrongPassword"));
    }

    @Test
    public void testCheckPasswordCond() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.checkPasswordCond("short"));
        assertFalse(passwordObj.checkPasswordCond("nouppercase"));
        assertFalse(passwordObj.checkPasswordCond("NOLOWERCASE"));
        assertFalse(passwordObj.checkPasswordCond("NoSpecialCharacter"));
        assertFalse(passwordObj.checkPasswordCond("NoDigit1"));

        assertFalse(passwordObj.checkPasswordCond("LongPasswordWithoutSpecialCharacterAndDigit"));

        assertTrue(passwordObj.checkPasswordCond("ValidPassword1."));
        assertTrue(passwordObj.checkPasswordCond("AnotherValidP_sord2"));
    }

    @Test
    public void testIsContainsSpeacialChar() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.isContainsSpeacialChar("NoSpecialCharacter"));
        assertTrue(passwordObj.isContainsSpeacialChar("Contains_Underscore"));
        assertTrue(passwordObj.isContainsSpeacialChar("Contains.Dot"));
    }

    @Test
    public void testLengthCond() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.lenghtCond("Short"));
        assertFalse(passwordObj.lenghtCond("ThisIsAReallyLongPasswordThatExceedsTheMaximumLength"));

        assertTrue(passwordObj.lenghtCond("ValidLength1!"));
    }

    @Test
    public void testCheckUpperCaseCond() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.checkUpperCaseCond("nouppercase"));
        assertTrue(passwordObj.checkUpperCaseCond("ContainsUpperCase"));
    }

    @Test
    public void testCheckLowerCaseCond() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.checkLowerCaseCond("NOLOWERCASE"));
        assertTrue(passwordObj.checkLowerCaseCond("ContainsLowerCase"));
    }

    @Test
    public void testCheckDigitCaseCond() {
        Password passwordObj = new Password("");

        assertFalse(passwordObj.checkNumberCaseCond("NoDigit"));
        assertTrue(passwordObj.checkNumberCaseCond("ContainsDigit1"));
    }
}

