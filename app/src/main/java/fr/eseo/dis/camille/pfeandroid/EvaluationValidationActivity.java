package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.R;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;
import fr.eseo.dis.camille.pfeandroid.dto.note.Note;
import fr.eseo.dis.camille.pfeandroid.dto.note.NoteInfo;

public class EvaluationValidationActivity extends AppCompatActivity {

    private EvaluationValidationAdaptater evaluationValidationAdaptater;
    private RecyclerView recycler;
    private TextView textViewMessage;

    private WebServices webServices;

    private int idProject;
    private NoteInfo noteInfo;
    private String message = "";
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_validation);

        textViewMessage = (TextView) findViewById(R.id.text_loading);

        Intent intent = getIntent();
        ObjectMapper o = new ObjectMapper();
        try {
            noteInfo = o.readValue(intent.getStringExtra("noteInfoAsString"), NoteInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        idProject = Integer.parseInt(intent.getStringExtra("idProject"));
        recycler = (RecyclerView) findViewById(R.id.cardNotesValidation);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        evaluationValidationAdaptater = new EvaluationValidationAdaptater(this);
        recycler.setAdapter(evaluationValidationAdaptater);
        evaluationValidationAdaptater.setNotes(noteInfo.getNotes());

        new HttpRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, NoteInfo> {
        @Override
        protected NoteInfo doInBackground(Void... params) {

            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                NoteInfo newNoteInfo = webServices.notes(EvaluationValidationActivity.this, pref.getString("username", null), pref.getString("token", null), idProject +"");
                List<Note> listNotes = noteInfo.getNotes();
                for (int i = 0; i < newNoteInfo.getNotes().size(); i++) {
                    if (listNotes.get(i).getMynote() != newNoteInfo.getNotes().get(i).getMynote()) {
                        webServices.newNote( EvaluationValidationActivity.this, pref.getString( "username", null ),
                                pref.getString( "token", null ), idProject + "", listNotes.get(i).getUserId() + "", listNotes.get(i).getMynote() + "" );
                    }
                }
                newNoteInfo = webServices.notes(EvaluationValidationActivity.this, pref.getString("username", null), pref.getString("token", null), idProject +"");
                return newNoteInfo;
            } catch (LoginError e) {
                message = e.getMessage();
                Log.e("EvaluationValidationActivity", message, e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(NoteInfo noteInfo) {
            if (noteInfo == null) {

            }
            else{
                textViewMessage.setText("Notes envoyées");
                recycler.setAdapter(evaluationValidationAdaptater);
                evaluationValidationAdaptater.setNotes(noteInfo.getNotes());
            }

        }

    }
}
