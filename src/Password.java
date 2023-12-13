public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean compareCurrentPassword(String enteredPassword){
        return enteredPassword.equals(password);
    }

    public boolean checkPasswordCond(String newPassword){
		boolean containsUpperCase = checkUpperCaseCond(newPassword),
        containsLowerCase =checkUpperCaseCond(newPassword),
	    containsNumber = checkUpperCaseCond(newPassword),
        continsSpeacialCharacter=isContains(newPassword),
        lenght=lenghtCond(newPassword),
        valid=false;  
		if (lenght && continsSpeacialCharacter && containsUpperCase && containsLowerCase && containsNumber){
				valid=true;
			}
         return valid;
	}

    public boolean isContains(String newPassword){
        boolean valid=false;
        if(newPassword.contains(".") || newPassword.contains("_")){
            valid=true;
        }
        return valid;
    }

    public boolean lenghtCond(String newPassword){
        boolean valid=false;
        if(newPassword.length() > 8 && newPassword.length() < 20){
            valid=true;
        }
        return valid;
    }

    public boolean checkUpperCaseCond(String newPassword){
        boolean valid=false;
        for (char c : newPassword.toCharArray()) {
            if (Character.isUpperCase(c)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public boolean checkLowerCaseCond(String newPassword){
        boolean valid=false;
         for (char c : newPassword.toCharArray()) {
            if (Character.isLowerCase(c)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public boolean checkDigitCaseCond(String newPassword){
        boolean valid=false;
         for (char c : newPassword.toCharArray()) {
            if (Character.isDigit(c)) {
                valid = true;
                break;
            }
        }
        return valid;
    }
    
}