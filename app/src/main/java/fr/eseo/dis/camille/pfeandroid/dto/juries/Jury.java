package fr.eseo.dis.camille.pfeandroid.dto.juries;

/**
 * Created by Arthur on 20/12/2017.
 */

public class Jury {

    private int idJury;
    private String Date;
    private Info info;

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
