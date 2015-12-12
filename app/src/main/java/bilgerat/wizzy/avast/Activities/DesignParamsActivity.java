package bilgerat.wizzy.avast.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;

import android.widget.EditText;

import bilgerat.wizzy.avast.R;
import bilgerat.wizzy.avast.Services.InfectionService;

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

    static int aggression = 0;
    static int strength = 0;
    static int climate=0;
    static int bluetoothInfection = 0;
    static int gpsInfection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_params);
        bindButtons();
    }


    void bindButtons()
    {
        final Activity paramsActivity = this;
        final EditText editText = (EditText) findViewById(R.id.design_params_virus_name);
        Button doneButton = (Button) findViewById(R.id.params_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfectionService.doNewBuild(editText.getText().toString(), aggression, strength, climate, bluetoothInfection, gpsInfection);
                Intent intent = new Intent(paramsActivity, HomeActivity.class);
                startActivity(intent);
            }
        });

        Button aggressionPlus = (Button) findViewById(R.id.params_aggression_plus);
        aggressionPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aggression < 10)
                    aggression++;
            }
        });

        Button aggressionMinus = (Button) findViewById(R.id.params_aggression_minus);
        aggressionMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aggression > 0)
                    aggression--;
            }
        });

        Button strengthPlus = (Button) findViewById(R.id.params_strength_plus);
        strengthPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strength < 10)
                    strength++;
            }
        });

        Button strengthMinus = (Button) findViewById(R.id.params_strength_minus);
        strengthMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strength > 0)
                    strength--;
            }
        });

        Button climatePlus = (Button) findViewById(R.id.params_robustness_plus);
        climatePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (climate < 10)
                    climate++;
            }
        });

        Button climateMinus = (Button) findViewById(R.id.params_robustness_minus);
        climateMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (climate > 0)
                    climate--;
            }
        });

        Button bluetoothPlus = (Button) findViewById(R.id.params_bluetooth_infectivity_plus);
        bluetoothPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothInfection < 10)
                    bluetoothInfection++;
            }
        });

        Button bluetoothMinus = (Button) findViewById(R.id.params_bluetooth_infectivity_minus);
        bluetoothMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothInfection > 0)
                    bluetoothInfection--;
            }
        });

        Button gpsPlus = (Button) findViewById(R.id.params_gps_infectivity_plus);
        gpsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gpsInfection < 10)
                    gpsInfection++;

                LinearLayout l = (LinearLayout) findViewById(R.id.params_bluetooth_infectivity_layout);
                redraw(gpsInfection, l);
            }
        });

        Button gpsMinus = (Button) findViewById(R.id.params_gps_infectivity_minus);
        gpsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gpsInfection > 0)
                    gpsInfection--;

                LinearLayout l = (LinearLayout) findViewById(R.id.params_bluetooth_infectivity_layout);
                redraw(gpsInfection, l);
            }
        });


    }

    void redraw(int n, LinearLayout theLayout)
    {
        for (int i = 0; i < n; i++)
        {
            theLayout.getChildAt(i).setBackgroundColor(0x00FF00);
        }
        for (int i = n; i < 10; i++)
        {
            theLayout.getChildAt(i).setBackgroundColor(0xBBBBBB);
        }
    }
}
