package fr.eseo.dis.camille.pfeandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.Login;

import static android.R.id.message;
import static fr.eseo.dis.camille.pfeandroid.R.id.username;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final Button listProjectActivity = (Button) findViewById(R.id.button_all_projects);
        final Button listMyProjectActivity = (Button) findViewById(R.id.button_my_projects);

        listProjectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListProjectActivity.class);
                intent.putExtra("listProjectStyle", "all");
                startActivity(intent);
            }

        });

        listMyProjectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListProjectActivity.class);
                intent.putExtra("listProjectStyle", "my");
                startActivity(intent);
            }

        });

        TextView text = (TextView)findViewById(R.id.textView);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String username = pref.getString("username", null);
        text.setText(username);




    }


}
