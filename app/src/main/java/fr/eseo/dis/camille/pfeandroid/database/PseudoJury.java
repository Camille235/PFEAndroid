package fr.eseo.dis.camille.pfeandroid.database;

/**
 * Created by Jérome on 14/01/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pseudojurys"
)
public class PseudoJury {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idPseudoJury")
    private int idPseudoJury;

    @NonNull
    @ColumnInfo(name = "namePseudoJury")
    private String namePseudoJury;

    @NonNull
    @ColumnInfo(name = "passwordPseudoJury")
    private String passwordPseudoJury;

    public PseudoJury(@NonNull int idPseudoJury, @NonNull String namePseudoJury, @NonNull String passwordPseudoJury) {
        this.idPseudoJury = idPseudoJury;
        this.namePseudoJury = namePseudoJury;
        this.passwordPseudoJury = passwordPseudoJury;
    }

    @NonNull
    public int getIdPseudoJury() {
        return idPseudoJury;
    }

    public void setIdPseudoJury(@NonNull int idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

    @NonNull
    public String getNamePseudoJury() {
        return namePseudoJury;
    }

    public void setNamePseudoJury(@NonNull String namePseudoJury) {
        this.namePseudoJury = namePseudoJury;
    }

    @NonNull
    public String getPasswordPseudoJury() {
        return passwordPseudoJury;
    }

    public void setPasswordPseudoJury(@NonNull String passwordPseudoJury) {
        this.passwordPseudoJury = passwordPseudoJury;
    }
}
