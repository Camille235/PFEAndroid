package fr.eseo.dis.camille.pfeandroid.webServiceBean;

import fr.eseo.dis.camille.pfeandroid.bean.Project;

/**
 * Created by Arthur on 20/12/2017.
 */

public class JuryInfo {

    private String result;
    private String api;
    private Project[] projects;

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

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }
}
