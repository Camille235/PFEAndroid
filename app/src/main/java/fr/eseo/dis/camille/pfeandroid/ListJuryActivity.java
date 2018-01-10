package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Jury;
import fr.eseo.dis.camille.pfeandroid.dto.juries.ListJuries;

public class ListJuryActivity extends AppCompatActivity {

    WebServices webServices;
    String message = "";

    public static final String JURY_EXTRA = "jury_extra";
    public static int NEW_CARD_COUNTER;

    private ListJuryAdaptater listJuryAdaptater;
    public static Jury jury;

    RecyclerView recycler;
    SharedPreferences pref;

    String listJuryStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jury);
        Intent intent = getIntent();
        listJuryStyle = intent.getStringExtra("listJuryStyle");



        NEW_CARD_COUNTER = 3;
        recycler = (RecyclerView) findViewById(R.id.cardList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        listJuryAdaptater = new ListJuryAdaptater(this);

        new HttpRequestTask().execute();



    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ListJuries> {
        @Override
        protected ListJuries doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                ListJuries listJuries = new ListJuries();
                if("all".equals(listJuryStyle)) {
                    listJuries = webServices.listAllJuries(ListJuryActivity.this, pref.getString("username", null), pref.getString("token", null));
                }else if("my".equals(listJuryStyle)){
                    listJuries = webServices.listMyJuries(ListJuryActivity.this, pref.getString("username", null), pref.getString("token", null));
                }

                return listJuries;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ListJuries listJuries) {
            if (listJuries == null) {
                Toast.makeText(ListJuryActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else{
                recycler.setAdapter(listJuryAdaptater);
                listJuryAdaptater.setJurys(new ArrayList<>(Arrays.asList(listJuries.getJuries())));
            }
        }

    }

    public void clickItem(Jury jury) {
        Intent intent = new Intent(this, JuryDetailsActivity.class);
        SharedPreferences.Editor editor = pref.edit();
        this.jury = jury;
        /*editor.putInt("juryId", jury.getIdJury());
        editor.putString("juryDate", jury.getDate());
        ProjectInfo[] projectInfo = jury.getInfo().getProjects();
        int[] listPostersId = new int[projectInfo.length];
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < listPostersId.length; i++) {
            str.append(projectInfo[i].getProjectId()).append(",");
        }
        editor.putString("listPostersId", str.toString());
        editor.putInt("size", listPostersId.length);
        editor.commit(); // commit changes*/
        startActivity(intent);


    }




}
