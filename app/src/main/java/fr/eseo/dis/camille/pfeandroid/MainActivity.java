package fr.eseo.dis.camille.pfeandroid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.bean.Login;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView messageError;
    WebServices webServices;

    String usernameString;
    String passwordString;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button homeActivity = (Button) findViewById(R.id.sign_in_button);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        messageError = (TextView)findViewById(R.id.message_error);
        webServices = new WebServices();
        homeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameString = username.getText().toString();
                passwordString = password.getText().toString();
                new HttpRequestTask().execute();
                /*String message = "";
                try {
                    Login login = webServices.login(MainActivity.this, usernameString, passwordString);
                } catch (LoginError e) {
                    message = message + e.getMessage();
                    messageError.setText(message);
                    messageError.setVisibility(View.VISIBLE);

                }

                if("".equals(message)){
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }*/
            }

        });

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Login> {
        @Override
        protected Login doInBackground(Void... params) {
            try {
                Login log = webServices.login(MainActivity.this, usernameString, passwordString);;
                return log;
            } catch (LoginError e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Login login) {
            intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

    }

}

