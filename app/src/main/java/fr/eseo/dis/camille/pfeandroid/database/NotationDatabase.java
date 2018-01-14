package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.Database;
import android.content.Context;


/**
 * Created by Jérome on 10/01/2018.
 */

@Database(entities = {PseudoJury.class, Mark.class, Project.class},
        version = 2)

public abstract class NotationDatabase extends RoomDatabase {
    private static NotationDatabase INSTANCE;

    public abstract  PseudoJuryDao pseudoJuryDao();

    public static NotationDatabase getDatabase(Context context) {
        if(INSTANCE == null){
            //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
            // and have a filename where the database will be stored physically on the device
            INSTANCE = Room.databaseBuilder(context, NotationDatabase.class, "notation.db")
                    // For ease of use only => Need to delete this for production code
                    .allowMainThreadQueries()
                    //When migrating delete the database and recreate it
                    .fallbackToDestructiveMigration()
                    //Create the database
                    .build();
        }
        return INSTANCE;
    }


    public static void destroyInstance(){
        INSTANCE = null;
    }
}
