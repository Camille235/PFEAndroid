package fr.eseo.dis.camille.pfeandroid;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class DisplayPosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_poster);

        Bitmap fullPoster = ProjectDetailsActivity.fullPoster;
        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        posterImageView.setImageBitmap(fullPoster);

    }
}
