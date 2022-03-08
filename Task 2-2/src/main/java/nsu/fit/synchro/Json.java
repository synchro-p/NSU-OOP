package nsu.fit.synchro;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class Json {
    public Info read(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Info info = null;
        try {
            info = mapper.readValue(Paths.get(filename).toFile(), Info.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
}
