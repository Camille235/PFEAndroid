package fr.eseo.dis.camille.pfeandroid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Arthur on 10/01/2018.
 */

public class Tool {

    public static String parse(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(o);
        return json;
    }

    public static Object unparse(String s, JavaType j) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(s,j);
        return o;
    }
}
