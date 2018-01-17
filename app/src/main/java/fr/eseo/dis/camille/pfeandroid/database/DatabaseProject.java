package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jérome on 14/01/2018.
 */

@Entity(tableName = "projects"
)
public class DatabaseProject {
    @PrimaryKey (autoGenerate = true)
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
    private String posterProject;

    @NonNull
    @ColumnInfo(name = "idPseudoJury")
    private int idPseudoJury;

    public DatabaseProject(@NonNull String titleProject, @NonNull String descriptionProject, @NonNull String posterProject, @NonNull int idPseudoJury) {
        this.titleProject = titleProject;
        this.descriptionProject = descriptionProject;
        this.posterProject = posterProject;
        this.idPseudoJury = idPseudoJury;
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
    public String getPosterProject() {
        return posterProject;
    }

    public void setPosterProject(@NonNull String posterProject) {
        this.posterProject = posterProject;
    }

    @NonNull
    public int getIdPseudoJury() {
        return idPseudoJury;
    }

    public void setIdPseudoJury(@NonNull int idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

}
