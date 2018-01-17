package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Jérome on 14/01/2018.
 */

@Dao
public interface PseudoJuryDao {

    @Query("SELECT * FROM pseudojurys")
    List<PseudoJury> loadAllPseudoJurys();

    @Query("SELECT * FROM pseudojurys where namePseudoJury=:pseudo and passwordPseudoJury=:password")
    List<PseudoJury> loadOnePseudoJurys(String pseudo, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPseudoJury(PseudoJury pseudoJury);
}
