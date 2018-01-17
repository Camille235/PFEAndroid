package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.database.DatabaseProject;
import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;

public class ListProjectsAsVisitorActivity extends AppCompatActivity {

    String username = "";
    private SharedPreferences pref;
    private ListProjectsAsVisitorAdaptater listProjectAdapter;

    RecyclerView recycler;
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
        recycler = (RecyclerView) findViewById(R.id.cardListAsVisitor);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        listProjectAdapter = new ListProjectsAsVisitorAdaptater(this);
        listProjectAdapter.setProjects(ldpFinal);
        recycler.setAdapter( listProjectAdapter );


    }


}
