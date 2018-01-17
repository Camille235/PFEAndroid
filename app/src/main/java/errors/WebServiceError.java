package errors;

import fr.eseo.dis.camille.pfeandroid.WebServices;

/**
 * Created by Arthur on 17/01/2018.
 */

public class WebServiceError extends Error {

    public WebServiceError(String error){
        super(error);
    }
}
