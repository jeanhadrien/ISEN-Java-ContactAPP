import java.net.URL;

public class FileManager{

    public static URL getResource(String pathToFile){
        return FileManager.class.getClassLoader().getResource(pathToFile);
    }
}
