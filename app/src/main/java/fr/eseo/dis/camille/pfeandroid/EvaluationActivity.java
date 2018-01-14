package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import static android.os.Build.VERSION_CODES.M;

public class EvaluationActivity extends AppCompatActivity {


    private WebServices webServices;

    private int idProject;
    private Project project;

    private SharedPreferences pref;
    private String message = "";


    private NoteAdaptater noteAdapter;

    private RecyclerView recycler;

    private TextView projectTitle;
    private Button buttonValidation;

    private NoteInfo noteInfoGlobal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        projectTitle = (TextView) findViewById(R.id.project_title);
        buttonValidation = (Button) findViewById(R.id.button_notes_validation);

        Intent intent = getIntent();
        ObjectMapper o = new ObjectMapper();
        try {
            project = o.readValue(intent.getStringExtra("projectAsString"), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idProject = project.getProjectId();
        projectTitle.setText(project.getTitle());

        recycler = (RecyclerView) findViewById(R.id.cardListNotes);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        noteAdapter = new NoteAdaptater(this);

        new HttpRequestTask().execute();

        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EvaluationActivity.this, EvaluationValidationActivity.class);
                try {
                    String noteInfoAsString = Tool.parse(noteInfoGlobal);
                    intent.putExtra("noteInfoAsString", noteInfoAsString);
                    intent.putExtra("idProject", idProject + "");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }

        });

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, NoteInfo> {
        @Override
        protected NoteInfo doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);

                NoteInfo noteInfo = webServices.notes(EvaluationActivity.this, pref.getString("username", null), pref.getString("token", null), idProject +"");
                noteInfoGlobal = noteInfo;
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
                buttonValidation.setVisibility(View.VISIBLE);
            }
        }

    }

    public void clickNoteVariation(List<Note> listNotes) {
        noteInfoGlobal.setNotes(listNotes);
        recycler.setAdapter(noteAdapter);
        noteAdapter.setNotes(listNotes);
    }
}
