package ubcorbit.org.testapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import ubcorbit.org.testapp.services.AllocateCheckService;
import ubcorbit.org.testapp.services.IncrementService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainAc";
    private static String RECORD_NAME = "main-activity.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "done");

    }

}
