package fr.eseo.dis.camille.pfeandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;

/**
 * Created by Jérome on 10/01/2018.
 */

public class ProjectManager {
    public final static String PROJECT_TABLE_NAME = "Projects";
    public final static String PROJECT_ID = "idProject";
    private final static int COL_ID = 0;
    public final static String PROJECT_TITLE = "title";
    private final static int COL_TITLE = 1;
    public final static String PROJECT_DESCRIPTION = "description";
    private final static int COL_DESCRIPTION = 2;
    public final static String PROJECT_POSTER = "poster";
    private final static int COL_POSTER = 3;

    public static final String CREATE_TABLE_PROJECT =
            "CREATE TABLE " + PROJECT_TABLE_NAME + " (" + PROJECT_ID + " INTEGER PRIMARY KEY ASC, "+
                    PROJECT_TITLE + " TEXT NOT NULL" + PROJECT_DESCRIPTION + " TEXT NOT NULL, "+
                    PROJECT_POSTER + " BLOB NOT NULL);";


    private SQLiteDatabase bdd;
    private BDDHandler mySQLite;


    public ProjectManager(Context context){
        mySQLite = new BDDHandler(context);
        mySQLite.getWritableDatabase();
    }

    public void open(){bdd = mySQLite.getWritableDatabase();}

    public void close(){bdd.close();}

    public SQLiteDatabase getBDD(){return bdd;}

    public long insertProject(Project project, byte[] poster){
        ContentValues values = new ContentValues();
        values.put(PROJECT_TITLE, project.getTitle());
        values.put(PROJECT_DESCRIPTION, project.getDescrip());
        values.put(PROJECT_POSTER, poster);
        return bdd.insert(PROJECT_TABLE_NAME, null, values);
    }

    public int updateProject(int idProject, Project project, byte[] poster) {
        ContentValues values = new ContentValues();
        values.put(PROJECT_ID, idProject);
        values.put(PROJECT_TITLE, project.getTitle());
        values.put(PROJECT_DESCRIPTION, project.getDescrip());
        values.put(PROJECT_POSTER, poster);
        return bdd.update(PROJECT_TABLE_NAME, values, PROJECT_ID + " = " + idProject, null);
    }

    public int removeProjectWithID(int idProject){
        return bdd.delete(PROJECT_TABLE_NAME, PROJECT_ID + " = " + idProject, null);
    }

    public Project getProjectWithId(int idProject) {
        Cursor c = bdd.query(PROJECT_TABLE_NAME, new String[]{PROJECT_ID, PROJECT_TITLE, PROJECT_DESCRIPTION, PROJECT_POSTER},
                PROJECT_ID + " LIKE \"" + idProject + "\"", null, null, null, null);
        return cursorToProject(c);
    }

    private Project cursorToProject(Cursor cursor){
        if (cursor.getCount() ==0)
            return null;
        cursor.moveToFirst();
        Project project = new Project();
        project.setProjectId(cursor.getInt(COL_ID));
        project.setTitle(cursor.getString(COL_TITLE));
        project.setDescrip(cursor.getString(COL_DESCRIPTION));
        cursor.close();
        return project;
    }
}
