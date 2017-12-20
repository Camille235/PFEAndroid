package fr.eseo.dis.camille.pfeandroid.webServiceBean;

import fr.eseo.dis.camille.pfeandroid.bean.Jury;

/**
 * Created by Arthur on 20/12/2017.
 */

public class ListJuries {

    private String api;
    private String result;
    private Jury[] juries;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Jury[] getJuries() {
        return juries;
    }

    public void setJuries(Jury[] juries) {
        this.juries = juries;
    }
}
