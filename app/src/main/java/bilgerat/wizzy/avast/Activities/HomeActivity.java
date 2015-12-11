package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class HomeActivity extends AppCompatActivity {

    /*
     * Main screen -- Draws a bunch of buttons to move to different activities
     * Links:
     *   How to (HowToActivity)
     *   My Virus (StatisticsActivity)
     *   My Host (SelfViewActivity)
     *   Global Spread (MapActivity)
     *   Design New Virus (DesignParamsActivity)
     *   Exit
     *   Settings
     * Plus maybe some cool bio-themed art in the BG
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

}
