package Creator;

import PersonObject.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CreateLecturer {
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private String fileName;

    public CreateLecturer(String fileName) {
        this.fileName = fileName;
        createLecturers();
    }
    public void createLecturers() {
        try {
            String content = new String(Files.readAllBytes(Path.of(fileName)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray lecturerJSON = jsonObject.getJSONArray("lecturers");
            for (int i = 0; i < lecturerJSON.length(); i++) {
                String name = lecturerJSON.getJSONObject(i).getString("name");
                String surname = lecturerJSON.getJSONObject(i).getString("surname");
                String lecturerId = lecturerJSON.getJSONObject(i).getString("lecturerId");
                String password = lecturerJSON.getJSONObject(i).getString("password");
                lecturers.add(new Lecturer(name, surname, new Id(lecturerId), new Password(password)));
            }
        }
        catch (JSONException | IOException ignored){
            System.err.println("An error occurred in the lecturers JSON file. Please ensure that the file is created in the correct format and fix any errors.");
            System.exit(0);
        }
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }
}

