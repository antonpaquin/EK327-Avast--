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
}
