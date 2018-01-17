package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.eseo.dis.camille.pfeandroid.database.NotationDatabase;
import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;

public class AddPseudoJuryActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String usernameString;
    String passwordString;
    PseudoJury ps = null;

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
