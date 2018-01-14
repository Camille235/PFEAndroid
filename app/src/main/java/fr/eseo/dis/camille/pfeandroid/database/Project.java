package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jérome on 14/01/2018.
 */

@Entity(tableName = "marks",
        primaryKeys = {"idProject"}
)
public class Project {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idProject")
    private int idProject;

    @NonNull
    @ColumnInfo(name = "titleProject")
    private String titleProject;

    @NonNull
    @ColumnInfo(name = "descriptionProject")
    private String descriptionProject;

    @NonNull
    @ColumnInfo(name = "posterProject")
    private byte[] posterProject;

    public Project(@NonNull int idProject, @NonNull String titleProject, @NonNull String descriptionProject, @NonNull byte[] posterProject) {
        this.idProject = idProject;
        this.titleProject = titleProject;
        this.descriptionProject = descriptionProject;
        this.posterProject = posterProject;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(@NonNull String titleProject) {
        this.titleProject = titleProject;
    }

    @NonNull
    public String getDescriptionProject() {
        return descriptionProject;
    }

    public void setDescriptionProject(@NonNull String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }

    @NonNull
    public byte[] getPosterProject() {
        return posterProject;
    }

    public void setPosterProject(@NonNull byte[] posterProject) {
        this.posterProject = posterProject;
    }
}
