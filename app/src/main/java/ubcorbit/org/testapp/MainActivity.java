package ubcorbit.org.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import ubcorbit.org.testapp.services.StartupService;

public class MainActivity extends AppCompatActivity {

    /**
     * This is a dummy activity required for the app services
     * to function independently.
     *
     * IMPORTANT: Upon each (re)install this activity must be run
     * at least once for the the manifest to be processed, and the
     * services and receivers to be registered.
     */

    // NOTE: all class tags have to begin with "orbit" for easier log-filtering
    private static final String TAG = "orbitMainAc";
    private static String RECORD_NAME = "main-activity.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // StartupService handles all the test runs
        Intent i = new Intent(this, StartupService.class);
        startService(i);

    }

}
