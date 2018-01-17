package fr.eseo.dis.camille.pfeandroid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.database.DatabaseProject;
import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;

public class ListProjectsAsVisitorActivity extends AppCompatActivity {

    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_projects_as_visitor);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        pref.getString("username",username);

        List<PseudoJury> lp = NotationDatabase.getDatabase(ListProjectsAsVisitorActivity.this).pseudoJuryDao().loadAllPseudoJurys();
        PseudoJury pseudoJ = null;
        for (PseudoJury p : lp) {
            if(p.getNamePseudoJury() == username){
                pseudoJ = p;
            }
        }

        List<DatabaseProject> ldp = NotationDatabase.getDatabase(ListProjectsAsVisitorActivity.this).databaseProjectDao().loadAllProjects();
        List<DatabaseProject> ldpFinal = new ArrayList<>();
        for(DatabaseProject dp : ldp) {
            if(pseudoJ.getIdPseudoJury() == dp.getIdPseudoJury()) {
                ldpFinal.add(dp);
            }
        }


    }
}
