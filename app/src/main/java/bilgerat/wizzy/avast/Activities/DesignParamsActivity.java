package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class DesignParamsActivity extends AppCompatActivity {
    /*
     * Customize virus stats
     * +- stat bars
     * Skill powers 0-10
     * Stats:
     *  Aggression (rename reproduction?) (1p -> 7.5%)
     *  Strength (1p -> 1p)
     *  Heat resistance (1p -> 10%)
     *  Infection (contact) (bluetooth) (1p -> 2%)
     *  Infection (air) (gps) (1p -> .1%)
     *
     * 25p allocated
     *
     * Allow input to name virus
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design_params, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
