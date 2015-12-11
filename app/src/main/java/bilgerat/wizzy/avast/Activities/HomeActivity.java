package bilgerat.wizzy.avast.Activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
     * Plus maybe some cool bio-themed art in the BG
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = new Intent(this, ConnectionService.class);
        startService(intent);
        ConnectionService.startBluetooth(this);
    }


}
