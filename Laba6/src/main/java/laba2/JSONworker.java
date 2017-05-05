package laba2;

/**
 * Created by danil on 28.02.2017.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
public class JSONworker {

    public static void toJSON(FoodResidus fr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("some.json"), fr);
        System.out.println("json created!");
    }

    public static FoodResidus toJavaObject(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, FoodResidus.class);
    }
}
