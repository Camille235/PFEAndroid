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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import errors.LoginError;

public class ProjectDetailsActivity extends AppCompatActivity {


    private TextView title;
    private TextView poster;
    private TextView supervisor;
    private TextView description;

    private int idPoster;

    private ImageView posterView;

    private WebServices webServices;
    public static Bitmap fullPoster;

    private Button btnDetails;
    private SharedPreferences pref;
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        idPoster = pref.getInt("projectId", 0);
        title = (TextView) findViewById(R.id.project_details_title);
        poster = (TextView) findViewById(R.id.project_details_poster);
        supervisor = (TextView) findViewById(R.id.project_details_supervisor);
        description = (TextView) findViewById(R.id.project_details_description);

        btnDetails = (Button) findViewById(R.id.button_details);
        posterView = (ImageView) findViewById(R.id.posterImageView);

        title.setText(pref.getString("projectTitle", null));
        if(pref.getBoolean("projectPoster", false)){
            poster.setText("Poster : Oui");
        }else{
            poster.setText("Poster : Non");
        }
        supervisor.setText(pref.getString("projectSupervisor", null));
        description.setText(pref.getString("projectDescription", null));
       /* btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmDetailsActivity.this, FilmNotationActivity.class);
                intent.putExtra(FILM_EXTRA, film);
                startActivity(intent);
            }
        });*/

        Set<String> studentNameSet = pref.getStringSet("studentName", null);
        List<String> studentName = new ArrayList<>(studentNameSet);

        LinearLayout linear = (LinearLayout) findViewById(R.id.project_details_students);
        for (int i = 0; i < studentName.size(); i++) {
            TextView spaceText = new TextView(this);
            spaceText.setText(studentName.get(i));
            spaceText.setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
            linear.addView(spaceText);
        }
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
                Toast.makeText(ProjectDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
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
