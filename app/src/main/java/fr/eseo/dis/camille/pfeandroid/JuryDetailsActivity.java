package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Jury;
import fr.eseo.dis.camille.pfeandroid.dto.juries.JuryInfo;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Member;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;
import fr.eseo.dis.camille.pfeandroid.dto.juries.Student;

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

    public static int NEW_CARD_COUNTER;

    private RecyclerView recycler;
    private JuryDetailsAdaptater juryDetailsAdaptater;
    private Jury jury;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_details);
        Intent intent = getIntent();
        ObjectMapper o = new ObjectMapper();
        try {
            jury = o.readValue(intent.getStringExtra("juryAsString"), Jury.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        NEW_CARD_COUNTER = 3;
        id = (TextView) findViewById(R.id.jury_id);
        date = (TextView) findViewById(R.id.jury_date);
        Member[] listMembers = jury.getInfo().getMembers();

        LinearLayout linear = (LinearLayout) findViewById(R.id.jury_details_members);
        for (int i = 0; i < listMembers.length; i++) {
            TextView spaceText = new TextView(this);
            spaceText.setText(listMembers[i].getForename() + " " + listMembers[i].getSurname());
            spaceText.setTextColor(ContextCompat.getColor(this, R.color.darkGrey));
            linear.addView(spaceText);
        }

        id.setText("" + jury.getIdJury());
        date.setText(jury.getDate());
        recycler = (RecyclerView) findViewById(R.id.cardListDetailsJury);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        juryDetailsAdaptater = new JuryDetailsAdaptater(this);

        new HttpRequestTask().execute();



    }

    private class HttpRequestTask extends AsyncTask<Void, Void, JuryInfo> {
        @Override
        protected JuryInfo doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                JuryInfo juryInfo = webServices.juryinfo(JuryDetailsActivity.this, pref.getString("username", null), pref.getString("token", null), jury.getIdJury() + "");

                return juryInfo;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(JuryInfo juryInfo ) {
            if (juryInfo == null) {
                Toast.makeText(JuryDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            else{
                Project[] projects = juryInfo.getProjects();
                recycler.setAdapter(juryDetailsAdaptater);
                juryDetailsAdaptater.setProjects(new ArrayList<>(Arrays.asList(projects)));
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