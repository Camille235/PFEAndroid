package fr.eseo.dis.camille.pfeandroid.dto.juries;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Arthur on 20/12/2017.
 */

public class Student{




    String userId;
    String forename;
    String surname;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
