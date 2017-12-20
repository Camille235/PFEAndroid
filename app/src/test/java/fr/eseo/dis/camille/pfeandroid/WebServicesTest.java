package fr.eseo.dis.camille.pfeandroid;

import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.bean.Login;


/**
 * Created by Arthur on 20/12/2017.
 */
@MediumTest
public class WebServicesTest {

    @Test(expected = LoginError.class)
    public void loginTestWrong() throws Exception {
        Login log = WebServices.login(null, "toto","toto");
    }

}