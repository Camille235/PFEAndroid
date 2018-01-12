package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Student;
import fr.eseo.dis.camille.pfeandroid.dto.note.NoteInfo;


public class ProjectDetailsActivity extends AppCompatActivity {


    private TextView title;
    private TextView poster;
    private TextView supervisor;


    private Button buttonEvaluation;

    private TextView description;

    private int idPoster;

    private ImageView posterView;

    private WebServices webServices;
    public static Bitmap fullPoster;

    private SharedPreferences pref;
    private String message = "";
    private Project project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        title = (TextView) findViewById(R.id.project_details_title);
        poster = (TextView) findViewById(R.id.project_details_poster);
        supervisor = (TextView) findViewById(R.id.project_details_supervisor);
        description = (TextView) findViewById(R.id.project_details_description);

        buttonEvaluation = (Button) findViewById(R.id.button_evaluation);
        posterView = (ImageView) findViewById(R.id.posterImageView);


        Intent intent = getIntent();
        ObjectMapper o = new ObjectMapper();
        try {
            project = o.readValue(intent.getStringExtra("projectAsString"), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idPoster = project.getProjectId();
        title.setText(project.getTitle());
        if(project.isPoster()){
            poster.setText("Poster : Oui");
        }else{
            poster.setText("Poster : Non");
        }
        supervisor.setText(project.getSupervisor().getForename() + " " + project.getSupervisor().getSurname());
        description.setText(project.getDescrip());
       /* btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmDetailsActivity.this, FilmNotationActivity.class);
                intent.putExtra(FILM_EXTRA, film);
                startActivity(intent);
            }
        });*/

        Student[] students = project.getStudents();

        LinearLayout linear = (LinearLayout) findViewById(R.id.project_details_students);
        for (int i = 0; i < students.length; i++) {
            TextView spaceText = new TextView(this);
            spaceText.setText(students[i].getForename() + " " + students[i].getSurname());
            spaceText.setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
            linear.addView(spaceText);
        }


        buttonEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetailsActivity.this, EvaluationActivity.class);
                try {
                    String projectAsString = Tool.parse(project);
                    intent.putExtra("projectAsString", projectAsString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }

        });

        new HttpRequestTask().execute();

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                fullPoster = webServices.poster(ProjectDetailsActivity.this, pref.getString("username", null), pref.getString("token", null), idPoster +"", "FULL");
                Bitmap poster = webServices.poster(ProjectDetailsActivity.this, pref.getString("username", null), pref.getString("token", null), idPoster +"", "THUMB");

                return poster;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap poster) {
            if (poster == null) {

            }
            else{
                posterView.setImageBitmap(poster);
            }
        }

    }


    public void onClick(View v) {
        Intent intent = new Intent(this, DisplayPosterActivity.class);
        startActivity(intent);

    }

}
