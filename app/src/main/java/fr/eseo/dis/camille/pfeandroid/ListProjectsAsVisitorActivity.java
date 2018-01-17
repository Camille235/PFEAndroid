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
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_projects_as_visitor);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        username = pref.getString("username",username);

        List<PseudoJury> lp = NotationDatabase.getDatabase(ListProjectsAsVisitorActivity.this).pseudoJuryDao().loadAllPseudoJurys();
        PseudoJury pseudoJ = null;
        for (PseudoJury p : lp) {
            if(p.getNamePseudoJury().equals(username)){
                pseudoJ = p;
            }
        }

        List<DatabaseProject> ldp = NotationDatabase.getDatabase(ListProjectsAsVisitorActivity.this).databaseProjectDao().loadAllProjects();
        List<DatabaseProject> ldpFinal = new ArrayList<>();
        for(DatabaseProject dp : ldp) {
            System.out.println(pseudoJ.getIdPseudoJury()+"     "+dp.getIdPseudoJury());
            if(pseudoJ.getIdPseudoJury() == dp.getIdPseudoJury()) {
                System.out.println("into if "+dp.getTitleProject());
                ldpFinal.add(dp);
            }
        }


    }
}
