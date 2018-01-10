package fr.eseo.dis.camille.pfeandroid.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Arthur on 20/12/2017.
 */

public class Login {

    String result;
    String api;
    String token;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
