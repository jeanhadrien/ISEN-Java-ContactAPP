import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Config {


    /**
     * Load config from json file.
     * TODO : Save the config if the SQLConfigWindow input results in successful SQL connection.
     */

    private static final String PATHTOFILE = "config.json";
    private static JSONObject config;

    public static void load() {
        if (config != null) { config.clear(); }

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(FileManager.getResourceURL(PATHTOFILE).getPath())) {
            config = (JSONObject) parser.parse(reader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
