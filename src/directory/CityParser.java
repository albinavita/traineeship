package directory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CityParser {

    public static List<City> readFile(String fileName) throws Exception {
        List<City> result = new ArrayList<>();
        Path path = Paths.get(fileName);
        try {
            Scanner scanner = new Scanner(path);
            while ( scanner.hasNext() ){
                String str = scanner.useDelimiter("\r\n").nextLine();
                String[] arrays = str.split(";", -1);
                if(arrays.length < 6){
                    throw new Exception("Invalid file format");
                }
                result.add(new City(Integer.parseInt(arrays[0]), arrays[1], arrays[2], arrays[3], Integer.parseInt(arrays[4]), arrays[5]));
            }
            scanner.close();
        } catch (IOException e) {
            throw e;
        }
        return result;
    }

}
