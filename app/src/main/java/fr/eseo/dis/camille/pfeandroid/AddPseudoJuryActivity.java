package fr.eseo.dis.camille.pfeandroid;

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
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.database.DatabaseProject;
import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;
import fr.eseo.dis.camille.pfeandroid.dto.door.Project;

import static java.lang.System.in;

public class AddPseudoJuryActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String usernameString;
    String passwordString;
    PseudoJury ps = null;
    String token = "";
    String userPseudoString = "";

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

                WebServices service = new WebServices();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", userPseudoString);
                pref.getString("token",token);
                List<Project> lsProj = service.porte(AddPseudoJuryActivity.this,userPseudoString,token).getProjects();

                for(Project p : lsProj) {
                    DatabaseProject dP = new DatabaseProject(p.getTitle(),p.getDescription(),p.getPoster(),ps.getIdPseudoJury());
                    NotationDatabase.getDatabase(AddPseudoJuryActivity.this).databaseProjectDao().insertProject(dP);
                }

                NotationDatabase.getDatabase(AddPseudoJuryActivity.this).pseudoJuryDao().insertPseudoJury(ps);


                int test = NotationDatabase.getDatabase(AddPseudoJuryActivity.this).pseudoJuryDao().loadAllPseudoJurys().size();
                Log.d("Test BDD : ",""+test);
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

    private class AddPseudoJuryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            NotationDatabase.getDatabase(AddPseudoJuryActivity.this).pseudoJuryDao().insertPseudoJury(ps);
            return null;
        }
    }

}
