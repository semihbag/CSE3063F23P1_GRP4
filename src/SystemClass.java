// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class SystemClass {
    private SystemDomain domain;


    public SystemClass() throws JSONException, IOException {
        domain = new SystemDomain();

    }


    public SystemDomain getDomain() {
        return domain;
    }

}

