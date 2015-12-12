package bilgerat.wizzy.avast.Activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import bilgerat.wizzy.avast.R;
import bilgerat.wizzy.avast.Services.ConnectionService;


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
        Intent intent = new Intent(this, ConnectionService.class);
        startService(intent);
        ConnectionService.startBluetooth(this);
        bindButtons();
    }

    private void bindButtons() {
        Button mapButton = (Button) findViewById(R.id.home_map_button);
        final Activity homeActivity = this;
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, MapActivity.class);
                startActivity(intent);
            }
        });
        Button howtoButton = (Button) findViewById(R.id.home_how_to_button);
        howtoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, HowToActivity.class);
                startActivity(intent);
            }
        });
        Button selfviewButton = (Button) findViewById(R.id.home_self_view_button);
        selfviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, SelfViewActivity.class);
                startActivity(intent);
            }
        });
        Button designButton = (Button) findViewById(R.id.home_design_appearance_button);
        designButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, DesignAppearanceActivity.class);
                startActivity(intent);
            }
        });
        Button settingsButton = (Button) findViewById(R.id.home_settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button quitButton = (Button) findViewById(R.id.home_quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity.finish();
                System.exit(0);
            }
        });
    }


}
