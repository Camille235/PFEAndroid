package fr.eseo.dis.camille.pfeandroid.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jérome on 10/01/2018.
 */

public class BDDHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 6;
    private static BDDHandler sInstance;

    public BDDHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la base de données
        // on exécute ici les requêtes de création des tables
        Log.d("KEY", "test");
        sqLiteDatabase.execSQL("drop table IF EXISTS PseudoJurys;");
        sqLiteDatabase.execSQL("drop table IF EXISTS Projects;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Marks;");
        sqLiteDatabase.execSQL(PseudoJuryManager.CREATE_TABLE_PSEUDO_JURY);
        sqLiteDatabase.execSQL(ProjectManager.CREATE_TABLE_PROJECT);
        sqLiteDatabase.execSQL(MarkManager.CREATE_TABLE_MARK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Mise à jour de la base de données
        // méthode appelée sur incrémentation de DATABASE_VERSION
        // on peut faire ce qu'on veut ici, comme recréer la base :
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PseudoJurys;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Projects;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Marks;");
        onCreate(sqLiteDatabase);
    }
}
