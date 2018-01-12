package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.login.Login;


public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView messageError;
    WebServices webServices;

    String usernameString;
    String passwordString;
    Intent intent;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button homeActivity = (Button) findViewById(R.id.sign_in_button);
        final Button autoLog = (Button) findViewById(R.id.auto_button);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        messageError = (TextView)findViewById(R.id.message_error);
        webServices = new WebServices();
        homeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameString = username.getText().toString();
                passwordString = password.getText().toString();
                if(!"".equals(usernameString) && !"".equals(passwordString)){
                    new HttpRequestTask().execute();
                }else{

                }

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

        autoLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameString = "woodwric";
                passwordString = "odoxH6dwrjix";
                new HttpRequestTask().execute();
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
                message = e.getMessage();
                //Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Login login) {
            if (login == null) {
                messageError.setText(message);
                messageError.setVisibility(View.VISIBLE);
            }

            else{

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username", usernameString);
                editor.putString("token", login.getToken());
                editor.commit(); // commit changes
                intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }

    }

}

