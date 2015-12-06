package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class StartupActivity extends AppCompatActivity {
    /*
     * Splash screen and service manager, I think
     * Shows an image, and after a short (1s) delay jumps to HomeActivity
     * Also starts / initializes ConnectionService and InfectionService if they haven't been started
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }


}
