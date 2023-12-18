package Creator;

import PersonObject.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class CreateAdvisor {
    private ArrayList<Advisor> advisors = new ArrayList<>();
    private String fileName;

    public CreateAdvisor(String fileName,ArrayList<Lecturer> lecturers ) throws JSONException, IOException{
        this.fileName = fileName;
        createAdvisors(lecturers);
    }

    private void createAdvisors(ArrayList<Lecturer> lecturers){
        try {
            String content = new String(Files.readAllBytes(Path.of(fileName)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray advisorJSON = jsonObject.getJSONArray("advisors");
            for (int i = 0; i < advisorJSON.length(); i++) {
                String name = advisorJSON.getJSONObject(i).getString("name");
                String surname = advisorJSON.getJSONObject(i).getString("surname");
                String advisorId = advisorJSON.getJSONObject(i).getString("advisorId");
                String password = advisorJSON.getJSONObject(i).getString("password");
                Advisor advisor = new Advisor(name, surname, new Id(advisorId), new Password(password));
                advisors.add(advisor);
                lecturers.add(advisor);
            }
        }
        catch (JSONException | IOException ignored){
            System.out.println("An error occurred in the advisors JSON file. Please ensure that the file is created in the correct format and fix any errors.");
            System.exit(0);
        }
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
