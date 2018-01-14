package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jérome on 14/01/2018.
 */

@Entity(tableName = "marks",
        primaryKeys = {"idMark"},
        foreignKeys = {@ForeignKey(entity=PseudoJury.class, parentColumns = "idPseudoJury",childColumns = "idPseudoJury"),
            @ForeignKey(entity=Project.class, parentColumns = "idProject",childColumns = "idProject")
            }
        )
public class Mark {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMark")
    private int idMark;

    @NonNull
    @ColumnInfo(name = "valueMark")
    private int valueMark;

    @NonNull
    @ColumnInfo(name = "idPseudoJury")
    private int idPseudoJury;

    @NonNull
    @ColumnInfo(name = "idProject")
    private int idProject;

    public Mark(@NonNull int idMark, @NonNull int valueMark, @NonNull int idPseudoJury, @NonNull int idProject) {
        this.idMark = idMark;
        this.valueMark = valueMark;
        this.idProject = idProject;
        this.idPseudoJury = idPseudoJury;
    }

    @NonNull
    public int getIdMark() {
        return idMark;
    }

    public void setIdMark(@NonNull int idMark) {
        this.idMark = idMark;
    }

    @NonNull
    public int getValueMark() {
        return valueMark;
    }

    public void setValueMark(@NonNull int valueMark) {
        this.valueMark = valueMark;
    }

    @NonNull
    public int getIdPseudoJury() {
        return idPseudoJury;
    }

    public void setIdPseudoJury(@NonNull int idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

    @NonNull
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(@NonNull int idProject) {
        this.idProject = idProject;
    }
}
