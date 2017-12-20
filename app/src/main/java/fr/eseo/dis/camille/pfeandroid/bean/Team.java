package fr.eseo.dis.camille.pfeandroid.bean;

/**
 * Created by Jérome on 20/12/2017.
 */

public class Team {

    private int idProject;
    private int idMember;

    public Team() {
        this.idProject = -1;
        this.idMember = -1;
    }

    public Team(int idProject, int idMember) {
        this.idProject = idProject;
        this.idMember = idMember;
    }


    public int getIdProject() {

        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }
}
