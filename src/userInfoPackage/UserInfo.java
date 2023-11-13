package userInfoPackage;

public class UserInfo {
	private String username;
	private String password;
	
	public UserInfo(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	/// GETTER - SETTER METHODS
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
