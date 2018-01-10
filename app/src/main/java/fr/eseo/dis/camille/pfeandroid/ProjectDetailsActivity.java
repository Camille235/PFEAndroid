package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Notation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.bean.Poster;
import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.bean.Role;
import fr.eseo.dis.camille.pfeandroid.bean.Student;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.ListProjects;

import static android.R.attr.y;
import static android.R.id.message;
import static fr.eseo.dis.camille.pfeandroid.ListProjectActivity.PROJECT_EXTRA;

public class ProjectDetailsActivity extends AppCompatActivity {


    private TextView title;
    private TextView poster;
    private TextView supervisor;
    private TextView description;


    private Button btnDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        title = (TextView) findViewById(R.id.project_details_title);
        poster = (TextView) findViewById(R.id.project_details_poster);
        supervisor = (TextView) findViewById(R.id.project_details_supervisor);
        description = (TextView) findViewById(R.id.project_details_description);

        btnDetails = (Button) findViewById(R.id.button_details);

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

    }

}
