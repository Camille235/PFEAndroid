package fr.eseo.dis.camille.pfeandroid.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Arthur on 20/12/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {

    String result;
    String api;
    String token;
    String error;


}
