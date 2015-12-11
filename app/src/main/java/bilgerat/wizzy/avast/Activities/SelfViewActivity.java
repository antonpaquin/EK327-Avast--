package bilgerat.wizzy.avast.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import bilgerat.wizzy.avast.R;

public class SelfViewActivity extends AppCompatActivity {
    /*
     * Shows what viruses have infected you.
     * Contains a RecyclerView with virus stats, and a picture showing % infections
     * Data (picture, %list) should be got from server
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);
    }
}
