package fr.eseo.dis.camille.pfeandroid.bean;

/**
 * Created by camil on 20/12/2017.
 */

public class Presentation {

    private int id;
    private int idProject;
    private String filePathPDF;

    public Presentation() {
        this.id = -1;
        this.idProject = -1;
        this.filePathPDF = "";
    }

    public Presentation(int id, int idProject, String filePathPDF) {
        this.id = id;
        this.idProject = idProject;
        this.filePathPDF = filePathPDF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getFilePathPDF() {
        return filePathPDF;
    }

    public void setFilePathPDF(String filePathPDF) {
        this.filePathPDF = filePathPDF;
    }

}
