package fr.eseo.dis.camille.pfeandroid.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eseo.dis.camille.pfeandroid.bean.User;

/**
 * Created by Jérome on 10/01/2018.
 */

public class PseudoJuryManager {
    public final static String PSEUDO_JURY_TABLE_NAME = "PseudoJurys";
    public final static String PSEUDO_JURY_ID = "idPseudoJury";
    private final static int COL_ID = 0;
    public final static String PSEUDO_JURY_NAME = "name";
    private final static int COL_NAME = 1;
    public final static String PSEUDO_JURY_PASSWORD = "password";
    private final static int COL_PASSWORD = 2;

    public static final String CREATE_TABLE_PSEUDO_JURY =
            "CREATE TABLE " + PSEUDO_JURY_TABLE_NAME + " (" +PSEUDO_JURY_ID + " INTEGER PRIMARY KEY ASC, "+
                    PSEUDO_JURY_NAME + " TEXT NOT NULL, "+ PSEUDO_JURY_PASSWORD + " TEXT NOT NULL);";


    private SQLiteDatabase bdd;
    private BDDHandler mySQLite;


    public PseudoJuryManager(Context context){
        mySQLite = new BDDHandler(context);
        mySQLite.getWritableDatabase();
    }

    public void open(){bdd = mySQLite.getWritableDatabase();}

    public void close(){bdd.close();}

    public SQLiteDatabase getBDD(){return bdd;}

    public long insertPseudoJury(User user){
        ContentValues values = new ContentValues();
        values.put(PSEUDO_JURY_NAME, user.getUserName());
        values.put(PSEUDO_JURY_PASSWORD, user.getPassword());
        return bdd.insert(PSEUDO_JURY_TABLE_NAME, null, values);
    }

    public int updatePseudoJury(int idPseudoJury, User user) {
        ContentValues values = new ContentValues();
        values.put(PSEUDO_JURY_ID, user.getIdUser());
        values.put(PSEUDO_JURY_NAME, user.getUserName());
        values.put(PSEUDO_JURY_PASSWORD, user.getPassword());
        return bdd.update(PSEUDO_JURY_TABLE_NAME, values, PSEUDO_JURY_ID + " = " + idPseudoJury, null);
    }

    public int removePseudoJuryWithID(int idPseudoJury){
        return bdd.delete(PSEUDO_JURY_TABLE_NAME, PSEUDO_JURY_ID + " = " + idPseudoJury, null);
    }

    public User getPseudoJuryWithId(int idPseudoJury){
        Cursor c = bdd.query(PSEUDO_JURY_TABLE_NAME, new String[] {PSEUDO_JURY_ID, PSEUDO_JURY_NAME, PSEUDO_JURY_PASSWORD}, PSEUDO_JURY_ID + " LIKE \"" + idPseudoJury +"\"", null, null, null, null);
        return cursorToPseudoJury(c);
    }

    private User cursorToPseudoJury(Cursor cursor){
        if (cursor.getCount() ==0)
            return null;
        cursor.moveToFirst();
        User user = new User();
        user.setIdUser(cursor.getInt(COL_ID));
        user.setUserName(cursor.getString(COL_NAME));
        user.setPassword(cursor.getString(COL_PASSWORD));
        cursor.close();
        return user;
    }
}
