package fr.eseo.dis.camille.pfeandroid;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;

public class ShowPseudoJurysActivity extends AppCompatActivity {


    RecyclerView recycler;
    SharedPreferences pref;
    ShowPseudoJurysAdaptater showPseudoJurys;
    public static int NEW_CARD_COUNTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pseudo_jurys);

        recycler = (RecyclerView) findViewById(R.id.recycler_view_pseudos_jurys);
        List<PseudoJury> p = NotationDatabase.getDatabase(ShowPseudoJurysActivity.this).pseudoJuryDao().loadAllPseudoJurys();

        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        showPseudoJurys = new ShowPseudoJurysAdaptater(this);

        showPseudoJurys.setPseudojurys(p);

    }

/*
    private class GetAllPseudoJurys extends AsyncTask<Void, Void, Void> {
        private List<PseudoJury> listPseudoJurys;

        @Override
        protected Void doInBackground(Void... params) {
            setListPseudoJurys(NotationDatabase.getDatabase(ShowPseudoJurysActivity.this).pseudoJuryDao().loadAllPseudoJurys());
            return null;
        }

        public List<PseudoJury> getListPseudoJurys() {
            return listPseudoJurys;
        }

        public void setListPseudoJurys(List<PseudoJury> listPseudoJurys) {
            this.listPseudoJurys = listPseudoJurys;
        }
    }
    */
}
