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
     *  Aggression (rename reproduction?) (10% + 5%/1p)
     *  Strength (1 + 1/1p)
     *  Heat resistance (0 + 10%/1p)
     *  Infection (contact) (bluetooth) (5% + 2%/1p)
     *  Infection (air) (gps) ( 0.5% + 0.15%/1p)
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
