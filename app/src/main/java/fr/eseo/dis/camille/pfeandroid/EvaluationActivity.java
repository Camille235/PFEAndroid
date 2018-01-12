package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;
import fr.eseo.dis.camille.pfeandroid.dto.note.Note;
import fr.eseo.dis.camille.pfeandroid.dto.note.NoteInfo;

public class EvaluationActivity extends AppCompatActivity {


    private WebServices webServices;

    private int idPoster;
    private Project project;

    private SharedPreferences pref;
    private String message = "";


    private NoteAdaptater noteAdapter;

    private RecyclerView recycler;

    private TextView projectTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        projectTitle = (TextView) findViewById(R.id.project_title);

        Intent intent = getIntent();
        ObjectMapper o = new ObjectMapper();
        try {
            project = o.readValue(intent.getStringExtra("projectAsString"), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idPoster = project.getProjectId();


        recycler = (RecyclerView) findViewById(R.id.cardListNotes);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        noteAdapter = new NoteAdaptater(this);

        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, NoteInfo> {
        @Override
        protected NoteInfo doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);

                NoteInfo noteInfo = webServices.notes(EvaluationActivity.this, pref.getString("username", null), pref.getString("token", null), idPoster +"");

                return noteInfo;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(NoteInfo noteInfo) {
            if (noteInfo == null) {

            }
            else{
                recycler.setAdapter(noteAdapter);
                noteAdapter.setNotes(noteInfo.getNotes());
            }
        }

    }
}
