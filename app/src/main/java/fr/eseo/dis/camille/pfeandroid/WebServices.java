package fr.eseo.dis.camille.pfeandroid;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.bean.Login;

/**
 * Created by Arthur on 20/12/2017.
 */

public class WebServices {

    private static String retrieve(String URI) {
        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String test;
            while ((test = br.readLine()) != null){
                output.append(test);
            }
            conn.disconnect();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return output.substring(0);

    }

    public static Login login(String username, String password) throws LoginError{

        String json = retrieve("https://192.168.4.10/www/pfe/webservice.php?q=LOGON&username="+username+"&pass="+password);

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String,String> map = new HashMap<>();
        try {
            map = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
        }

        if(map.containsKey("error")) {
            throw new LoginError(map.get("error"));
        }

        Login log = null;

        try {
            log = mapper.readValue(json, Login.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

}
