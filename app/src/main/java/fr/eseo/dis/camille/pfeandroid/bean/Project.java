package fr.eseo.dis.camille.pfeandroid.bean;

/**
 * Created by camil on 20/12/2017.
 */

public class Project {

    private int id;
    private String title;
    private String description;
    private int confidentiality;
    private String confidentialityDescription;

    public Project(){
        this.id = -1;
        this.title = "";
        this.description = "";
        this.confidentiality = -1;
        this.confidentialityDescription = "";
    }
    public Project(int id, String title, String description, int confidentiality, String confidentialityDescription){
        this.id = id;
        this.title = title;
        this.description = description;
        this.confidentiality = confidentiality;
        this.confidentialityDescription = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(int confidentiality) {
        this.confidentiality = confidentiality;
    }

    public String getConfidentialityDescription() {
        return confidentialityDescription;
    }

    public void setConfidentialityDescription(String confidentialityDescription) {
        this.confidentialityDescription = confidentialityDescription;
    }
}
