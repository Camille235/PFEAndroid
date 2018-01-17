package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Jérome on 17/01/2018.
 */
@Dao
public interface DatabaseProjectDao {

    @Query("SELECT * FROM projects")
    List<DatabaseProject> loadAllProjects();

    @Insert
    void insertProject(DatabaseProject databaseProject);

}
