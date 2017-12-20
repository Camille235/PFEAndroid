package fr.eseo.dis.camille.pfeandroid.bean;

/**
 * Created by Jérome on 20/12/2017.
 */

public class Supervisor {
    private int idProject;
    private int idSupervisor;

    public Supervisor() {
        this.idProject = -1;
        this.idSupervisor = -1;
    }

    public Supervisor(int idProject, int idSupervisor) {
        this.idProject = idProject;
        this.idSupervisor = idSupervisor;
    }



    public int getIdProject() {

        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }
}
