import org.json.JSONException;

import userInterfacePackage.UserInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JSONException, IOException {
    
    	try {
    		UserInterface userInterface = new UserInterface();
            SystemClass system = new SystemClass(userInterface);
            system.run();
        
    	}
    	catch (Exception e) {
    		 System.err.println("Hata yakalandı: " + e.getMessage());
             e.printStackTrace();
    	}
    }
}