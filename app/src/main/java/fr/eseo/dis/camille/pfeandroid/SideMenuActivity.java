package fr.eseo.dis.camille.pfeandroid;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SideMenuActivity extends Activity {

    private String[] drawerItemsList;
    private ListView myDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        drawerItemsList = getResources().getStringArray(R.array.items);
        myDrawer = (ListView) findViewById(R.id.my_drawer);
        myDrawer.setAdapter(new ArrayAdapter<String>(this,
                R.layout.navigation_menu, drawerItemsList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

