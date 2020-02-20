import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

	private static JSONObject config;
	private static final String PATHTOFILE = "config.json";
	private static final String[] VALID_KEYS = {"Username","Password","ServerName","Database"};
	
	/*
	 * Saves config to PATHTOFILE
	 */
	public static void save() {
		/*
        try (FileWriter file = new FileWriter(PATH + FILENAME)) {
 
            file.write(config.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

		 */
	}
	
	/*
	 * Loads JSON config file from PATH/FILENAME
	 * and stores it as JSONObject in config.
	 */
	public static void load() {
		if( config != null) {
			config.clear();

		}
		
		JSONParser parser = new JSONParser();

		try (FileReader reader = new FileReader(FileManager.getResourceURL(PATHTOFILE).getPath())) {
			config = (JSONObject) parser.parse(reader);

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getKey(String key) {
		return config.get(key);		
	}

	public static void setKey(String key, Object value) {
		if (config.containsKey(key)){
			config.replace(key, value);
		}
		else {
			config.put(key, value);
		}
	}
	
	public static void test() {
		load();

	}
	
	
}
