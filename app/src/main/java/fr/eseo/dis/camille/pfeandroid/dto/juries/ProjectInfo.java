package fr.eseo.dis.camille.pfeandroid.dto.juries;

import fr.eseo.dis.camille.pfeandroid.dto.juries.Supervisor;

/**
 * Created by Arthur on 20/12/2017.
 */

public class ProjectInfo {

    private int projectId;
    private String title;
    private int confid;
    private boolean poster;
    private Supervisor supervisor;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public boolean isPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
