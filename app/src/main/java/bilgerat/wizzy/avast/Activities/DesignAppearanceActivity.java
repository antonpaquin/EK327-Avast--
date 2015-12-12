package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import bilgerat.wizzy.avast.R;
import bilgerat.wizzy.avast.Support.VirusView;

public class DesignAppearanceActivity extends AppCompatActivity {
    /*
     * Draw a virus
     * Base: circle with 12 spokes
     * Controls: shapes, colors
     *   Shapes:
     *      Triangle
     *      Square
     *      Circle
     *      'y' shape
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
        bindButtons();
    }

    private void bindButtons() {
        Log.d("Anton", "bind");
        final View slidermenu = findViewById(R.id.design_color_slider_menu);
        Button sliderButton = (Button) findViewById(R.id.design_slider_button);
        sliderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidermenu.getVisibility() == View.GONE) {
                    slidermenu.setVisibility(View.VISIBLE);
                }else{
                    slidermenu.setVisibility(View.GONE);
                }
            }
        });
        final VirusView virus = (VirusView) findViewById(R.id.design_virus_view);
        final Button geomButton = (Button) findViewById(R.id.design_geometry_button);
        geomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geomButton.setText(Integer.toString(virus.getColor(0)));
            }
        });
        SeekBar red = (SeekBar) findViewById(R.id.design_red_slider);
        red.setMax(255);
        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                virus.changeRed(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar green = (SeekBar) findViewById(R.id.design_green_slider);
        green.setMax(255);
        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                virus.changeGreen(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar blue = (SeekBar) findViewById(R.id.design_blue_slider);
        blue.setMax(255);
        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                virus.changeBlue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
