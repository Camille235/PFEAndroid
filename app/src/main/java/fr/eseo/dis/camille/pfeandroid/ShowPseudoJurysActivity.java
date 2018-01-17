package fr.eseo.dis.camille.pfeandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;

public class ShowPseudoJurysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pseudo_jurys);

        TextView text =(TextView) findViewById(R.id.text_view_database_test);

        GetAllPseudoJurys g = new GetAllPseudoJurys();
        g.execute();
        PseudoJury[] p = g.getListPseudoJurys();

        text.setText(p[0].getNamePseudoJury());
    }


    private class GetAllPseudoJurys extends AsyncTask<Void, Void, Void> {
        private PseudoJury[] listPseudoJurys;

        @Override
        protected Void doInBackground(Void... params) {
            listPseudoJurys = NotationDatabase.getDatabase(ShowPseudoJurysActivity.this).pseudoJuryDao().loadAllPseudoJurys();
            return null;
        }

        public PseudoJury[] getListPseudoJurys() {
            return listPseudoJurys;
        }
    }
}
