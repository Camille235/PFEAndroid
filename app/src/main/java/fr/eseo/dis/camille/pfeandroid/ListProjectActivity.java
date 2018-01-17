package fr.eseo.dis.camille.pfeandroid;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Student;
import fr.eseo.dis.camille.pfeandroid.dto.projects.ListProjects;

import static fr.eseo.dis.camille.pfeandroid.Tool.parse;


public class ListProjectActivity extends AppCompatActivity {


    WebServices webServices;
    String message = "";

    public static final String PROJECT_EXTRA = "project_extra";
    public static int NEW_CARD_COUNTER;

    private ListProjectAdaptater listProjectAdapter;


    TextView messageProject;

    RecyclerView recycler;
    SharedPreferences pref;

    String listProjectStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);
        Intent intent = getIntent();
        listProjectStyle = intent.getStringExtra("listProjectStyle");

        messageProject = (TextView) findViewById(R.id.message_project);

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
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                ListProjects listProjects = new ListProjects();
                if("all".equals(listProjectStyle)) {
                    listProjects = webServices.listAllProjects(ListProjectActivity.this, pref.getString("username", null), pref.getString("token", null));
                }else if("my".equals(listProjectStyle)){
                    listProjects = webServices.listMyProjects(ListProjectActivity.this, pref.getString("username", null), pref.getString("token", null));
                }

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
                if ("Invalide Credentials".equals(message)) {
                    Intent intent = new Intent(ListProjectActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(!"".equals(message)) {
                    Log.e( "ListProjectActivity", message );
                }
            }
            else{
                if( listProjects.getProjects().length == 0){
                    messageProject.setVisibility( View.VISIBLE);
                }else {
                    recycler.setAdapter( listProjectAdapter );
                    listProjectAdapter.setProjects( new ArrayList<>( Arrays.asList( listProjects.getProjects() ) ) );
                }
            }
        }

    }

    public void clickItem(Project project) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);

        try {
            String projectAsString = Tool.parse(project);
            intent.putExtra("projectAsString", projectAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }




}
