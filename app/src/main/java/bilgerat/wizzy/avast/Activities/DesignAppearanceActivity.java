package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class DesignAppearanceActivity extends AppCompatActivity {
    /*
     * Draw a virus
     * Base: circle with 12 spokes
     * Controls: shapes, colors
     *   Shapes:
     *      Triangle
     *      Square
     *      Circle
     *      Rhombus
     *      'y' shape
     *      Squiggles
     *      blank
     *   Colors:
     *      RGB sliders
     * Choosing a button opens a sub-menu with options
     * Tapping a spoke / center changes options for that element
     * Yes, it's limited, but it's easy to implement
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_appearance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design_appearance, menu);
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
