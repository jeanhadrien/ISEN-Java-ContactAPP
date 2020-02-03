package project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Dictionary;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

	private static JSONObject config;
	private static final String FILENAME = "config.json";
	private static final String PATH = "resources/";
	private static final String[] VALID_KEYS = {"Username","Password","ServerName","Database"};
	
	/*
	 * Saves config to PATHFILENAME
	 */
	public static void save() {

        try (FileWriter file = new FileWriter(PATH + FILENAME)) {
 
            file.write(config.toJSONString());
            file.flush();
 
        } catch (IOException e) {
        	App.print("Couldn't save config");
            e.printStackTrace();
        }
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

		Path mypath = App.path.resolve(PATH + FILENAME).toAbsolutePath();

		try (FileReader reader = new FileReader(mypath.toString())) {
			config = (JSONObject) parser.parse(reader);

		} catch (FileNotFoundException e) {
			App.print("Config file not found");
			e.printStackTrace();
		} catch (IOException e) {
			App.print("Error I/O");
			e.printStackTrace();
		} catch (ParseException e) {
			App.print("Error in json file");
			e.printStackTrace();
		}
	}
	
	public static Object getKey(String key) {
		return config.get(key);		
	}
	
	@SuppressWarnings("unchecked")
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
