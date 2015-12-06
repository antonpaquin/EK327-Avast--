package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class MapActivity extends AppCompatActivity {

    /*
     * Gets an image from the server and draws it to an ImageView
     * Image should be a map of US with dots at each location where someone you have infected is located
     * Not much to do here
     *
     * NOTE: for image loading, let's use the UIL library
     * Yell at Anton if you're here and it isn't installed yet / isn't clear how to use it
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
}
