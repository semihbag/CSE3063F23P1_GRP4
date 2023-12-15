import System.SystemClass;
import UserInterface.UserInterface;

public class Main {
    public static void main(String[] args) {
    	try {
    		UserInterface userInterface = new UserInterface();
            SystemClass system = new SystemClass(userInterface);
            system.run();
        
    	}
    	catch (Exception e) {
    		 System.err.println("Error found: " + e.getMessage());
             e.printStackTrace();
    	}
    }
}