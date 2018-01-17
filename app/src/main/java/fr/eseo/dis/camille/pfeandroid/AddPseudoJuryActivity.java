package fr.eseo.dis.camille.pfeandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.database.DatabaseProject;
import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;
import fr.eseo.dis.camille.pfeandroid.dto.door.DoorProjects;
import fr.eseo.dis.camille.pfeandroid.dto.door.Project;
import fr.eseo.dis.camille.pfeandroid.dto.projects.ListProjects;

import static java.lang.System.in;

public class AddPseudoJuryActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String usernameString;
    String passwordString;
    PseudoJury ps = null;
    String userPseudoString = "";
    WebServices webService;
    SharedPreferences pref;
    String message = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pseudo_jury);

        final Button submitButton = (Button) findViewById(R.id.button_submit);
        final Button otherButton = (Button) findViewById(R.id.button_show_if_works);
        username = (EditText)findViewById(R.id.champ_name);
        password = (EditText)findViewById(R.id.champs_password);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameString = username.getText().toString();
                passwordString = password.getText().toString();
                ps = new PseudoJury(usernameString,passwordString);
                NotationDatabase.getDatabase(AddPseudoJuryActivity.this).pseudoJuryDao().insertPseudoJury(ps);

                int test = NotationDatabase.getDatabase(AddPseudoJuryActivity.this).pseudoJuryDao().loadAllPseudoJurys().size();
                Log.d("Test BDD : ",""+test);
                new HttpRequestTask().execute();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPseudoJuryActivity.this, ShowPseudoJurysActivity.class);
                startActivity(intent);
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, DoorProjects> {


        protected void onPreExecute(){
            progressDialog = new ProgressDialog(AddPseudoJuryActivity.this);
            progressDialog.setTitle("Chargement");
            progressDialog.setMessage("Progression ...");
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);//horizontal
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER); //Circular
            progressDialog.setProgress(0);
            progressDialog.setMax(20); // the progress has 20 steps
            progressDialog.setIndeterminate(true);// infinite loop in the progress
            progressDialog.show();

        }

        @Override
        protected DoorProjects doInBackground(Void... params) {
            try {
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                DoorProjects doorProjects = webService.porte(AddPseudoJuryActivity.this, pref.getString("username", null), pref.getString("token", null));
                return doorProjects;
            } catch (LoginError e) {
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(DoorProjects doorProjects) {
            if (doorProjects == null) {
                if ("Invalide Credentials".equals(message)) {
                    Intent intent = new Intent(AddPseudoJuryActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(!"".equals(message)) {
                    Log.e( "AddPseudoJuryActivity", message );
                }
            }
            else{
                for(Project p : doorProjects.getProjects()) {
                    DatabaseProject dP = new DatabaseProject(p.getTitle(),p.getDescription(),p.getPoster(),ps.getIdPseudoJury());
                    NotationDatabase.getDatabase(AddPseudoJuryActivity.this).databaseProjectDao().insertProject(dP);
                }
                progressDialog.dismiss();
                Intent intent = new Intent(AddPseudoJuryActivity.this, MainActivity.class);
                startActivity(intent);


            }
        }

    }


}
