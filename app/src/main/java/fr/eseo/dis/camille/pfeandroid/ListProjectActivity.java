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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.R;
import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.bean.Student;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.ListProjects;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.Login;

import static android.R.id.message;
import static fr.eseo.dis.camille.pfeandroid.R.id.login;
import static fr.eseo.dis.camille.pfeandroid.R.id.username;

public class ListProjectActivity extends AppCompatActivity {


    WebServices webServices;
    String message = "";

    public static final String PROJECT_EXTRA = "project_extra";
    public static int NEW_CARD_COUNTER;

    private ListProjectAdaptater listProjectAdapter;

    RecyclerView recycler;
    SharedPreferences pref;

    String listProjectStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);
        Intent intent = getIntent();
        listProjectStyle = intent.getStringExtra("listProjectStyle");

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
                Toast.makeText(ListProjectActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else{
                recycler.setAdapter(listProjectAdapter);
                listProjectAdapter.setProjects(new ArrayList<>(Arrays.asList(listProjects.getProjects())));
            }
        }

    }

    public void clickItem(Project project) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("projectTitle", project.getTitle());
        editor.putString("projectDescription", project.getDescrip());
        editor.putString("projectSupervisor", project.getSupervisor().getForename() + " " + project.getSupervisor().getSurname());
        editor.putInt("projectConfidentiality", project.getConfid());
        editor.putBoolean("projectPoster", project.isPoster());

        Student[] students = project.getStudents();

        Set<String> studentName = new HashSet<>();

        for(int i = 0; i < students.length; i++){
            studentName.add(students[i].getForename() + " " + students[i].getSurname());
        }
        editor.putStringSet("studentName", studentName);

        editor.commit(); // commit changes
        startActivity(intent);
    }




}
