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

    public boolean comparePreviousPassword(String newPassword){

        return newPassword.equals(password);
    }

    public boolean checkPasswordCond(String newPassword){
		boolean containsUpperCase = false, containsLowerCase = false;
		boolean containsNumber = false;
        boolean valid=false;

        for (char c : newPassword.toCharArray()) {
            if (Character.isDigit(c)) {
                containsNumber = true;
                break;
            }
        }
        for (char c : newPassword.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUpperCase = true;
                break;
            }
        }
        for (char c : newPassword.toCharArray()) {
            if (Character.isLowerCase(c)) {
                containsLowerCase = true;
                break;
            }
        }
		if ((newPassword.length() > 8 || newPassword.length() < 20)&&
			(newPassword.contains(".") || newPassword.contains("_"))&&
			containsUpperCase && containsLowerCase && containsNumber){
				valid=true;
			}
			
         return valid;
	}
}