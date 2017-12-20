package fr.eseo.dis.camille.pfeandroid;



import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.R;
import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.ListProjects;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.Login;

import static android.R.id.message;
import static fr.eseo.dis.camille.pfeandroid.R.id.login;
import static fr.eseo.dis.camille.pfeandroid.R.id.username;

public class ListProjectActivity extends AppCompatActivity {


    WebServices webServices;
    String message = "";
    public static int NEW_CARD_COUNTER;

    private ListProjectAdaptater listProjectAdapter;

    private List<Project> listProject;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);
        NEW_CARD_COUNTER = 3;
        recycler = (RecyclerView) findViewById(R.id.cardList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        listProjectAdapter = new ListProjectAdaptater(this);

        new HttpRequestTask().execute();



    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ListProjects> {
        @Override
        protected ListProjects doInBackground(Void... params) {
            try {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                ListProjects listProjects = webServices.listAllProjects(ListProjectActivity.this, pref.getString("username", null),  pref.getString("token", null));
                return listProjects;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ListProjects listProjects) {
            if (listProjects == null) {
                Toast.makeText(ListProjectActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else{
                recycler.setAdapter(listProjectAdapter);
                listProjectAdapter.setProjects(new ArrayList<>(Arrays.asList(listProjects.getProjects())));
            }
        }

    }
    /* public void clickItem(Project project) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, project);
        startActivity(intent);
    }*/




}
