package fr.eseo.dis.camille.pfeandroid.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
/**
 * Created by Jérome on 14/01/2018.
 */

public class NotationDatabaseCallback extends RoomDatabase.Callback {

    private static String [] CREATE_TABLES = new String[]{
            "CREATE TABLE IF NOT EXISTS 'notation'.`pseudojurys` ('idPseudoJury' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "'namePseudoJury' VARCHAR(20) NOT NULL, 'passwordPseudoJury' VARCHAR(100) NOT NULL)",
            "CREATE TABLE IF NOT EXISTS 'notation'.`projects` ('idProject' INTEGER PRIMARY KEY," +
                    "'titleProject' VARCHAR(100) NOT NULL, 'descriptionProject' VARCHAR(500), 'posterProject' BLOB NOT NULL)",
            "CREATE TABLE IF NOT EXISTS 'notation'.`marks` ('idMark' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "'valueMark' INTEGER NOT NULL, 'idPseudoJury' INTEGER NOT NULL, 'idProject' INTEGER NOT NULL)"
    };



    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        for(String creationQuery : CREATE_TABLES){
            db.execSQL(creationQuery);
        }
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        Log.d("ROOM_Database:","onOpen()");
        super.onOpen(db);
    }


}
