package UnitTests;

import PersonObject.*;
import Creator.*;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAdvisorTest {
    private CreateAdvisor createAdvisor;
    private ArrayList<Lecturer> lecturers;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize necessary objects for testing
        lecturers = new ArrayList<>(); // Initialize the list of lecturers
        // Provide a sample JSON file for testing
        String fileContent="{\"advisors\": [\n" +
                "    {\n" +
                "        \"password\": \"ruby99\",\n" +
                "        \"advisorId\": \"1501001\",\n" +
                "        \"surname\": \"Corut Ergin\",\n" +
                "        \"name\": \"Fatma\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"password\": \"Deneme_123\",\n" +
                "        \"advisorId\": \"1501002\",\n" +
                "        \"surname\": \"Agaoglu\",\n" +
                "        \"name\": \"Mustafa\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"password\": \"Abo_12345\",\n" +
                "        \"advisorId\": \"1501005\",\n" +
                "        \"surname\": \"Boz\",\n" +
                "        \"name\": \"Betul\"\n" +
                "    }\n" +
                "]}";
        String fileName = "src\\UnitTests\\advisorsTest.json";
        File file = new File(fileName);
        Path tempFile = Path.of(fileName);
        Files.write(tempFile, fileContent.getBytes());

        try {
            createAdvisor = new CreateAdvisor(fileName, lecturers);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGetFileName() {
        assertEquals("src\\UnitTests\\advisorsTest.json", createAdvisor.getFileName());
    }

    @Test
    void testSetFileName(){
        createAdvisor.setFileName("src\\UnitTests\\new_advisorsTest.json");
        assertEquals("src\\UnitTests\\new_advisorsTest.json", createAdvisor.getFileName());
    }

    @Test
    void testCreateAdvisors() throws JSONException, IOException {
        ArrayList<Advisor> advisors = createAdvisor.getAdvisors();

        assertEquals(3, advisors.size());

        assertEquals(3, lecturers.size());
        assertTrue(lecturers.get(0) instanceof Advisor);
    }
}

