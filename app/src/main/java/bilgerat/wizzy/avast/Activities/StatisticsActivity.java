package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class StatisticsActivity extends AppCompatActivity {

    /*
     * Fetches and presents data:
     *   Picture of virus
     *   Number of infected
     *   Graph of infected vs time
     *   Average infection per host
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
}
