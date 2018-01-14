package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by Jérome on 14/01/2018.
 */

@Dao
public interface PseudoJuryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPseudoJury(PseudoJury pseudoJury);
}
