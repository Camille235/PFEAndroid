package fr.eseo.dis.camille.pfeandroid.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.bean.User;

/**
 * Created by Jérome on 10/01/2018.
 */

public class MarkManager {
    public final static String MARK_TABLE_NAME = "Marks";
    public final static String MARK_ID = "idMark";
    private final static int COL_ID = 0;
    public final static String MARK_VALUE = "value";
    private final static int COL_VALUE = 1;
    public final static String PSEUDO_JURY_ID = "idPseudoJury";
    private final static int COL_ID_PSEUDO_JURY = 2;
    public final static String PROJECT_ID = "idProject";
    private final static int COL_ID_PROJECT = 2;

    public static final String CREATE_TABLE_MARK =
            "CREATE TABLE " + MARK_TABLE_NAME + " (" + MARK_ID + " INTEGER PRIMARY KEY ASC, "+
                    MARK_VALUE + " INTEGER NOT NULL, "+ PSEUDO_JURY_ID + " INTEGER NOT NULL, "+ PROJECT_ID + " INTEGER NOT NULL);";


    private SQLiteDatabase bdd;
    private BDDHandler mySQLite;


    public MarkManager(Context context){
        mySQLite = new BDDHandler(context);
        mySQLite.getWritableDatabase();
    }

    public void open(){bdd = mySQLite.getWritableDatabase();}

    public void close(){bdd.close();}

    public SQLiteDatabase getBDD(){return bdd;}

    public long insertMark(int mark, User user, Project project){
        ContentValues values = new ContentValues();
        values.put(MARK_VALUE, mark);
        values.put(PSEUDO_JURY_ID, user.getIdUser());
        values.put(PROJECT_ID, project.getProjectId());
        return bdd.insert(MARK_TABLE_NAME, null, values);
    }

    public int updateMark(int idMark, int mark, User user, Project project) {
        ContentValues values = new ContentValues();
        values.put(MARK_ID, idMark);
        values.put(MARK_VALUE, mark);
        values.put(PSEUDO_JURY_ID, user.getIdUser());
        values.put(PROJECT_ID, project.getProjectId());
        return bdd.update(MARK_TABLE_NAME, values, MARK_ID + " = " + idMark, null);
    }

    public int removeMarkWithID(int idMark){
        return bdd.delete(MARK_TABLE_NAME, MARK_ID + " = " + idMark, null);
    }

    public int getMarkWithId(int idMark){
        Cursor c = bdd.query(MARK_TABLE_NAME, new String[] {MARK_ID, MARK_VALUE, PSEUDO_JURY_ID, PROJECT_ID},
                MARK_ID + " LIKE \"" + idMark +"\"", null, null, null, null);
        return cursorToMark(c);
    }

    private int cursorToMark(Cursor cursor){
        if (cursor.getCount() ==0)
            return -1;
        cursor.moveToFirst();
        int mark = cursor.getInt(COL_VALUE);
        cursor.close();
        return mark;
    }
}
