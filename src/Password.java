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

    public boolean comparePreviousPassword(Password newPassword){
        return newPassword.equals(this.password);
    }

    public boolean checkPasswordCond(Password password){
		boolean containsUpperCase = false, containsLowerCase = false;
		boolean containsNumber = false;
        boolean valid=false;

        for (char c : password.getPassword().toCharArray()) {
            if (Character.isDigit(c)) {
                containsNumber = true;
                break;
            }
        }
        for (char c : password.getPassword().toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
                break;
            }
        }
        for (char c : password.getPassword().toCharArray()) {
            if (Character.isLowerCase(c)) {
                containsLowerCase = true;
                break;
            }
        }
		if ((password.getPassword().length() > 8 || password.getPassword().length() < 20)&&
			(password.getPassword().contains(".") || password.getPassword().contains("_"))&&
			containsUpperCase && containsLowerCase && containsNumber){
				valid=true;
			}
			
         return valid;
	}
}