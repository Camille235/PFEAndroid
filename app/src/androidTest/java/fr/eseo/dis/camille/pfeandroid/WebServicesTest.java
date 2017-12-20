package fr.eseo.dis.camille.pfeandroid;

import org.junit.Test;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.bean.Login;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Arthur on 20/12/2017.
 */
public class WebServicesTest {

    @Test(expected = LoginError.class)
    public void loginTestWrong() throws Exception {
        Login log = WebServices.login("toto","toto");
    }

}