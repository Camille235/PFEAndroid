package fr.eseo.dis.camille.pfeandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

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
        List<PseudoJury> p = g.getListPseudoJurys();

        text.setText(p.get(0).getNamePseudoJury());
    }


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
}
