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
import fr.eseo.dis.camille.pfeandroid.bean.Jury;
import fr.eseo.dis.camille.pfeandroid.bean.ProjectInfo;

import static android.R.attr.description;

public class JuryDetailsActivity extends AppCompatActivity {


    private TextView id;
    private TextView date;

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
        setContentView(R.layout.activity_jury_details);

        Jury jury = ListJuryActivity.jury;
        ProjectInfo[] projects = jury.getInfo().getProjects();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        id = (TextView) findViewById(R.id.jury_id);
        date = (TextView) findViewById(R.id.jury_date);


        id.setText("" + jury.getIdJury());
        date.setText(jury.getDate());


        LinearLayout linear = (LinearLayout) findViewById(R.id.jury_projects);
        for (int i = 0; i < projects.length; i++) {
            TextView spaceText = new TextView(this);
            spaceText.setText(projects[i].getTitle());
            spaceText.setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
            linear.addView(spaceText);
        }

    }


    public void onClick(View v) {
        Intent intent = new Intent(this, DisplayPosterActivity.class);
        startActivity(intent);

    }
}